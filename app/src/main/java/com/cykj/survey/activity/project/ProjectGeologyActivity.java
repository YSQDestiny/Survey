package com.cykj.survey.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.cykj.survey.R;
import com.cykj.survey.activity.MapProjectActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectGeologyActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_geology_1)
    Spinner projectGeology1;
    @BindView(R.id.project_geology_2)
    Spinner projectGeology2;
    @BindView(R.id.project_geology_3)
    Spinner projectGeology3;
    @BindView(R.id.project_geology_4)
    Spinner projectGeology4;
    @BindView(R.id.project_geology_5)
    Spinner projectGeology5;
    @BindView(R.id.project_geology_6)
    Spinner projectGeology6;
    @BindView(R.id.project_geology_edit_6)
    EditText projectGeologyEdit6;
    @BindView(R.id.project_geology_7)
    Spinner projectGeology7;
    @BindView(R.id.project_geology_edit_8)
    EditText projectGeologyEdit8;
    @BindView(R.id.project_geology_9)
    Spinner projectGeology9;
    @BindView(R.id.project_geology_edit_10)
    EditText projectGeologyEdit10;
    @BindView(R.id.project_geology_11)
    Spinner projectGeology11;
    @BindView(R.id.project_geology_edit_11)
    EditText projectGeologyEdit11;
    @BindView(R.id.project_geology_12)
    Spinner projectGeology12;
    @BindView(R.id.project_geology_edit_12)
    EditText projectGeologyEdit12;
    @BindView(R.id.project_geology_13)
    Spinner projectGeology13;
    @BindView(R.id.project_geology_14)
    Spinner projectGeology14;
    @BindView(R.id.project_geology_15)
    Spinner projectGeology15;
    @BindView(R.id.project_geology_16)
    Spinner projectGeology16;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_geology);
        ButterKnife.bind(this);
        initTopbar();
        initData();
        initView();
    }

    private void initTopbar() {
        topbar.setTitle("地质选项");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectGeologyActivity.this, MapProjectActivity.class);
                startActivity(intent);
            }
        });
    }


    private List<String> pgData_1 = new ArrayList<>();
    private List<String> pgData_2 = new ArrayList<>();
    private List<String> pgData_3 = new ArrayList<>();
    private List<String> pgData_4 = new ArrayList<>();
    private List<String> pgData_5 = new ArrayList<>();
    private List<String> pgData_6 = new ArrayList<>();
    private List<String> pgData_7 = new ArrayList<>();
    private List<String> pgData_9 = new ArrayList<>();
    private List<String> pgData_11 = new ArrayList<>();
    private List<String> pgData_12 = new ArrayList<>();
    private List<String> pgData_13 = new ArrayList<>();
    private List<String> pgData_14 = new ArrayList<>();
    private List<String> pgData_15 = new ArrayList<>();
    private List<String> pgData_16 = new ArrayList<>();

    private void initData(){

        pgData_1.add("高原");
        pgData_1.add("山地");
        pgData_1.add("平原");
        pgData_1.add("丘陵");
        pgData_1.add("盆地");

        pgData_2.add("较大");
        pgData_2.add("较小");
        pgData_2.add("一般");

        pgData_3.add("较陡");
        pgData_3.add("较缓");
        pgData_3.add("一般");

        pgData_4.add("较好");
        pgData_4.add("一般");
        pgData_4.add("较差");

        pgData_5.add("落叶乔木");
        pgData_5.add("灌木");
        pgData_5.add("灌草");
        pgData_5.add("杂草");
        pgData_5.add("荒漠");

        pgData_6.add("造山带");
        pgData_6.add("断裂带");
        pgData_6.add("稳定陆块");
        pgData_6.add("弧盆区");
        pgData_6.add("岛弧带");
        pgData_6.add("手动输入");

        pgData_7.add("存在");
        pgData_7.add("不存在");

        pgData_9.add("较大");
        pgData_9.add("一般");
        pgData_9.add("较小");
        pgData_9.add("无");

        pgData_11.add("人工填土");
        pgData_11.add("植物堆积层");
        pgData_11.add("冲洪积层");
        pgData_11.add("残坡积层");
        pgData_11.add("冰水积层");
        pgData_11.add("崩积层");
        pgData_11.add("沼泽沉积层");
        pgData_11.add("滑坡堆积");
        pgData_11.add("手动输入");

        pgData_12.add("砂岩");
        pgData_12.add("砾岩");
        pgData_12.add("页岩");
        pgData_12.add("泥岩");
        pgData_12.add("碳酸盐岩");
        pgData_12.add("变质岩");
        pgData_12.add("岩浆岩");
        pgData_12.add("碎屑堆积物");
        pgData_12.add("手动输入");

        pgData_13.add("松散岩类孔隙水");
        pgData_13.add("基岩裂隙水");
        pgData_13.add("岩溶水");
        pgData_13.add("上层滞水");
        pgData_13.add("承压水");
        pgData_13.add("潜水");

        pgData_14.add("丰富");
        pgData_14.add("缺乏");
        pgData_14.add("一般");

        pgData_15.add("无腐蚀性");
        pgData_15.add("微腐蚀性");
        pgData_15.add("弱腐蚀性");
        pgData_15.add("中等腐蚀性");
        pgData_15.add("强腐蚀性");

        pgData_16.add("路基");
        pgData_16.add("桥基");
        pgData_16.add("隧道");



    }

    private void initView(){

        ArrayAdapter<String> pgAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_1);
        ArrayAdapter<String> pgAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_2);
        ArrayAdapter<String> pgAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_3);
        ArrayAdapter<String> pgAdapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_4);
        ArrayAdapter<String> pgAdapter5 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_5);
        ArrayAdapter<String> pgAdapter6 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_6);
        ArrayAdapter<String> pgAdapter7 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_7);
        ArrayAdapter<String> pgAdapter9 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_9);
        ArrayAdapter<String> pgAdapter11 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_11);
        ArrayAdapter<String> pgAdapter12 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_12);
        ArrayAdapter<String> pgAdapter13 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_13);
        ArrayAdapter<String> pgAdapter14 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_14);
        ArrayAdapter<String> pgAdapter15 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_15);
        ArrayAdapter<String> pgAdapter16 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pgData_16);

        projectGeology1.setAdapter(pgAdapter1);
        projectGeology2.setAdapter(pgAdapter2);
        projectGeology3.setAdapter(pgAdapter3);
        projectGeology4.setAdapter(pgAdapter4);
        projectGeology5.setAdapter(pgAdapter5);
        projectGeology6.setAdapter(pgAdapter6);
        projectGeology7.setAdapter(pgAdapter7);
        projectGeology9.setAdapter(pgAdapter9);
        projectGeology11.setAdapter(pgAdapter11);
        projectGeology12.setAdapter(pgAdapter12);
        projectGeology13.setAdapter(pgAdapter13);
        projectGeology14.setAdapter(pgAdapter14);
        projectGeology15.setAdapter(pgAdapter15);
        projectGeology16.setAdapter(pgAdapter16);

        projectGeology6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pgData_6.get(position).equals("手动输入")){
                    projectGeologyEdit6.setVisibility(View.VISIBLE);
                }else {
                    projectGeologyEdit6.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        projectGeology11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pgData_11.get(position).equals("手动输入")){
                    projectGeologyEdit11.setVisibility(View.VISIBLE);
                }else {
                    projectGeologyEdit11.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        projectGeology12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pgData_12.get(position).equals("手动输入")){
                    projectGeologyEdit12.setVisibility(View.VISIBLE);
                }else {
                    projectGeologyEdit12.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
}

}
