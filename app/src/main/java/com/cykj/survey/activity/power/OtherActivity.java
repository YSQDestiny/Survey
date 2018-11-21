package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        data1.add("没有");

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
                finish();
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

    private void setData(){
        String other = "";

        other += "箭板电站管理较为规范，厂区周围" + hydroOther1.getSelectedItem().toString() + "建造围墙，24小时有人值班，" +
                "与区域群众" + hydroOther2.getSelectedItem().toString() + "民事纠纷，设备设施遭受人员或小动物损坏可能性"
                + hydroOther3.getSelectedItem().toString()+"。";

        String url = Constants.TEST_SERVICE + "/hydro/uploadOthers";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("other",other)
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
                    finish();
                }
            }
        });
    }
}
