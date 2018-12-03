package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.rengwuxian.materialedittext.MaterialEditText;

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


    private static List<String> data4 = new ArrayList<>();
    private static List<String> data5 = new ArrayList<>();
    private static List<String> data6 = new ArrayList<>();
    private static List<String> data7 = new ArrayList<>();
    private static List<String> data8 = new ArrayList<>();

    static {
        data4.add("无");
        data4.add("有");

        data5.add("无");
        data5.add("有");

        data6.add("无");
        data6.add("有");

        data7.add("无");
        data7.add("有（承重墙体、梁、顶等有开裂，变形等）");

        data8.add("无");
        data8.add("有");
    }

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_building_use)
    MaterialEditText hydroBuildingUse;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.accident_edit_2)
    EditText accidentEdit2;
    @BindView(R.id.hydro_building_4)
    Spinner hydroBuilding4;
    @BindView(R.id.hydro_building_5)
    Spinner hydroBuilding5;
    @BindView(R.id.hydro_building_6)
    Spinner hydroBuilding6;
    @BindView(R.id.accident_building_6_edit)
    EditText accidentBuilding6Edit;
    @BindView(R.id.hydro_building_7)
    Spinner hydroBuilding7;
    @BindView(R.id.hydro_building_8)
    Spinner hydroBuilding8;
    @BindView(R.id.accident_building_4_edit)
    EditText accidentBuilding4Edit;

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

    private void initTopbar() {
        topbar.setTitle("水工建筑风险");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {

        final ArrayAdapter<String> hdAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data4);
        ArrayAdapter<String> hdAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data5);
        ArrayAdapter<String> hdAdapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data6);
        ArrayAdapter<String> hdAdapter7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data7);
        ArrayAdapter<String> hdAdapter8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data8);

        spinnerSetAdapter(hdAdapter4, hydroBuilding4);
        spinnerSetAdapter(hdAdapter5, hydroBuilding5);
        spinnerSetAdapter(hdAdapter6, hydroBuilding6);
        spinnerSetAdapter(hdAdapter7, hydroBuilding7);
        spinnerSetAdapter(hdAdapter8, hydroBuilding8);

        hydroBuilding6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (data6.get(position)) {
                    case "有":
                        accidentBuilding6Edit.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        accidentBuilding6Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }

        });

        hydroBuilding8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (data8.get(position)) {
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

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void setData() {

        Intent intent = new Intent(BuildingActivity.this, OtherActivity.class);
        startActivity(intent);
        finish();

//        String building = "";
//        building += accidentEdit.getText().toString() + "现场查勘大坝主体结构良好，坝体" + hydroBuilding2.getSelectedItem().toString()
//                + "漏水。启闭机及闸门等均维护较好，" + hydroBuilding3.getSelectedItem().toString() + "明显缺陷。电站厂房置于坝体后方左岸开挖基岩上。目前厂房主体结构"
//                + hydroBuilding4.getSelectedItem().toString() + "。";
//
//        String url = Constants.TEST_SERVICE + "/hydro/uploadBuilding";
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = new FormBody.Builder()
//                .add("building", building)
//                .add("id", Constants.HYDRO_ID.toString())
//                .build();
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String resultStr = response.body().string();
//                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
//                if (result.getCode() == 0) {
//                    Intent intent = new Intent(BuildingActivity.this, OtherActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
    }
}
