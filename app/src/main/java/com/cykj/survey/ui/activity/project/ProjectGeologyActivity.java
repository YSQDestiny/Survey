package com.cykj.survey.ui.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.ui.activity.MapProjectActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    private void postData(){

        String url = Constants.TEST_SERVICE + "/project/postStr";

        String pg6Str = null;
        String pg8Str = null;
        String pg10Str = null;
        String pg11Str = null;
        String pg12Str = null;

        if (projectGeology6.getSelectedItem().toString().equals("手动填写")){
            if (projectGeologyEdit6.getText().toString().equals("")){
                showToastShort("请输入工程项目所在地所属构造区");
            }else {
                pg6Str = projectGeologyEdit6.getText().toString();
            }
        }
        if (projectGeology11.getSelectedItem().toString().equals("手动填写")){
            if (projectGeologyEdit11.getText().toString().equals("")){
                showToastShort("请输入该地区的覆盖层类型");
            }else {
                pg11Str = projectGeologyEdit11.getText().toString();
            }
        }
        if (projectGeology12.getSelectedItem().toString().equals("手动填写")){
            if (projectGeologyEdit12.getText().toString().equals("")){
                showToastShort("请输入该地区的基岩类型");
            }else {
                pg12Str = projectGeologyEdit12.getText().toString();
            }
        }
        if (projectGeologyEdit8.getText().toString().equals("")){
            showToastShort("请输入工程项目穿越过的断裂构造");
        }else{
            pg8Str = projectGeologyEdit8.getText().toString();
        }
        if (projectGeologyEdit10.getText().toString().equals("")){
            showToastShort("请输入工程项目所在地的地震烈度");
        }else {
            pg10Str = projectGeologyEdit10.getText().toString();
        }

        String str = "工程区所在地区属" + projectGeology1.getSelectedItem().toString() + "地貌区，地势起伏" + projectGeology2.getSelectedItem().toString() +
        "，山体坡度" + projectGeology3.getSelectedItem().toString() + "，区域内植被覆盖情况"+ projectGeology4.getSelectedItem().toString() + "，主要以"
        + projectGeology5.getSelectedItem().toString() +"为主。" + "\n" + "工程区所在地区处";

        if (pg6Str != null){
            str += pg6Str;
        }else {
            str += projectGeology6.getSelectedItem().toString();
        }

        str += "，工程区内" + projectGeology7.getSelectedItem().toString() + "构造，该工程项目穿越过的断裂构造有" +
                pg8Str +"，断裂构造对该工程项目的影响"+ projectGeology9.getSelectedItem().toString() +"。本工程沿线地震基本烈度为"+
                pg10Str + "度。";

        if (pg11Str != null){
            str += "\n" + "根据工程地质勘察情况，该地区的覆盖层主要为" + pg11Str;
        }else {
            str += "\n" + "根据工程地质勘察情况，该地区的覆盖层主要为" + projectGeology11.getSelectedItem().toString();
        }

        if (pg12Str != null){
            str += "，基岩为" + pg12Str;
        }else {
            str += "，基岩为" + projectGeology12.getSelectedItem().toString();
        }

        str += "\n" + "工程项目所在区域的地下水为" + projectGeology13.getSelectedItem().toString() +"。根据详勘成果，该区域地表水较为"+
                projectGeology14.getSelectedItem().toString() +",地下水体对混凝土为"+ projectGeology15.getSelectedItem().toString() +"。"
                + "\n" + "项目沿线与公路工程有关的工程地质问题主要集中在"+projectGeology16.getSelectedItem().toString()+"。";

        

        OkHttpClient client = new OkHttpClient();

        Long projectId = Constants.PROJECT_ID;

        RequestBody body = new FormBody.Builder()
                .add("projectId",projectId.toString())
                .add("geologyStr",str)
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
                    Intent intent = new Intent(ProjectGeologyActivity.this, MapProjectActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initTopbar() {
        topbar.setTitle("地质选项");
//        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                postData();
//            }
//        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
