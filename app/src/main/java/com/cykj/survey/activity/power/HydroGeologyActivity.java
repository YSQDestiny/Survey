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
import com.cykj.survey.model.HydroGeology;
import com.cykj.survey.model.ResultModel;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.hydro_geology_1_2)
    Spinner hydroGeology12;
    @BindView(R.id.hydro_geology_1_3)
    Spinner hydroGeology13;
    @BindView(R.id.hydro_geology_1_4)
    Spinner hydroGeology14;
    @BindView(R.id.hydro_geology_1_5)
    Spinner hydroGeology15;
    @BindView(R.id.hydro_geology_1_6)
    Spinner hydroGeology16;
    @BindView(R.id.hydro_geology_1_7)
    Spinner hydroGeology17;
    @BindView(R.id.hydro_geology_1_8)
    Spinner hydroGeology18;
    @BindView(R.id.hydro_geology_2_1)
    Spinner hydroGeology21;
    @BindView(R.id.hydro_geology_2_2)
    Spinner hydroGeology22;
    @BindView(R.id.hydro_geology_2_3)
    Spinner hydroGeology23;
    @BindView(R.id.hydro_geology_2_4)
    Spinner hydroGeology24;
    @BindView(R.id.hydro_geology_2_5)
    Spinner hydroGeology25;
    @BindView(R.id.hydro_geology_2_6)
    Spinner hydroGeology26;
    @BindView(R.id.hydro_geology_2_7)
    Spinner hydroGeology27;
    @BindView(R.id.hydro_geology_2_8)
    Spinner hydroGeology28;
    @BindView(R.id.hydro_geology_3_1)
    Spinner hydroGeology31;
    @BindView(R.id.hydro_geology_3_2)
    Spinner hydroGeology32;
    @BindView(R.id.hydro_geology_3_3)
    Spinner hydroGeology33;
    @BindView(R.id.hydro_geology_3_4)
    Spinner hydroGeology34;
    @BindView(R.id.hydro_geology_4_1)
    Spinner hydroGeology41;
    @BindView(R.id.hydro_geology_4_2)
    Spinner hydroGeology42;
    @BindView(R.id.hydro_geology_4_3)
    Spinner hydroGeology43;
    @BindView(R.id.hydro_geology_4_4)
    Spinner hydroGeology44;
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
    @BindView(R.id.hydro_geology_5_10)
    Spinner hydroGeology510;
    @BindView(R.id.hydro_geology_5_11)
    Spinner hydroGeology511;
    @BindView(R.id.hydro_geology_5_12)
    Spinner hydroGeology512;
    @BindView(R.id.hydro_geology_5_13)
    Spinner hydroGeology513;


    private static Map<String,String> data1_1 = new HashMap<>();
    private static List<String> data1_2 = new ArrayList<>();
    private static List<String> data1_3 = new ArrayList<>();
    private static List<String> data1_4 = new ArrayList<>();
    private static List<String> data1_5 = new ArrayList<>();
    private static List<String> data1_6 = new ArrayList<>();
    private static List<String> data1_7 = new ArrayList<>();
    private static List<String> data1_8 = new ArrayList<>();

    private static List<String> data2_1 = new ArrayList<>();
    private static List<String> data2_2 = new ArrayList<>();
    private static List<String> data2_3 = new ArrayList<>();
    private static List<String> data2_4 = new ArrayList<>();
    private static List<String> data2_5 = new ArrayList<>();
    private static List<String> data2_6 = new ArrayList<>();
    private static List<String> data2_7 = new ArrayList<>();
    private static List<String> data2_8 = new ArrayList<>();

    private static List<String> data3_1 = new ArrayList<>();
    private static List<String> data3_2 = new ArrayList<>();
    private static List<String> data3_3 = new ArrayList<>();
    private static List<String> data3_4 = new ArrayList<>();

    private static List<String> data4_1 = new ArrayList<>();
    private static List<String> data4_2 = new ArrayList<>();
    private static List<String> data4_3 = new ArrayList<>();
    private static List<String> data4_4 = new ArrayList<>();

    private static List<String> data5_1 = new ArrayList<>();
    private static List<String> data5_2 = new ArrayList<>();
    private static List<String> data5_3 = new ArrayList<>();
    private static List<String> data5_4 = new ArrayList<>();
    private static List<String> data5_5 = new ArrayList<>();
    private static List<String> data5_6 = new ArrayList<>();
    private static List<String> data5_7 = new ArrayList<>();
    private static List<String> data5_8 = new ArrayList<>();
    private static List<String> data5_9 = new ArrayList<>();
    private static List<String> data5_10 = new ArrayList<>();
    private static List<String> data5_11 = new ArrayList<>();
    private static List<String> data5_12 = new ArrayList<>();
    private static List<String> data5_13 = new ArrayList<>();

    static {
        data1_1.put("热带季风","终年高温，存在明显的雨季、旱季，降水集中在雨季，且降雨量大，季风气候显著，多热带气旋");
        data1_1.put("亚热带季风","夏季高温多雨，冬季温和少雨，四季分明，降水丰沛，冬夏干湿差别不大");
        data1_1.put("温带季风","夏季高温多雨，冬季寒冷干燥，季风性显著，夏秋季节易受热带气旋影响");
        data1_1.put("高原山地","日照时间长，太阳辐射强，空气稀薄，干燥少云，早晚寒凉晌午热，整体气温较低，局部多大风");
        data1_1.put("温带大陆性","冬季寒冷干燥，夏季炎热湿润，气温年较差、日较差均大，降雨地区差异明显");
        data1_1.put("热带雨林","全年高温多雨，太阳辐射年变化较小，局部地区多热带气旋");
        data1_2.add("存在");
        data1_2.add("不存在");
        data1_3.add("明显");
        data1_3.add("不明显");
        data1_4.add("较好");
        data1_4.add("一般");
        data1_4.add("较差");
        data1_6.add("较多");
        data1_6.add("较少");
        data1_7.add("较大");
        data1_7.add("一般");
        data1_7.add("较小");
        data1_7.add("无");
        data1_8.add("较大");
        data1_8.add("一般");
        data1_8.add("较小");
        data1_8.add("无");

        data2_1.add("较大（≥0.1亿m³）");
        data2_1.add("较小（＜0.1亿m³）");
        data2_2.add("较好");
        data2_2.add("一般");
        data2_2.add("较差");
        data2_3.add("降水");
        data2_3.add("地下水");
        data2_3.add("冰雪融水");
        data2_3.add("其他");
        data2_4.add("均匀");
        data2_4.add("不均匀");
        data2_6.add("存在");
        data2_6.add("不存在");
        data2_7.add("具备");
        data2_7.add("不具备");
        data2_8.add("较大");
        data2_8.add("一般");
        data2_8.add("较小");
        data2_8.add("无");

        data3_1.add("存在（低于0℃）");
        data3_1.add("不存在");
        data3_2.add("容易（冬季雨雪、冰冻天数每年＞7天）");
        data3_2.add("不容易");
        data3_3.add("存在");
        data3_3.add("不存在");
        data3_4.add("较大");
        data3_4.add("一般");
        data3_4.add("较小");
        data3_4.add("无");

        data4_1.add("较多（≥20天）");
        data4_1.add("较少（＜20天）");
        data4_1.add("无");
        data4_2.add("偏多");
        data4_2.add("偏少");
        data4_2.add("无");
        data4_3.add("存在");
        data4_3.add("不存在");
        data4_4.add("少雷区");
        data4_4.add("多雷区");
        data4_4.add("高雷区");
        data4_4.add("强雷区");
        data4_4.add("无雷区");

        data5_1.add("砂岩");
        data5_1.add("砾岩");
        data5_1.add("页岩");
        data5_1.add("碳酸盐岩");
        data5_1.add("变质岩");
        data5_1.add("岩浆岩");
        data5_1.add("碎屑堆积物");
        data5_2.add("不发育");
        data5_2.add("较发育");
        data5_2.add("极为发育");
        data5_3.add("较近");
        data5_3.add("较远");
        data5_4.add("较小");
        data5_4.add("中等");
        data5_4.add("较大");
        data5_5.add("无");
        data5_5.add("有");
        data5_6.add("不发育");
        data5_6.add("发育");
        data5_7.add("较好");
        data5_7.add("一般");
        data5_7.add("较差");
        data5_8.add("不存在");
        data5_8.add("存在");
        data5_9.add("会");
        data5_9.add("不会");
        data5_10.add("有");
        data5_10.add("无");
        data5_11.add("较小");
        data5_11.add("一般");
        data5_11.add("较高");
        data5_12.add("较陡");
        data5_12.add("较缓");
        data5_12.add("一般");
        data5_13.add("较小");
        data5_13.add("较高");
        data5_13.add("一般");
    }

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

    private void initView() {

        setAdapter();
    }

    private void setAdapter(){
        List<String> g1_1 = new ArrayList<>();
        for(String str : data1_1.keySet()){
            g1_1.add(str);
        }

        ArrayAdapter<String> pgAdapter1_1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,g1_1);
        ArrayAdapter<String> pgAdapter1_2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_2);
        ArrayAdapter<String> pgAdapter1_3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_3);
        ArrayAdapter<String> pgAdapter1_4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_4);
        ArrayAdapter<String> pgAdapter1_6 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_6);
        ArrayAdapter<String> pgAdapter1_7 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_7);
        ArrayAdapter<String> pgAdapter1_8 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data1_8);

        ArrayAdapter<String> pgAdapter2_1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_1);
        ArrayAdapter<String> pgAdapter2_2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_2);
        ArrayAdapter<String> pgAdapter2_3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_3);
        ArrayAdapter<String> pgAdapter2_4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_4);
        ArrayAdapter<String> pgAdapter2_6 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_6);
        ArrayAdapter<String> pgAdapter2_7 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_7);
        ArrayAdapter<String> pgAdapter2_8 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data2_8);

        ArrayAdapter<String> pgAdapter3_1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3_1);
        ArrayAdapter<String> pgAdapter3_2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3_2);
        ArrayAdapter<String> pgAdapter3_3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3_3);
        ArrayAdapter<String> pgAdapter3_4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data3_4);

        ArrayAdapter<String> pgAdapter4_1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4_1);
        ArrayAdapter<String> pgAdapter4_2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4_2);
        ArrayAdapter<String> pgAdapter4_3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4_3);
        ArrayAdapter<String> pgAdapter4_4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data4_4);

        ArrayAdapter<String> pgAdapter5_1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_1);
        ArrayAdapter<String> pgAdapter5_2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_2);
        ArrayAdapter<String> pgAdapter5_3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_3);
        ArrayAdapter<String> pgAdapter5_4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_4);
        ArrayAdapter<String> pgAdapter5_5 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_5);
        ArrayAdapter<String> pgAdapter5_6 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_6);
        ArrayAdapter<String> pgAdapter5_7 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_7);
        ArrayAdapter<String> pgAdapter5_8 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_8);
        ArrayAdapter<String> pgAdapter5_9 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_9);
        ArrayAdapter<String> pgAdapter5_10 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_10);
        ArrayAdapter<String> pgAdapter5_11 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_11);
        ArrayAdapter<String> pgAdapter5_12 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_12);
        ArrayAdapter<String> pgAdapter5_13 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data5_13);


        spinnerSetAdapter(pgAdapter1_1,hydroGeology11);
        spinnerSetAdapter(pgAdapter1_2,hydroGeology12);
        spinnerSetAdapter(pgAdapter1_3,hydroGeology13);
        spinnerSetAdapter(pgAdapter1_4,hydroGeology14);
        spinnerSetAdapter(pgAdapter1_6,hydroGeology16);
        spinnerSetAdapter(pgAdapter1_7,hydroGeology17);
        spinnerSetAdapter(pgAdapter1_8,hydroGeology18);

        spinnerSetAdapter(pgAdapter2_1,hydroGeology21);
        spinnerSetAdapter(pgAdapter2_2,hydroGeology22);
        spinnerSetAdapter(pgAdapter2_3,hydroGeology23);
        spinnerSetAdapter(pgAdapter2_4,hydroGeology24);
        spinnerSetAdapter(pgAdapter2_6,hydroGeology26);
        spinnerSetAdapter(pgAdapter2_7,hydroGeology27);
        spinnerSetAdapter(pgAdapter2_8,hydroGeology28);

        spinnerSetAdapter(pgAdapter3_1,hydroGeology31);
        spinnerSetAdapter(pgAdapter3_2,hydroGeology32);
        spinnerSetAdapter(pgAdapter3_3,hydroGeology33);
        spinnerSetAdapter(pgAdapter3_4,hydroGeology34);


        spinnerSetAdapter(pgAdapter4_1,hydroGeology41);
        spinnerSetAdapter(pgAdapter4_2,hydroGeology42);
        spinnerSetAdapter(pgAdapter4_3,hydroGeology43);
        spinnerSetAdapter(pgAdapter4_4,hydroGeology44);

        spinnerSetAdapter(pgAdapter5_1,hydroGeology51);
        spinnerSetAdapter(pgAdapter5_2,hydroGeology52);
        spinnerSetAdapter(pgAdapter5_3,hydroGeology53);
        spinnerSetAdapter(pgAdapter5_4,hydroGeology54);
        spinnerSetAdapter(pgAdapter5_5,hydroGeology55);
        spinnerSetAdapter(pgAdapter5_6,hydroGeology56);
        spinnerSetAdapter(pgAdapter5_7,hydroGeology57);
        spinnerSetAdapter(pgAdapter5_8,hydroGeology58);
        spinnerSetAdapter(pgAdapter5_9,hydroGeology59);
        spinnerSetAdapter(pgAdapter5_10,hydroGeology510);
        spinnerSetAdapter(pgAdapter5_11,hydroGeology511);
        spinnerSetAdapter(pgAdapter5_12,hydroGeology512);
        spinnerSetAdapter(pgAdapter5_13,hydroGeology513);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter,Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
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

    private void setData(){

        String rainStorm = "";
        rainStorm += "，所在流域属"+ hydroGeology11.getSelectedItem().toString() + "，具有" + data1_1.get(hydroGeology11.getSelectedItem().toString()) + "等特点。"
                  + "该地多年平均气温 ℃，最热月平均气温 ℃，最冷月平均气温 ℃，多年平均降水量,多年平均降水量 mm,，主要集中在 月,占全年降水量的75%。"
                  + "该地区气候垂直变化" + hydroGeology12.getSelectedItem().toString() + "，夏季降水充足，" + hydroGeology13.getSelectedItem().toString() + "干旱、暴雨、大风、绵雨等灾害性天气。"
                  +  "该地的降水年内分配极不均匀，主要集中在 月，暴雨多集中在 月，夏季常常出现短时强降雨天气，易造成短时洪峰。其次该流域内，植被覆盖情况" + hydroGeology14.getSelectedItem().toString()
                  + "，对地表的坡面流水有一定的调蓄和涵养能力，暴雨季节库区及河道内的漂浮物（枯枝、落叶、杂物）" + hydroGeology16.getSelectedItem().toString() + "，对电站设施存在" + hydroGeology17.getSelectedItem().toString()
                  + "的影响。综合而言，该电站受暴雨的影响" + hydroGeology18.getSelectedItem().toString() + "。"  ;

        String flood = "";
        flood += "该流域内多山地及丘陵，支流发育，汇水面积大，该电站的库容量"+ hydroGeology21.getSelectedItem().toString() + "，拦河坝设计洪水二十年一遇，校核洪水一百年一遇，厂房设计洪水二十年一遇，校核洪水五十年一遇。流域上游森林植被覆盖率高，生态环境"
               + hydroGeology22.getSelectedItem().toString() + "。流域径流主要由" + hydroGeology23.getSelectedItem().toString() + "补给形成，径流年内分配" + hydroGeology24.getSelectedItem().toString() + "，径流主要集中在汛期 月，径流量约占全年的78.2%，8月汛期平均流量可达41.3m3/s。箭板电站龙溪河上游"
               + hydroGeology26.getSelectedItem().toString() + "其他的电站及大坝，对该流域的洪峰";
        if (hydroGeology26.getSelectedItem().equals("存在")){
            flood += "能够起到一定的调节作用，";
        }else {
            flood += "缺少一定的调节作用，";
        }
        flood += "箭板电站也具备一定的泄洪调蓄能力。因此，该电站的洪水风险" + hydroGeology27.getSelectedItem().toString() + "。";

        String low = "";
        low += "该地区冬季" + hydroGeology31.getSelectedItem().toString() + "低温天气，" + hydroGeology32.getSelectedItem().toString() + "出现雨雪、冰冻等低温天气，该地河流" + hydroGeology33.getSelectedItem().toString()
            + "凌汛现象，其他低温灾害对电站设施及大坝的影响" + hydroGeology34.getSelectedItem().toString() + "。";

        String lightning = "";
        lightning += "箭板电站地处河谷地带，因地形、地势原因，气流抬升作用明显，夏季雷雨天气" + hydroGeology41.getSelectedItem().toString() + "，且云层偏低，因此雷雨云对地闪击的频率比平原或丘陵区" + hydroGeology42.getSelectedItem().toString()
                  + "，该地区" + hydroGeology43.getSelectedItem().toString() + "发生雷击的风险。" + "箭板电站距离沐川县城约24公里（04-4），根据中国气象数据，沐川县年平均雷暴日为42.9d/a（04-1）。综合考虑沐川县与箭板电站的降雨、地形、气候等因素差异，电站所在区域的雷暴日约在30～50d/a，属于"
                  + hydroGeology44.getSelectedItem().toString() + "。";

        String geology = "";
        geology += "板电站即位于龙溪河河谷冲积地区，该地区的岩性主要为" + hydroGeology51.getSelectedItem().toString() +"，电站周边地形坡地"+ hydroGeology512.getSelectedItem().toString() + "。电站区域内的崩塌、滑坡、泥石流等地质灾害" + hydroGeology52.getSelectedItem().toString()
                + "。其次，该地区距离地震带" + hydroGeology53.getSelectedItem().toString() + "，地震发生强度及规模均" + hydroGeology54.getSelectedItem().toString() + "。该地区" + hydroGeology55.getSelectedItem().toString() + "区域性断层通过，小型活动断裂隙构造"
                + hydroGeology56.getSelectedItem().toString() + "，该地区的区域稳定性" + hydroGeology57.getSelectedItem().toString() + "，" + hydroGeology58.getSelectedItem().toString() + "发生较强破坏力地震的可能性，电站坝址区的地基稳定" + hydroGeology59.getSelectedItem().toString()
                + "，电站库区蓄水并" + hydroGeology510.getSelectedItem().toString() + "诱发过库区地震。综上所述，该电站区域发生地震灾害的风险" + hydroGeology511.getSelectedItem().toString()  + "，发生崩塌、滑坡、泥石流等地质灾害的的风险" + hydroGeology513.getSelectedItem().toString() + "。";

        HydroGeology hydroGeology = new HydroGeology();
        hydroGeology.setRainStorm(rainStorm);
        hydroGeology.setFlood(flood);
        hydroGeology.setLow(low);
        hydroGeology.setLightning(lightning);
        hydroGeology.setGeology(geology);

        String json = JSONObject.toJSONString(hydroGeology);
        String url = Constants.TEST_SERVICE + "/hydro/uploadGeology";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json",json)
                .add("id",Constants.HYDRO_ID.toString())
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        /**
         * qi sha la kuo le ni wa
         * wo zhi dao wo de wei lai bu shi meng
         * wo bu zai de di yi
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToastShort("网络连接失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr,ResultModel.class);
                if (result.getCode() == 0){
                    Intent intent = new Intent(HydroGeologyActivity.this,HydroImageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }



}
