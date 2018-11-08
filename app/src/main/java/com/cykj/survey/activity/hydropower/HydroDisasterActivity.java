package com.cykj.survey.activity.hydropower;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HydroDisasterActivity extends BaseFragmentActivity {


    private static List<String> data1 = new ArrayList<>();
    private static List<String> data2 = new ArrayList<>();
    private static List<String> data4 = new ArrayList<>();
    private static List<String> data5 = new ArrayList<>();
    private static List<String> data6 = new ArrayList<>();
    private static List<String> data7 = new ArrayList<>();
    private static List<String> data8 = new ArrayList<>();
    private static List<String> data10 = new ArrayList<>();
    private static List<String> data15 = new ArrayList<>();
    private static List<String> data16 = new ArrayList<>();
    private static List<String> data17 = new ArrayList<>();

    static {

        data2.add("钢混结构");
        data2.add("砖混结构");
        data2.add("砖木结构");
        data2.add("彩钢结构");

        data4.add("有");
        data4.add("无");

        data5.add("有");
        data5.add("无");

        data6.add("整洁");
        data6.add("较整洁");
        data6.add("较杂乱");
        data6.add("极杂乱");

        data7.add("充足（消防通道宽度大于2米）");
        data7.add("较充足（消防通道宽度大于1米）");
        data7.add("不充足（消防通道堵塞）");

        data8.add("集中保存");
        data8.add("未集中保存");

        data10.add("是");
        data10.add("否");

        data15.add("是");
        data15.add("否");

        data16.add("是");
        data16.add("否");

        data17.add("是");
        data17.add("否");
    }

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_disaster_main)
    MaterialEditText hydroDisasterMain;
    @BindView(R.id.hydro_disaster_vice)
    MaterialEditText hydroDisasterVice;
    @BindView(R.id.hydro_disaster_2)
    Spinner hydroDisaster2;
    @BindView(R.id.hydro_disaster_level)
    MaterialEditText hydroDisasterLevel;
    @BindView(R.id.hydro_disaster_4)
    Spinner hydroDisaster4;
    @BindView(R.id.hydro_disaster_5)
    Spinner hydroDisaster5;
    @BindView(R.id.hydro_disaster_6)
    Spinner hydroDisaster6;
    @BindView(R.id.hydro_disaster_7)
    Spinner hydroDisaster7;
    @BindView(R.id.hydro_disaster_8)
    Spinner hydroDisaster8;
    @BindView(R.id.hydro_disaster_10)
    Spinner hydroDisaster10;
    @BindView(R.id.hydro_transformer_number)
    MaterialEditText hydroTransformerNumber;
    @BindView(R.id.hydro_transformer_version)
    MaterialEditText hydroTransformerVersion;
    @BindView(R.id.hydro_transformer_location)
    MaterialEditText hydroTransformerLocation;
    @BindView(R.id.hydro_disaster_15)
    Spinner hydroDisaster15;
    @BindView(R.id.hydro_disaster_16)
    Spinner hydroDisaster16;
    @BindView(R.id.hydro_disaster_17)
    Spinner hydroDisaster17;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_disaster);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initView() {
        ArrayAdapter<String> hdAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data2);
        ArrayAdapter<String> hdAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data4);
        ArrayAdapter<String> hdAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data5);
        ArrayAdapter<String> hdAdapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data6);
        ArrayAdapter<String> hdAdapter7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data7);
        ArrayAdapter<String> hdAdapter8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data8);
        ArrayAdapter<String> hdAdapter10 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data10);
        ArrayAdapter<String> hdAdapter15 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data15);
        ArrayAdapter<String> hdAdapter16 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data16);
        ArrayAdapter<String> hdAdapter17 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data17);

        spinnerSetAdapter(hdAdapter2, hydroDisaster2);
        spinnerSetAdapter(hdAdapter4, hydroDisaster4);
        spinnerSetAdapter(hdAdapter5, hydroDisaster5);
        spinnerSetAdapter(hdAdapter6, hydroDisaster6);
        spinnerSetAdapter(hdAdapter7, hydroDisaster7);
        spinnerSetAdapter(hdAdapter8, hydroDisaster8);
        spinnerSetAdapter(hdAdapter10, hydroDisaster10);
        spinnerSetAdapter(hdAdapter15, hydroDisaster15);
        spinnerSetAdapter(hdAdapter16, hydroDisaster16);
        spinnerSetAdapter(hdAdapter17, hydroDisaster17);

    }

    private void initTopbar() {
        topbar.setTitle("火灾风险");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HydroDisasterActivity.this, ElectromechanicalActivity.class);
                startActivity(intent);
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

}
