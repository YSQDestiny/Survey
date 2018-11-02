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

public class ElectromechanicalActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_electromechanical_1)
    Spinner hydroElectromechanical1;
    @BindView(R.id.hydro_electromechanical_2)
    Spinner hydroElectromechanical2;
    @BindView(R.id.hydro_electromechanical_3)
    Spinner hydroElectromechanical3;
    @BindView(R.id.hydro_electromechanical_4)
    Spinner hydroElectromechanical4;
    @BindView(R.id.hydro_disaster_level)
    MaterialEditText hydroDisasterLevel;
    @BindView(R.id.hydro_disaster_use_year)
    MaterialEditText hydroDisasterUseYear;
    @BindView(R.id.hydro_disaster_crew)
    MaterialEditText hydroDisasterCrew;
    @BindView(R.id.hydro_electromechanical_8)
    Spinner hydroElectromechanical8;

    private static List<String> data1 = new ArrayList<>();
    private static List<String> data2 = new ArrayList<>();
    private static List<String> data3 = new ArrayList<>();
    private static List<String> data4 = new ArrayList<>();
    private static List<String> data8 = new ArrayList<>();

    static{
        data1.add("管理人员数量");
        data1.add("运维人员数量");
        data2.add("充足");
        data2.add("不足");
        data3.add("齐全");
        data3.add("不齐全");
        data4.add("是");
        data4.add("否");
        data8.add("正常");
        data8.add("温度异常");
        data8.add("噪声异常");
        data8.add("出力异常");
        data8.add("振动异常");
    }

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_electromechanical);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initTopbar(){
        topbar.setTitle("机电设备风险");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectromechanicalActivity.this,BuildingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        ArrayAdapter<String> hdAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1);
        ArrayAdapter<String> hdAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2);
        ArrayAdapter<String> hdAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3);
        ArrayAdapter<String> hdAdapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4);
        ArrayAdapter<String> hdAdapter8 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data8);

        spinnerSetAdapter(hdAdapter1,hydroElectromechanical1);
        spinnerSetAdapter(hdAdapter2,hydroElectromechanical2);
        spinnerSetAdapter(hdAdapter3,hydroElectromechanical3);
        spinnerSetAdapter(hdAdapter4,hydroElectromechanical4);
        spinnerSetAdapter(hdAdapter8,hydroElectromechanical8);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

}
