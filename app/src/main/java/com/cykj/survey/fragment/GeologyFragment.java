package com.cykj.survey.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GeologyFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.geology_1_1)
    Spinner geology1_1;
    @BindView(R.id.geology_1_2)
    Spinner geology1_2;
    @BindView(R.id.geology_1_3)
    Spinner geology1_3;
    @BindView(R.id.geology_1_4)
    EditText geology1_4;
    @BindView(R.id.geology_1_5)
    CustomGridView geology1_5;
    @BindView(R.id.geology_1_6)
    Spinner geology1_6;
    @BindView(R.id.geology_1_7)
    Spinner geology1_7;
    @BindView(R.id.geology_2_1)
    Spinner geology2_1;
    @BindView(R.id.geology_2_2)
    Spinner geology2_2;
    @BindView(R.id.geology_2_3)
    EditText geology2_3;
    @BindView(R.id.geology_2_4)
    CustomGridView geology2_4;
    @BindView(R.id.geology_2_5)
    Spinner geology2_5;
    @BindView(R.id.geology_2_6)
    Spinner geology2_6;
    @BindView(R.id.geology_2_7)
    Spinner geology2_7;
    @BindView(R.id.geology_3_1)
    Spinner geology3_1;
    @BindView(R.id.geology_3_2)
    EditText geology3_2;
    @BindView(R.id.geology_5_1)
    Spinner geology5_1;
    @BindView(R.id.geology_5_2)
    Spinner geology5_2;
    @BindView(R.id.geology_5_3)
    Spinner geology5_3;
    @BindView(R.id.geology_5_4)
    Spinner geology5_4;
    @BindView(R.id.geology_5_5)
    Spinner geology5_5;
    @BindView(R.id.geology_5_6)
    Spinner geology5_6;
    @BindView(R.id.geology_5_7)
    Spinner geology5_7;
    @BindView(R.id.geology_5_8)
    Spinner geology5_8;
    @BindView(R.id.geology_5_9)
    Spinner geology5_9;
    @BindView(R.id.geology_5_10)
    Spinner geology5_10;
    @BindView(R.id.geology_5_11)
    Spinner geology5_11;
    @BindView(R.id.geology_5_12)
    Spinner geology5_12;
    @BindView(R.id.geology_5_13)
    Spinner geology5_13;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.ftagment_geology_, null);
        ButterKnife.bind(this, root);
        initData();
        initTopbar();
        initView();
        return root;
    }

    private void initView() {
        ArrayAdapter<String> geology1_1Adapter;
        List<String> g1_1 = new ArrayList<>();
        for(String str : data1_1.keySet()){
            g1_1.add(str);
        }
        geology1_1Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,g1_1);
        spinnerSetAdapter(geology1_1Adapter,geology1_1);

        ArrayAdapter<String> geology1_2Adapter;
        geology1_2Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data1_2);
        spinnerSetAdapter(geology1_2Adapter,geology1_2);

        ArrayAdapter<String> geology1_3Adapter;
        geology1_3Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data1_3);
        spinnerSetAdapter(geology1_3Adapter,geology1_3);

        //g1 5

        ArrayAdapter<String> geology1_6Adapter;
        geology1_6Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data1_6);
        spinnerSetAdapter(geology1_6Adapter,geology1_6);

        ArrayAdapter<String> geology1_7Adapter;
        geology1_7Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data1_7);
        spinnerSetAdapter(geology1_7Adapter,geology1_7);

        ArrayAdapter<String> geology2_1Adapter;
        geology2_1Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data2_1);
        spinnerSetAdapter(geology2_1Adapter,geology2_1);

        ArrayAdapter<String> geology2_2Adapter;
        geology2_2Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data2_2);
        spinnerSetAdapter(geology2_2Adapter,geology2_2);

        //g2 4

        ArrayAdapter<String> geology2_5Adapter;
        geology2_5Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data2_5);
        spinnerSetAdapter(geology2_5Adapter,geology2_5);

        ArrayAdapter<String> geology2_6Adapter;
        geology2_6Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data2_6);
        spinnerSetAdapter(geology2_6Adapter,geology2_6);

        ArrayAdapter<String> geology2_7Adapter;
        geology2_7Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data2_7);
        spinnerSetAdapter(geology2_7Adapter,geology2_7);

        ArrayAdapter<String> geology3_1Adapter;
        geology3_1Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data3_1);
        spinnerSetAdapter(geology3_1Adapter,geology3_1);

        ArrayAdapter<String> geology5_1Adapter;
        List<String> g5_1 = new ArrayList<>();
        for(String str : data1_1.keySet()){
            g5_1.add(str);
        }
        geology5_1Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,g5_1);
        spinnerSetAdapter(geology5_1Adapter,geology5_1);

        ArrayAdapter<String> geology5_2Adapter;
        geology5_2Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_2);
        spinnerSetAdapter(geology5_2Adapter,geology5_2);

        ArrayAdapter<String> geology5_3Adapter;
        geology5_3Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_3);
        spinnerSetAdapter(geology5_3Adapter,geology5_3);

        ArrayAdapter<String> geology5_4Adapter;
        geology5_4Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_4);
        spinnerSetAdapter(geology5_4Adapter,geology5_4);

        ArrayAdapter<String> geology5_5Adapter;
        geology5_5Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_5);
        spinnerSetAdapter(geology5_5Adapter,geology5_5);

        ArrayAdapter<String> geology5_6Adapter;
        geology5_6Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_6);
        spinnerSetAdapter(geology5_6Adapter,geology5_6);

        ArrayAdapter<String> geology5_7Adapter;
        geology5_7Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_7);
        spinnerSetAdapter(geology5_7Adapter,geology5_7);

        ArrayAdapter<String> geology5_8Adapter;
        geology5_8Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_8);
        spinnerSetAdapter(geology5_8Adapter,geology5_8);

        ArrayAdapter<String> geology5_9Adapter;
        geology5_9Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_9);
        spinnerSetAdapter(geology5_9Adapter,geology5_9);

        ArrayAdapter<String> geology5_10Adapter;
        geology5_10Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_10);
        spinnerSetAdapter(geology5_10Adapter,geology5_10);

        ArrayAdapter<String> geology5_11Adapter;
        geology5_11Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_11);
        spinnerSetAdapter(geology5_11Adapter,geology5_11);

        ArrayAdapter<String> geology5_12Adapter;
        geology5_12Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_12);
        spinnerSetAdapter(geology5_12Adapter,geology5_12);

        ArrayAdapter<String> geology5_13Adapter;
        geology5_13Adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,data5_13);
        spinnerSetAdapter(geology5_13Adapter,geology5_13);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter,Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private Map<String,String> data1_1 = new HashMap<>();
    private List<String> data1_2 = new ArrayList<>();
    private List<String> data1_3 = new ArrayList<>();
    private List<String> data1_5 = new ArrayList<>();
    private List<String> data1_6 = new ArrayList<>();
    private List<String> data1_7 = new ArrayList<>();
    private List<String> data2_1 = new ArrayList<>();
    private List<String> data2_2 = new ArrayList<>();
    private List<String> data2_4 = new ArrayList<>();
    private List<String> data2_5 = new ArrayList<>();
    private List<String> data2_6 = new ArrayList<>();
    private List<String> data2_7 = new ArrayList<>();
    private List<String> data3_1 = new ArrayList<>();
    private Map<String,String> data5_1 = new HashMap<>();
    private List<String> data5_2 = new ArrayList<>();
    private List<String> data5_3 = new ArrayList<>();
    private List<String> data5_4 = new ArrayList<>();
    private List<String> data5_5 = new ArrayList<>();
    private List<String> data5_6 = new ArrayList<>();
    private List<String> data5_7 = new ArrayList<>();
    private List<String> data5_8 = new ArrayList<>();
    private List<String> data5_9 = new ArrayList<>();
    private List<String> data5_10 = new ArrayList<>();
    private List<String> data5_11 = new ArrayList<>();
    private List<String> data5_12 = new ArrayList<>();
    private List<String> data5_13 = new ArrayList<>();
    private void initData() {

        data1_1.put("热带季风","终年高温，存在明显的雨季、旱季，降水集中在雨季，且降雨量大，季风气候显著，多热带气旋");
        data1_1.put("亚热带季风","夏季高温多雨，冬季温和少雨，四季分明，降水丰沛，冬夏干湿差别不大");
        data1_1.put("温带季风","夏季高温多雨，冬季寒冷干燥，季风性显著，夏秋季节易受热带气旋影响");
        data1_1.put("高原山地","日照时间长，太阳辐射强，空气稀薄，干燥少云，早晚寒凉晌午热，整体气温较低，局部多大风");
        data1_1.put("温带大陆性","冬季寒冷干燥，夏季炎热湿润，气温年较差、日较差均大，降雨地区差异明显");
        data1_1.put("热带雨林","全年高温多雨，太阳辐射年变化较小，局部地区多热带气旋");


        data1_2.add("偏高");
        data1_2.add("基本持平");
        data1_2.add("偏低");


        data1_3.add("增多");
        data1_3.add("减少");
        data1_3.add("没有明显变化");


        data1_5.add("高温");
        data1_5.add("低温");
        data1_5.add("干燥");
        data1_5.add("热浪");
        data1_5.add("寒潮");
        data1_5.add("其他");


        data1_6.add("会");
        data1_6.add("不会");


        data1_7.add("会");
        data1_7.add("不会");


        data2_1.add("偏高");
        data2_1.add("基本持平");
        data2_1.add("偏低");


        data2_2.add("增多");
        data2_2.add("减少");
        data2_2.add("没有明显变化");


        data2_4.add("暴雨");
        data2_4.add("少雨");
        data2_4.add("冰雹");
        data2_4.add("暴雪");
        data2_4.add("潮湿");
        data2_4.add("其他");


        data2_5.add("会");
        data2_5.add("不会");


        data2_6.add("会");
        data2_6.add("不会");


        data2_7.add("较小");
        data2_7.add("一般");
        data2_7.add("较高");


        data3_1.add("是");
        data3_1.add("否");


        data5_1.put("高原","顶面地势相对平坦，起伏较小，高原边缘存在一定高差");
        data5_1.put("山地","地形起伏较大，山高坡陡，沟谷幽深，多树枝状、脉状水系");
        data5_1.put("丘陵","地形起伏不大，坡度较缓，地面崎岖不平，多连绵不断的低矮山丘");
        data5_1.put("平原","地形平坦开阔，地势起伏较小");
        data5_1.put("河谷","谷底地形较为平坦，河谷内有河漫滩和阶地分布，谷坡稍陡");
        data5_1.put("盆地","内部地形平坦，起伏较小，盆地边缘地势起伏较大，多中低山");

        //1.砂岩 2.砾岩 3.页岩 4.碳酸盐岩 5.变质岩 6.岩浆岩 7.碎屑堆积物

        data5_2.add("砂岩");
        data5_2.add("砾岩");
        data5_2.add("页岩");
        data5_2.add("碳酸盐岩");
        data5_2.add("变质岩");
        data5_2.add("岩浆岩");
        data5_2.add("碎屑堆积物");

        //1.地震 2.滑坡 3.泥石流 4.崩塌 5.地裂缝
        data5_3.add("地震");
        data5_3.add("滑坡");
        data5_3.add("泥石流");
        data5_3.add("崩塌");
        data5_3.add("地裂缝");

        //1.不发育 2.较发育 3.极为发育

        data5_4.add("不发育");
        data5_4.add("较发育");
        data5_4.add("极为发育");

        //1.较轻 2.较重

        data5_5.add("较轻");
        data5_5.add("较重");

        //1.地面陷落 2.地下水超采 3.地下水污染 4.海水倒灌 5.砂土液化 6.水土流失及污染

        data5_6.add("地面陷落");
        data5_6.add("地下水超采");
        data5_6.add("地下水污染");
        data5_6.add("海水倒灌");
        data5_6.add("砂土液化");
        data5_6.add("水土流失及污染");

        //1.会 2.不会

        data5_7.add("会");
        data5_7.add("不会");


        data5_8.add("会");
        data5_8.add("不会");


        data5_9.add("会");
        data5_9.add("不会");

        //1.存在 2.不存在

        data5_10.add("存在");
        data5_10.add("不存在");

        //1.稳定 2.不稳定

        data5_11.add("稳定");
        data5_11.add("不稳定");

        //1.容易 2.不容易

        data5_12.add("容易");
        data5_12.add("不容易");

        //1.较小 2.一般 3.较高

        data5_13.add("较小");
        data5_13.add("一般");
        data5_13.add("较高");
    }

    private void initTopbar() {
        topbar.setTitle("地质水文");

        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        topbar.addRightTextButton("下一页", R.id.topbar_right_text_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        QMUIFragment fragment = new LicenseUploadFragment();
//                        startFragment(fragment);
                        try {
//                            postJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
