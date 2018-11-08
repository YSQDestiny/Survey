package com.cykj.survey.activity.hydropower;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_other_1)
    Spinner hydroOther1;
    @BindView(R.id.hydro_other_2)
    Spinner hydroOther2;
    @BindView(R.id.hydro_other_3)
    Spinner hydroOther3;

    private static List<String> data1 = new ArrayList();
    private static List<String> data2 = new ArrayList();
    private static List<String> data3 = new ArrayList();

    static {
        data1.add("有");
        data1.add("无");

        data2.add("有");
        data2.add("无");

        data3.add("小");
        data3.add("较大");
    }

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_other);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initTopbar() {
        topbar.setTitle("其他风险");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView(){
        ArrayAdapter<String> hdAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1);
        ArrayAdapter<String> hdAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2);
        ArrayAdapter<String> hdAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3);

        spinnerSetAdapter(hdAdapter1,hydroOther1);
        spinnerSetAdapter(hdAdapter1,hydroOther2);
        spinnerSetAdapter(hdAdapter1,hydroOther3);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
