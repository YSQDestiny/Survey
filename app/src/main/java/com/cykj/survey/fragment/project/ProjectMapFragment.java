package com.cykj.survey.fragment.project;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
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
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProjectMapFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.map_point)
    TextView mapPoint;
    @BindView(R.id.map_add)
    Button mapAdd;
    @BindView(R.id.map_clear)
    Button mapClear;
    @BindView(R.id.map_start)
    Button mapStart;

    private AMap aMap;
    private Handler handler;
    private List<LatLng> latLngList;
    private CityPickerView mPicker = new CityPickerView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.activity_project_map, null);
        ButterKnife.bind(this, root);
        initTopbar();
        if (aMap == null){
            aMap = map.getMap();
        }
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        handler = new Handler();
        latLngList = new ArrayList<>();
        mPicker.init(getActivity());
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

                        if (province == null){
                            showToastShort("省份信息获取失败");
                        }else {
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
                if (latLngList.size() > 1){
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLngList.get(0)));
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
                    aMap.addPolyline(new PolylineOptions().addAll(latLngList).width(10).color(Color.RED));
                }
            }
        });
        return root;
    }

    private void initTopbar(){
        topbar.setTitle("绘制路线");
    }

    private void getLocation(String url){
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
                latLngList.add(new LatLng(Double.parseDouble(point[1]),Double.parseDouble(point[0])));
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
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
}