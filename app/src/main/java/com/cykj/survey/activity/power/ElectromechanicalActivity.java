package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    private static List<String> data2 = new ArrayList<>();
    private static List<String> data3 = new ArrayList<>();
    private static List<String> data4 = new ArrayList<>();
    private static List<String> data7 = new ArrayList<>();
    private static List<String> data8 = new ArrayList<>();
    private static List<String> data9 = new ArrayList<>();
    private static List<String> data10 = new ArrayList<>();
    private static List<String> data11 = new ArrayList<>();
    private static List<String> data12 = new ArrayList<>();
    private static List<String> data16 = new ArrayList<>();
    private static List<String> data17 = new ArrayList<>();
    private static List<String> data18 = new ArrayList<>();
    private static List<String> data19 = new ArrayList<>();
    private static List<String> data20 = new ArrayList<>();

    static {

        data2.add("合理（熟练工比例≥70%）");
        data2.add("不合理");

        data3.add("齐全");
        data3.add("不齐全");

        data4.add("是");
        data4.add("否");

        data7.add("有");
        data7.add("无");

        data8.add("有");
        data8.add("无");

        data9.add("正常");
        data9.add("不正常");

        data10.add("整洁（柜体内部清洁，无灰尘杂物等）");
        data10.add("较整洁（有灰尘无杂物等）");
        data10.add("杂乱（柜体内有杂物，灰尘较多）");

        data11.add("有（绝缘层开裂脱落、接头氧化、绝缘层对地电阻＜0.5MΩ）");
        data11.add("无");

        data12.add("及时处理（故障报出后30分钟内有值班人员查看及处理）");
        data12.add("未及时处理");

        data16.add("正常");
        data16.add("振动偏大");

        data17.add("正常");
        data17.add("温度偏高");

        data18.add("有");
        data18.add("无");

        data19.add("能");
        data19.add("不能");

        data20.add("正常");
        data20.add("不正常");
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
    @BindView(R.id.hydro_electromechanical_7)
    Spinner hydroElectromechanical7;
    @BindView(R.id.hydro_electromechanical_8)
    Spinner hydroElectromechanical8;
    @BindView(R.id.hydro_electromechanical_9)
    Spinner hydroElectromechanical9;
    @BindView(R.id.hydro_electromechanical_10)
    Spinner hydroElectromechanical10;
    @BindView(R.id.hydro_electromechanical_11)
    Spinner hydroElectromechanical11;
    @BindView(R.id.hydro_electromechanical_12)
    Spinner hydroElectromechanical12;
    @BindView(R.id.hydro_disaster_used_year)
    MaterialEditText hydroDisasterUsedYear;
    @BindView(R.id.hydro_disaster_a)
    MaterialEditText hydroDisasterA;
    @BindView(R.id.hydro_disaster_b)
    MaterialEditText hydroDisasterB;
    @BindView(R.id.hydro_electromechanical_16)
    Spinner hydroElectromechanical16;
    @BindView(R.id.hydro_electromechanical_17)
    Spinner hydroElectromechanical17;
    @BindView(R.id.hydro_electromechanical_18)
    Spinner hydroElectromechanical18;
    @BindView(R.id.hydro_electromechanical_19)
    Spinner hydroElectromechanical19;
    @BindView(R.id.hydro_electromechanical_20)
    Spinner hydroElectromechanical20;
    @BindView(R.id.hydro_electromechanical_7_edit)
    MaterialEditText hydroElectromechanical7Edit;
    @BindView(R.id.hydro_electromechanical_8_edit)
    MaterialEditText hydroElectromechanical8Edit;
    @BindView(R.id.hydro_electromechanical_9_edit)
    MaterialEditText hydroElectromechanical9Edit;


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

        hydroElectromechanical7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (data7.get(i)){
                    case "有":
                        hydroElectromechanical7Edit.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        hydroElectromechanical7Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hydroElectromechanical8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (data8.get(i)){
                    case "有":
                        hydroElectromechanical8Edit.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        hydroElectromechanical8Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hydroElectromechanical9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (data9.get(i)){
                    case "正常":
                        hydroElectromechanical9Edit.setVisibility(View.GONE);
                        break;
                    case "不正常":
                        hydroElectromechanical9Edit.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        ArrayAdapter<String> hdAdapter7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data7);
        ArrayAdapter<String> hdAdapter8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data8);
        ArrayAdapter<String> hdAdapter9 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data9);
        ArrayAdapter<String> hdAdapter10 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data10);
        ArrayAdapter<String> hdAdapter11 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data11);
        ArrayAdapter<String> hdAdapter12 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data12);
        ArrayAdapter<String> hdAdapter16 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data16);
        ArrayAdapter<String> hdAdapter17 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data17);
        ArrayAdapter<String> hdAdapter18 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data18);
        ArrayAdapter<String> hdAdapter19 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data19);
        ArrayAdapter<String> hdAdapter20 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data20);

        spinnerSetAdapter(hdAdapter2, hydroElectromechanical2);
        spinnerSetAdapter(hdAdapter3, hydroElectromechanical3);
        spinnerSetAdapter(hdAdapter4, hydroElectromechanical4);
        spinnerSetAdapter(hdAdapter7, hydroElectromechanical7);
        spinnerSetAdapter(hdAdapter8, hydroElectromechanical8);
        spinnerSetAdapter(hdAdapter9, hydroElectromechanical9);
        spinnerSetAdapter(hdAdapter10, hydroElectromechanical10);
        spinnerSetAdapter(hdAdapter11, hydroElectromechanical11);
        spinnerSetAdapter(hdAdapter12, hydroElectromechanical12);
        spinnerSetAdapter(hdAdapter16, hydroElectromechanical16);
        spinnerSetAdapter(hdAdapter17, hydroElectromechanical17);
        spinnerSetAdapter(hdAdapter18, hydroElectromechanical18);
        spinnerSetAdapter(hdAdapter19, hydroElectromechanical19);
        spinnerSetAdapter(hdAdapter20, hydroElectromechanical20);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void setData() {
        Intent intent = new Intent(ElectromechanicalActivity.this, BuildingActivity.class);
        startActivity(intent);
        finish();

//        int manage = 0;
//        int operation = 0;
//        String electromechanical = "";
//
//        if (!hydroManage.getText().toString().equals("")) {
//            manage = Integer.parseInt(hydroManage.getText().toString());
//        } else {
//            showToastShort("请输入管理人员数量");
//            return;
//        }
//
//        if (!hydroOperation.getText().toString().equals("")) {
//            operation = Integer.parseInt(hydroOperation.getText().toString());
//        } else {
//            showToastShort("请输入运维人员数量");
//        }
//
//        if (hydroDisasterLevel.getText().toString().equals("")) {
//            showToastShort("请输入电站机组及柜体制造年限");
//            return;
//        }
//        if (hydroDisasterUseYear.getText().toString().equals("")) {
//            showToastShort("请输入设备使用年限");
//            return;
//        }
//
//
//        electromechanical += "箭板电站共配备人员" + (manage + operation) + "名，其中管理人员" + manage + "名，运维人员" + operation + "名,人员配置"
//                + hydroElectromechanical2.getSelectedItem().toString() + "。电站自动化程度高，运维制度齐全，管理规范，人员操作熟练，维护、检修记录"
//                + hydroElectromechanical3.getSelectedItem().toString() + "。中控室24小时" + hydroElectromechanical4.getSelectedItem().toString()
//                + "人值班，值班人员能通过控制室内的监视控制屏第一时间发现线路及设备故障。箭板电站机组设备及电气屏柜均于" + hydroDisasterLevel.getText().toString()
//                + "年，前后制造，距今使用约" + hydroDisasterUseYear.getText().toString() + "年。现场查勘期间设备" + hydroDisasterCrew.getText().toString()
//                + "台机组处于生产发电中，运行工况正常。";
//
//        String url = Constants.TEST_SERVICE + "/hydro/uploadElectromechanical";
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = new FormBody.Builder()
//                .add("electromechanical", electromechanical)
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
//                showToastShort("网络连接失败，请检查网络连接");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String resultStr = response.body().string();
//                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
//                if (result.getCode() == 0) {
//                    Intent intent = new Intent(ElectromechanicalActivity.this, BuildingActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
    }

}
