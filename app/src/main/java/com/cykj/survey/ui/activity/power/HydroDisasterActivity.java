package com.cykj.survey.ui.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class HydroDisasterActivity extends BaseFragmentActivity {


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
    private static List<String> data18 = new ArrayList<>();
    private static List<String> data20 = new ArrayList<>();
    private static List<String> data_add1 = new ArrayList<>();
    private static List<String> data_add2 = new ArrayList<>();

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

        data15.add("有");
        data15.add("无");

        data16.add("整齐（线路按要求走线槽，设有防火封堵）");
        data16.add("较整齐（动力电缆和大部分控制电缆走线槽，有防火封堵）");
        data16.add("不整齐（线路布置杂乱，动力电缆和控制电缆混合布线）");

        data17.add("有");
        data17.add("无");

        data18.add("厂房外部");
        data18.add("厂房内部");

        data20.add("正常");
        data20.add("过高");

        data_add1.add("充足（消防水泵、灭火器按25㎡设置一组）");
        data_add1.add("不足");

        data_add2.add("按时点检（有点检记录）");
        data_add2.add("未按时点检（无点检记录）");
    }

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_disaster_main)
    MaterialEditText hydroDisasterMain;
    @BindView(R.id.hydro_disaster_vice)
    MaterialEditText hydroDisasterVice;
    @BindView(R.id.hydro_disaster_2)
    Spinner hydroDisaster2;
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
    @BindView(R.id.hydro_disaster_18)
    Spinner hydroDisaster18;
    @BindView(R.id.hydro_distance)
    MaterialEditText hydroDistance;
    @BindView(R.id.hydro_disaster_15)
    Spinner hydroDisaster15;
    @BindView(R.id.hydro_disaster_20)
    Spinner hydroDisaster20;
    @BindView(R.id.hydro_disaster_16)
    Spinner hydroDisaster16;
    @BindView(R.id.hydro_disaster_17)
    Spinner hydroDisaster17;
    @BindView(R.id.hydro_disaster_add_1)
    Spinner hydroDisasterAdd1;
    @BindView(R.id.hydro_disaster_add_2)
    Spinner hydroDisasterAdd2;


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

        hydroDisaster18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (data18.get(i)){
                    case "厂房外部":
                        hydroDistance.setVisibility(View.VISIBLE);
                        break;
                    case "厂房内部":
                        hydroDistance.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        ArrayAdapter<String> hdAdapter18 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data18);
        ArrayAdapter<String> hdAdapter20 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data20);
        ArrayAdapter<String> hdAdapter_add1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_add1);
        ArrayAdapter<String> hdAdapter_add2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_add2);

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
        spinnerSetAdapter(hdAdapter18, hydroDisaster18);
        spinnerSetAdapter(hdAdapter20, hydroDisaster20);
        spinnerSetAdapter(hdAdapter_add1, hydroDisasterAdd1);
        spinnerSetAdapter(hdAdapter_add2, hydroDisasterAdd2);

    }

    private void initTopbar() {
        topbar.setTitle("火灾风险");
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

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void setData() {
        String disaster = "";
        if (!hydroDisasterMain.getText().toString().equals("")) {
            disaster += "箭板电站有" + hydroDisasterMain.getText().toString() + "座主厂房，";
        } else {
            showToastShort("请输入主厂房数量");
            return;
        }
        if (!hydroDisasterVice.getText().toString().equals("")) {
            disaster += hydroDisasterVice.getText().toString() + "座副厂房，厂房主体为";
        } else {
            showToastShort("请输入副厂房数量");
            return;
        }
        disaster += hydroDisaster2.getSelectedItem().toString() + "，根据对厂房结构材料进行观测，初步判定为";
        switch (hydroDisaster2.getSelectedItem().toString()) {
            case "钢混结构":
                disaster += "1级耐火等级建筑。水电站厂房火灾危险性类别有丙、丁、戊类，1级建筑耐火等级满足《水利水电工程设计防火规范》（SDJ 278－1990）要求。";
                break;
            case "砖混结构":
                disaster += "2级耐火等级建筑。水电站厂房火灾危险性类别有丙、丁、戊类，2级建筑耐火等级满足《水利水电工程设计防火规范》（SDJ 278－1990）要求。";
                break;
            case "砖木结构":
                disaster += "3-4级耐火等级建筑。水电站厂房火灾危险性类别有丙、丁、戊类，3-4级建筑耐火等级满足《水利水电工程设计防火规范》（SDJ 278－1990）要求。";
                break;
            case "彩钢结构":
                disaster += "3-4级耐火等级建筑。水电站厂房火灾危险性类别有丙、丁、戊类，3-4级建筑耐火等级满足《水利水电工程设计防火规范》（SDJ 278－1990）要求。";
                break;
            default:
                break;
        }

        disaster += "厂房区域" + hydroDisaster4.getSelectedItem().toString() + "禁烟火标志，" + hydroDisaster5.getSelectedItem().toString() + "自动消防报警装置。其内部物资等放置"
                + hydroDisaster6.getSelectedItem().toString() + "，消防间距" + hydroDisaster7.getSelectedItem().toString() + "，消防器材配置充足且按期点检，透平油等易燃物"
                + hydroDisaster8.getSelectedItem().toString() + "保存，发生火灾风险小；重点区域安装视频监控，中控室24小时有人值班，能及时发现并扑灭初期火灾。"
                + "电站升压站内安装";

        if (!hydroTransformerNumber.getText().toString().equals("")) {
            disaster += hydroTransformerNumber.getText().toString() + "台油浸式主变压器，型号分别为";
        } else {
            showToastShort("请输入变压器台数");
            return;
        }
        if (!hydroTransformerVersion.getText().toString().equals("")) {
            disaster += hydroTransformerVersion.getText().toString() + "；变压器安装在" + hydroDisaster18.getSelectedItem().toString() + "，与厂房距离超过";
        } else {
            showToastShort("请输入变压器型号");
            return;
        }
        if (!hydroDistance.getText().toString().equals("")) {
            disaster += hydroDistance.getText().toString() + "米，间设围墙，墙面靠近变压器侧无门窗。满足《3-110KV高压配电装置设计规范》（GB 50060-2008）要求。变压器表面"
                    + hydroDisaster15.getSelectedItem().toString() + "明显发黄点，进出线路及母排温度" + hydroDisaster20.getSelectedItem().toString() + "，短期变压器发生火灾风险";
        }
        switch (hydroDisaster20.getSelectedItem().toString()) {
            case "正常":
                disaster += "较小，对厂房影响小";
                break;
            case "过高":
                disaster += "较高，对厂房影响大";
                break;
            default:
                break;
        }

        disaster += "厂区内线路布置" + hydroDisaster16.getSelectedItem().toString() + "，查勘期间电站无机组运行，大部分线路处于断开状态。现场对线路表面进行观察，线路" + hydroDisaster17.getSelectedItem().toString()
                + "明显老化痕迹，升压站场地内出线穿管，";

        if (hydroDisaster16.getSelectedItem().toString().equals("不整齐（线路布置杂乱，动力电缆和控制电缆混合布线）") & hydroDisaster17.getSelectedItem().toString().equals("有")) {
            disaster += "线路短期风险较高";
        } else {
            disaster += "线路短期风险较小";
        }

        String url = Constants.TEST_SERVICE + "/hydro/uploadDisaster";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("disaster", disaster)
                .add("id", Constants.HYDRO_ID.toString())
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
                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                if (result.getCode() == 0) {
                    Intent intent = new Intent(HydroDisasterActivity.this, HydroDisasterPhotoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
