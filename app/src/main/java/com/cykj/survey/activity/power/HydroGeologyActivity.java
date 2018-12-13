package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.bean.HydroGeologyOption;
import com.cykj.survey.model.HydroGeology;
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

public class HydroGeologyActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_geology_1_1)
    Spinner hydroGeology11;
    @BindView(R.id.hydro_geology_temperature)
    EditText hydroGeologyTemperature;
    @BindView(R.id.hydro_geology_precipitation)
    EditText hydroGeologyPrecipitation;
    @BindView(R.id.hydro_geology_1_4_start)
    Spinner hydroGeology14Start;
    @BindView(R.id.hydro_geology_1_4_end)
    Spinner hydroGeology14End;
    @BindView(R.id.hydro_geology_1_5)
    Spinner hydroGeology15;
    @BindView(R.id.hydro_geology_1_6)
    Spinner hydroGeology16;
    @BindView(R.id.hydro_geology_1_7)
    Spinner hydroGeology17;
    @BindView(R.id.hydro_geology_1_8_start)
    Spinner hydroGeology18Start;
    @BindView(R.id.hydro_geology_1_8_end)
    Spinner hydroGeology18End;
    @BindView(R.id.hydro_geology_1_9)
    Spinner hydroGeology19;
    @BindView(R.id.hydro_geology_1_10)
    Spinner hydroGeology110;
    @BindView(R.id.hydro_geology_1_11)
    Spinner hydroGeology111;
    @BindView(R.id.hydro_geology_1_12)
    Spinner hydroGeology112;
    @BindView(R.id.hydro_geology_2_1)
    Spinner hydroGeology21;
    @BindView(R.id.hydro_geology_2_2)
    Spinner hydroGeology22;
    @BindView(R.id.hydro_geology_2_3)
    Spinner hydroGeology23;
    @BindView(R.id.hydro_geology_2_4)
    Spinner hydroGeology24;
    @BindView(R.id.hydro_geology_2_5_start)
    Spinner hydroGeology25Start;
    @BindView(R.id.hydro_geology_2_5_end)
    Spinner hydroGeology25End;
    @BindView(R.id.hydro_geology_2_6)
    Spinner hydroGeology26;
    @BindView(R.id.hydro_geology_2_7)
    Spinner hydroGeology27;
    @BindView(R.id.hydro_geology_3_1)
    EditText hydroGeology31;
    @BindView(R.id.hydro_geology_3_2)
    EditText hydroGeology32;
    @BindView(R.id.hydro_geology_3_3)
    Spinner hydroGeology33;
    @BindView(R.id.hydro_geology_3_4)
    Spinner hydroGeology34;
    @BindView(R.id.hydro_geology_3_5_title)
    TextView hydroGeology35Title;
    @BindView(R.id.hydro_geology_3_5)
    Spinner hydroGeology35;
    @BindView(R.id.hydro_geology_3_6)
    Spinner hydroGeology36;
    @BindView(R.id.hydro_geology_4_1)
    EditText hydroGeology41;
    @BindView(R.id.hydro_geology_4_2)
    EditText hydroGeology42;
    @BindView(R.id.hydro_geology_4_3)
    Spinner hydroGeology43;
    @BindView(R.id.hydro_geology_4_4)
    Spinner hydroGeology44;
    @BindView(R.id.hydro_geology_4_5)
    Spinner hydroGeology45;
    @BindView(R.id.hydro_geology_4_6)
    Spinner hydroGeology46;
    @BindView(R.id.hydro_geology_4_7)
    EditText hydroGeology47;
    @BindView(R.id.hydro_geology_4_8)
    Spinner hydroGeology48;
    @BindView(R.id.hydro_geology_4_9)
    Spinner hydroGeology49;
    @BindView(R.id.hydro_geology_5_1)
    Spinner hydroGeology51;
    @BindView(R.id.hydro_geology_5_2)
    Spinner hydroGeology52;
    @BindView(R.id.hydro_geology_5_3)
    Spinner hydroGeology53;
    @BindView(R.id.hydro_geology_5_4)
    Spinner hydroGeology54;
    @BindView(R.id.hydro_geology_5_5)
    Spinner hydroGeology55;
    @BindView(R.id.hydro_geology_5_6)
    Spinner hydroGeology56;
    @BindView(R.id.hydro_geology_5_7)
    Spinner hydroGeology57;
    @BindView(R.id.hydro_geology_5_8)
    Spinner hydroGeology58;
    @BindView(R.id.hydro_geology_5_9)
    Spinner hydroGeology59;
    @BindView(R.id.hydro_geology_5_10_title)
    TextView hydroGeology510Title;
    @BindView(R.id.hydro_geology_5_10)
    Spinner hydroGeology510;
    @BindView(R.id.hydro_geology_5_11)
    Spinner hydroGeology511;
    @BindView(R.id.hydro_geology_5_12)
    Spinner hydroGeology512;
    @BindView(R.id.hydro_geology_5_13)
    Spinner hydroGeology513;
    @BindView(R.id.hydro_geology_5_14)
    Spinner hydroGeology514;
    @BindView(R.id.hydro_geology_5_15)
    Spinner hydroGeology515;
    @BindView(R.id.hydro_geology_2_7_title)
    TextView hydroGeology27Title;
    @BindView(R.id.hydro_geology_4_8_edit)
    EditText hydroGeology48Edit;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_grology);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initTopbar() {
        topbar.setTitle("地质信息");
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

    private void initView() {
        initAdapter();

        hydroGeology26.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (HydroGeologyOption.data2_6.get(i)) {
                    case "存在":
                        hydroGeology27.setVisibility(View.VISIBLE);
                        hydroGeology27Title.setVisibility(View.VISIBLE);
                        break;
                    case "不存在":
                        hydroGeology27.setVisibility(View.GONE);
                        hydroGeology27Title.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        hydroGeology34.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (HydroGeologyOption.data3_4.get(i)) {
                    case "有":
                        hydroGeology35.setVisibility(View.VISIBLE);
                        hydroGeology35Title.setVisibility(View.VISIBLE);
                        break;
                    case "无":
                        hydroGeology35.setVisibility(View.GONE);
                        hydroGeology35Title.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hydroGeology48.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (HydroGeologyOption.data4_8.get(i)) {
                    case "存在损坏":
                        hydroGeology48Edit.setVisibility(View.VISIBLE);
                        break;
                    case "正常":
                        hydroGeology48Edit.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initAdapter() {
        List<String> g1_1 = new ArrayList<>();
        for (String str : HydroGeologyOption.data1_1.keySet()) {
            g1_1.add(str);
        }

        ArrayAdapter<String> pgAdapter1_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, g1_1);
        ArrayAdapter<String> pgAdapter1_4start = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter1_4end = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter1_5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_5);
        ArrayAdapter<String> pgAdapter1_6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_6);
        ArrayAdapter<String> pgAdapter1_7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_7);
        ArrayAdapter<String> pgAdapter1_8start = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter1_8end = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter1_9 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_9);
        ArrayAdapter<String> pgAdapter1_10 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_10);
        ArrayAdapter<String> pgAdapter1_11 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_11);
        ArrayAdapter<String> pgAdapter1_12 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data1_12);

        ArrayAdapter<String> pgAdapter2_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_1);
        ArrayAdapter<String> pgAdapter2_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_2);
        ArrayAdapter<String> pgAdapter2_3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_3);
        ArrayAdapter<String> pgAdapter2_4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_4);
        ArrayAdapter<String> pgAdapter2_5start = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter2_5end = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.mounthList);
        ArrayAdapter<String> pgAdapter2_6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_6);
        ArrayAdapter<String> pgAdapter2_7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data2_7);

        ArrayAdapter<String> pgAdapter3_3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data3_3);
        ArrayAdapter<String> pgAdapter3_4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data3_4);
        ArrayAdapter<String> pgAdapter3_5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data3_5);
        ArrayAdapter<String> pgAdapter3_6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data3_6);

        ArrayAdapter<String> pgAdapter4_3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_3);
        ArrayAdapter<String> pgAdapter4_4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_4);
        ArrayAdapter<String> pgAdapter4_5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_5);
        ArrayAdapter<String> pgAdapter4_6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_6);
        ArrayAdapter<String> pgAdapter4_8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_8);
        ArrayAdapter<String> pgAdapter4_9 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data4_9);

        ArrayAdapter<String> pgAdapter5_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_1);
        ArrayAdapter<String> pgAdapter5_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_2);
        ArrayAdapter<String> pgAdapter5_3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_3);
        ArrayAdapter<String> pgAdapter5_4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_4);
        ArrayAdapter<String> pgAdapter5_5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_5);
        ArrayAdapter<String> pgAdapter5_6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_6);
        ArrayAdapter<String> pgAdapter5_7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_7);
        ArrayAdapter<String> pgAdapter5_8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_8);
        ArrayAdapter<String> pgAdapter5_9 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_9);
        ArrayAdapter<String> pgAdapter5_10 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_10);
        ArrayAdapter<String> pgAdapter5_11 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_11);
        ArrayAdapter<String> pgAdapter5_12 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_12);
        ArrayAdapter<String> pgAdapter5_13 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_13);
        ArrayAdapter<String> pgAdapter5_14 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_14);
        ArrayAdapter<String> pgAdapter5_15 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HydroGeologyOption.data5_15);

        spinnerSetAdapter(pgAdapter1_1, hydroGeology11);
        spinnerSetAdapter(pgAdapter1_4start, hydroGeology14Start);
        spinnerSetAdapter(pgAdapter1_4end, hydroGeology14End);
        spinnerSetAdapter(pgAdapter1_5, hydroGeology15);
        spinnerSetAdapter(pgAdapter1_6, hydroGeology16);
        spinnerSetAdapter(pgAdapter1_7, hydroGeology17);
        spinnerSetAdapter(pgAdapter1_8start, hydroGeology18Start);
        spinnerSetAdapter(pgAdapter1_8end, hydroGeology18End);
        spinnerSetAdapter(pgAdapter1_9, hydroGeology19);
        spinnerSetAdapter(pgAdapter1_10, hydroGeology110);
        spinnerSetAdapter(pgAdapter1_11, hydroGeology111);
        spinnerSetAdapter(pgAdapter1_12, hydroGeology112);

        spinnerSetAdapter(pgAdapter2_1, hydroGeology21);
        spinnerSetAdapter(pgAdapter2_2, hydroGeology22);
        spinnerSetAdapter(pgAdapter2_3, hydroGeology23);
        spinnerSetAdapter(pgAdapter2_4, hydroGeology24);
        spinnerSetAdapter(pgAdapter2_5start, hydroGeology25Start);
        spinnerSetAdapter(pgAdapter2_5end, hydroGeology25End);
        spinnerSetAdapter(pgAdapter2_6, hydroGeology26);
        spinnerSetAdapter(pgAdapter2_7, hydroGeology27);

        spinnerSetAdapter(pgAdapter3_3, hydroGeology33);
        spinnerSetAdapter(pgAdapter3_4, hydroGeology34);
        spinnerSetAdapter(pgAdapter3_5, hydroGeology35);
        spinnerSetAdapter(pgAdapter3_6, hydroGeology36);

        spinnerSetAdapter(pgAdapter4_3, hydroGeology43);
        spinnerSetAdapter(pgAdapter4_4, hydroGeology44);
        spinnerSetAdapter(pgAdapter4_5, hydroGeology45);
        spinnerSetAdapter(pgAdapter4_6, hydroGeology46);
        spinnerSetAdapter(pgAdapter4_8, hydroGeology48);
        spinnerSetAdapter(pgAdapter4_9, hydroGeology49);

        spinnerSetAdapter(pgAdapter5_1, hydroGeology51);
        spinnerSetAdapter(pgAdapter5_2, hydroGeology52);
        spinnerSetAdapter(pgAdapter5_3, hydroGeology53);
        spinnerSetAdapter(pgAdapter5_4, hydroGeology54);
        spinnerSetAdapter(pgAdapter5_5, hydroGeology55);
        spinnerSetAdapter(pgAdapter5_6, hydroGeology56);
        spinnerSetAdapter(pgAdapter5_7, hydroGeology57);
        spinnerSetAdapter(pgAdapter5_8, hydroGeology58);
        spinnerSetAdapter(pgAdapter5_9, hydroGeology59);
        spinnerSetAdapter(pgAdapter5_10, hydroGeology510);
        spinnerSetAdapter(pgAdapter5_11, hydroGeology511);
        spinnerSetAdapter(pgAdapter5_12, hydroGeology512);
        spinnerSetAdapter(pgAdapter5_13, hydroGeology513);
        spinnerSetAdapter(pgAdapter5_14, hydroGeology514);
        spinnerSetAdapter(pgAdapter5_15, hydroGeology515);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }


    private void setData() {

        Intent intent = new Intent(HydroGeologyActivity.this, HydroImageActivity.class);
        startActivity(intent);
        finish();
        String rainStorm = "";
        rainStorm += "，所在流域属" + hydroGeology11.getSelectedItem().toString() + "，具有" + HydroGeologyOption.data1_1.get(hydroGeology11.getSelectedItem().toString()) + "等特点。"
                + "该地多年平均气温" + hydroGeologyTemperature.getText().toString() + " ℃，多年平均降水量" + hydroGeologyPrecipitation.getText().toString() + " mm,，主要集中在 " + hydroGeology14Start.getSelectedItem() + "~" + hydroGeology14End.getSelectedItem().toString() + ",占全年降水量的75%。"
                + "该地区气候垂直变化" + hydroGeology16.getSelectedItem().toString() + "，夏季降水充足，" + hydroGeology15.getSelectedItem().toString() + "干旱、暴雨、大风、绵雨等灾害性天气。"
                + "该地的降水年内分配极不均匀，主要集中在5~10月，暴雨多集中在" + hydroGeology18Start.getSelectedItem().toString() + "~" + hydroGeology18End.getSelectedItem().toString() + "，夏季常常出现短时强降雨天气，易造成短时洪峰。其次该流域内，植被覆盖情况" + hydroGeology17.getSelectedItem().toString()
                + "，对地表的坡面流水有一定的调蓄和涵养能力，暴雨季节库区及河道内的漂浮物（枯枝、落叶、杂物）" + hydroGeology19.getSelectedItem().toString() + "，对电站设施存在" + hydroGeology110.getSelectedItem().toString()
                + "的影响。厂区排水沟" + hydroGeology11.getSelectedItem().toString() + "排水系统设施" + hydroGeology112.getSelectedItem().toString() + "，" + "满足暴雨季节应急排水需求。"
                + "综合而言，该电站受暴雨的影响较小。";

        String flood = "";
        flood += "该流域内多山地及丘陵，支流发育，汇水面积大，该电站的库容量" + hydroGeology21.getSelectedItem().toString() + "，拦河坝设计洪水二十年一遇，校核洪水一百年一遇，厂房设计洪水二十年一遇，校核洪水五十年一遇。流域上游森林植被覆盖率高，生态环境"
                + hydroGeology22.getSelectedItem().toString() + "。流域径流主要由" + hydroGeology23.getSelectedItem().toString() + "补给形成，径流年内分配" + hydroGeology24.getSelectedItem().toString() + "，径流主要集中在汛期" + hydroGeology25Start.getSelectedItem().toString() + "~" + hydroGeology25End.getSelectedItem().toString() + "，径流量约占全年的78.2%，8月汛期平均流量可达41.3m3/s。箭板电站龙溪河上游"
                + hydroGeology26.getSelectedItem().toString() + "其他的电站及大坝，对该流域的洪峰";
        if (hydroGeology26.getSelectedItem().equals("存在")) {
            flood += "能够起到一定的调节作用，";
        } else {
            flood += "缺少一定的调节作用，";
        }
        flood += "箭板电站也具备一定的泄洪调蓄能力。因此，该电站的洪水风险" + hydroGeology27.getSelectedItem().toString() + "。";

        String low = "";
        low += "该地区全年平均最低气温"+hydroGeology31.getText().toString()+"，多年极端低温"+hydroGeology32.getText().toString()+"，"+hydroGeology33.getSelectedItem().toString()+"出现雨雪、冰冻等低温天气，输电线路"+hydroGeology34.getSelectedItem().toString()+"历史覆冰记录，" +
                "覆冰程度"+hydroGeology35.getSelectedItem().toString()+"；该地河流"+hydroGeology36.getSelectedItem().toString()+"凌汛现象，其他低温灾害对电站设施及大坝的影响较小。";

        String lightning = "";
        lightning += "箭板电站距离最近的县城约"+hydroGeology41.getText().toString()+"公里，根据中国气象数据，沐川县年平均雷暴日为"+hydroGeology42.getText().toString()+"，属于"+hydroGeology43.getSelectedItem().toString()+"。电站地处河谷地带，因地形、地势原因，气流抬升作用明显，雷雨天气集中在"+hydroGeology44.getSelectedItem().toString()+
                "，且云层高度"+hydroGeology45.getSelectedItem().toString()+"，因此雷雨云对地闪击的频率比平原或丘陵区"+hydroGeology46.getSelectedItem().toString()+"。" +
                "电站厂区装设有避雷针"+hydroGeology47.getText().toString()+"根，避雷针接闪器、引下线、接地体状况"+hydroGeology48.getSelectedItem().toString()+"，绝缘电阻值"+hydroGeology49.getSelectedItem().toString() +
                "该电站雷击风险较小。";

        String geology = "";
        geology += "板电站即位于龙溪河河谷冲积地区，该地区的岩性主要为" + hydroGeology51.getSelectedItem().toString() + "，电站周边地形坡地" + hydroGeology512.getSelectedItem().toString() + "。电站区域内的崩塌、滑坡、泥石流等地质灾害" + hydroGeology52.getSelectedItem().toString()
                + "。其次，该地区距离地震带" + hydroGeology53.getSelectedItem().toString() + "，地震发生强度及规模均" + hydroGeology54.getSelectedItem().toString() + "。该地区" + hydroGeology55.getSelectedItem().toString() + "区域性断层通过，小型活动断裂隙构造"
                + hydroGeology56.getSelectedItem().toString() + "，该地区的区域稳定性" + hydroGeology57.getSelectedItem().toString() + "，" + hydroGeology58.getSelectedItem().toString() + "发生较强破坏力地震的可能性，电站坝址区的地基稳定" + hydroGeology59.getSelectedItem().toString()
                + "，电站库区蓄水并" + hydroGeology510.getSelectedItem().toString() + "诱发过库区地震。综上所述，该电站区域发生地震灾害的风险" + hydroGeology511.getSelectedItem().toString() + "，发生崩塌、滑坡、泥石流等地质灾害的的风险" + hydroGeology513.getSelectedItem().toString() + "。";


        HydroGeology hydroGeology = new HydroGeology();
        hydroGeology.setRainStorm(rainStorm);
        hydroGeology.setFlood(flood);
        hydroGeology.setLow(low);
        hydroGeology.setLightning(lightning);
        hydroGeology.setGeology(geology);
        hydroGeology.setRainStorm(rainStorm);

        hydroGeology.setRainStorm(rainStorm);
        hydroGeology.setFlood(flood);
        hydroGeology.setLow(low);

        String json = JSONObject.toJSONString(hydroGeology);
        String url = Constants.TEST_SERVICE + "/hydro/uploadGeology";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json", json)
                .add("id", Constants.HYDRO_ID.toString())
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("数据上传失败，请检查网络连接是否畅通");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                if (result.getCode() == 0) {
                    Intent intent = new Intent(HydroGeologyActivity.this, HydroImageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
