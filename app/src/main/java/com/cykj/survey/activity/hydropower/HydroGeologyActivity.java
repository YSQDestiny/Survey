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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        data2_1.add("较大");
        data2_1.add("较小");
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

        data3_1.add("存在");
        data3_1.add("不存在");
        data3_2.add("容易");
        data3_2.add("不容易");
        data3_3.add("存在");
        data3_3.add("不存在");
        data3_4.add("较大");
        data3_4.add("一般");
        data3_4.add("较小");
        data3_4.add("无");

        data4_1.add("较多");
        data4_1.add("较少");
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
                Intent intent = new Intent(HydroGeologyActivity.this,HydroGeologyPhotoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
