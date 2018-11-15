package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                Intent intent = new Intent(BuildingActivity.this,OtherActivity.class);
                startActivity(intent);
                finish();
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
                int count = parent.getCount();
                if (count >= 0){
                    System.out.println("imaging be without you");
                    Log.d("","");
                }
                return;
            }
        });

    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

}
