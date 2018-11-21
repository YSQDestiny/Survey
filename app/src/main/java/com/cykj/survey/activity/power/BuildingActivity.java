package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
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

public class BuildingActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.hydro_building_1)
    Spinner hydroBuilding1;
    @BindView(R.id.hydro_building_2)
    Spinner hydroBuilding2;
    @BindView(R.id.accident_building_2_edit)
    EditText accidentBuilding2Edit;
    @BindView(R.id.hydro_building_3)
    Spinner hydroBuilding3;
    @BindView(R.id.hydro_building_4)
    Spinner hydroBuilding4;
    @BindView(R.id.accident_building_4_edit)
    EditText accidentBuilding4Edit;

    private static List<String> data2 = new ArrayList<>();

    private static List<String> data3 = new ArrayList<>();

    private static List<String> data4 = new ArrayList<>();

    private static List<String> data5 = new ArrayList<>();

    static {
        data2.add("结构良好，无漏水");
        data2.add("有漏水");

        data3.add("无");
        data3.add("有");

        data4.add("无");
        data4.add("有");

        data5.add("无");
        data5.add("有");
    }

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_building);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initTopbar(){
        topbar.setTitle("水工建筑风险");
        topbar.addRightTextButton("采集照片",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
    }

    private void initView(){

        final ArrayAdapter<String> hdAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2);
        ArrayAdapter<String> hdAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3);
        ArrayAdapter<String> hdAdapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4);
        ArrayAdapter<String> hdAdapter5 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5);

        spinnerSetAdapter(hdAdapter2,hydroBuilding1);
        spinnerSetAdapter(hdAdapter3,hydroBuilding2);
        spinnerSetAdapter(hdAdapter4,hydroBuilding3);
        spinnerSetAdapter(hdAdapter5,hydroBuilding4);

        hydroBuilding2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (data3.get(position)){
                    case "有":
                        accidentBuilding2Edit.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        accidentBuilding2Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }

        });

        hydroBuilding4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (data5.get(position)){
                    case "有":
                        accidentBuilding4Edit.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        accidentBuilding4Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                return;
            }
        });

    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void setData(){
        String building = "";
        building += accidentEdit.getText().toString() + "现场查勘大坝主体结构良好，坝体" + hydroBuilding2.getSelectedItem().toString()
        + "漏水。启闭机及闸门等均维护较好，" + hydroBuilding3.getSelectedItem().toString() + "明显缺陷。电站厂房置于坝体后方左岸开挖基岩上。目前厂房主体结构"
        + hydroBuilding4.getSelectedItem().toString()+"。";

        String url = Constants.TEST_SERVICE + "/hydro/uploadBuilding";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("building",building)
                .add("id",Constants.HYDRO_ID.toString())
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
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr,ResultModel.class);
                if (result.getCode() == 0){
                    Intent intent = new Intent(BuildingActivity.this,OtherActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
