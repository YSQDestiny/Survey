package com.cykj.survey.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.PolylineOptions;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.ui.activity.project.WeatherChartActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MapProjectActivity extends BaseFragmentActivity {
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.map_point)
    TextView mapPoint;
    @BindView(R.id.map_add)
    Button mapAdd;
    @BindView(R.id.map_start)
    Button mapStart;
    @BindView(R.id.map_clear)
    Button mapClear;

    private AMap aMap;
    private Handler handler;
    private List<LatLng> latLngList;
    private CityPickerView mPicker = new CityPickerView();
    private List<String> passingPointList = new ArrayList<>();

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    private void initTopbar() {
        topbar.setTitle("绘制路线");

//        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (passingPointList.size() > 0){
//                    postPassingPoint();
//                }else {
//                    showToastShort("路径点未选择");
//                }
//
//            }
//        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postPassingPoint(){
        String url = Constants.TEST_SERVICE + "/project/postPassingPoint";

        OkHttpClient client = new OkHttpClient();

        Long projectId = Constants.PROJECT_ID;

        RequestBody body = new FormBody.Builder()
                .add("projectId",projectId.toString())
                .add("passingPoint",JSONObject.toJSONString(passingPointList))
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    Intent intent = new Intent(MapProjectActivity.this,WeatherChartActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_map);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = map.getMap();
        }
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        handler = new Handler();
        latLngList = new ArrayList<>();
        mPicker.init(this);
        initTopbar();
        mapAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                        String url = "https://restapi.amap.com/v3/geocode/geo?key=8dee3eaaaab0b8e95b52d354a2407dd7";
                        String address = "";

                        if (province == null) {
                            showToastShort("省份信息获取失败");
                        } else {
                            address += province.getName();
                        }

                        //城市
                        if (city == null) {
                            showToastShort("城市信息获取失败");
                            return;
                        } else {
                            url += "&city=" + city.getName();
                            address += city.getName();
                        }

                        //地区
                        if (district == null) {
                            showToastShort("地区信息获取失败");
                            return;
                        } else {
                            address += district.getName();
                            url += "&address=" + address;
                        }

                        passingPointList.add(district.getName());

                        getLocation(url);
                    }

                    @Override
                    public void onCancel() {
                        showToastShort("操作取消");
                    }
                });
                mPicker.showCityPicker();
            }
        });

        mapStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latLngList.size() > 1) {
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLngList.get(0)));
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
                    aMap.addPolyline(new PolylineOptions().addAll(latLngList).width(10).color(Color.RED));
                }
            }
        });

        mapClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latLngList.clear();
                mapPoint.setText("0");
                aMap.clear();
            }
        });
    }

    private void getLocation(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultStr = response.body().string();
                JSONArray json = (JSONArray) JSON.parseObject(resultStr).get("geocodes");
                JSONObject geocodes = json.getJSONObject(0);
                String location = geocodes.getString("location");
                String[] point = location.split(",");
                latLngList.add(new LatLng(Double.parseDouble(point[1]), Double.parseDouble(point[0])));
                handler.post(uiAble);
            }
        });
    }

    Runnable uiAble = new Runnable() {
        @Override
        public void run() {
            String size = Integer.toString(latLngList.size());
            mapPoint.setText(size);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
}
