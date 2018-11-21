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

public class ElectromechanicalActivity extends BaseFragmentActivity {

    private static List<String> data2 = new ArrayList<>();
    private static List<String> data3 = new ArrayList<>();
    private static List<String> data4 = new ArrayList<>();
    private static List<String> data8 = new ArrayList<>();

    static {

        data2.add("充足");
        data2.add("不足");
        data3.add("齐全");
        data3.add("不齐全");
        data4.add("有");
        data4.add("无");
        data8.add("正常");
        data8.add("温度异常");
        data8.add("噪声异常");
        data8.add("出力异常");
        data8.add("振动异常");
    }

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_manage)
    MaterialEditText hydroManage;
    @BindView(R.id.hydro_operation)
    MaterialEditText hydroOperation;
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

    private void initTopbar() {
        topbar.setTitle("机电设备风险");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                Intent intent = new Intent(ElectromechanicalActivity.this, BuildingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        ArrayAdapter<String> hdAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data2);
        ArrayAdapter<String> hdAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data3);
        ArrayAdapter<String> hdAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data4);
        ArrayAdapter<String> hdAdapter8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data8);

        spinnerSetAdapter(hdAdapter2, hydroElectromechanical2);
        spinnerSetAdapter(hdAdapter3, hydroElectromechanical3);
        spinnerSetAdapter(hdAdapter4, hydroElectromechanical4);
        spinnerSetAdapter(hdAdapter8, hydroElectromechanical8);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void setData() {
        int manage = 0;
        int operation = 0;
        String electromechanical = "";

        if (!hydroManage.getText().toString().equals("")){
            manage = Integer.parseInt(hydroManage.getText().toString());
        }else {
            showToastShort("请输入管理人员数量");
            return;
        }

        if (!hydroOperation.getText().toString().equals("")){
            operation = Integer.parseInt(hydroOperation.getText().toString());
        }else {
            showToastShort("请输入运维人员数量");
        }

        if (hydroDisasterLevel.getText().toString().equals("")){
            showToastShort("请输入电站机组及柜体制造年限");
            return;
        }
        if (hydroDisasterUseYear.getText().toString().equals("")){
            showToastShort("请输入设备使用年限");
            return;
        }


        electromechanical += "箭板电站共配备人员" + (manage + operation) + "名，其中管理人员" + manage + "名，运维人员" + operation + "名,人员配置"
                          + hydroElectromechanical2.getSelectedItem().toString() + "。电站自动化程度高，运维制度齐全，管理规范，人员操作熟练，维护、检修记录"
                          + hydroElectromechanical3.getSelectedItem().toString() + "。中控室24小时" + hydroElectromechanical4.getSelectedItem().toString()
                          + "人值班，值班人员能通过控制室内的监视控制屏第一时间发现线路及设备故障。箭板电站机组设备及电气屏柜均于" + hydroDisasterLevel.getText().toString()
                          + "年，前后制造，距今使用约" + hydroDisasterUseYear.getText().toString() + "年。现场查勘期间设备" + hydroDisasterCrew.getText().toString()
                          + "台机组处于生产发电中，运行工况正常。";

        String url = Constants.TEST_SERVICE + "/hydro/uploadElectromechanical";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("electromechanical",electromechanical)
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
                    Intent intent = new Intent(ElectromechanicalActivity.this, BuildingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
