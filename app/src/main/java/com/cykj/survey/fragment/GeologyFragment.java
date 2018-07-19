package com.cykj.survey.fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.IndustryActivity;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    private AccidentGridAdapter g1_5adapter;
    private AccidentGridAdapter g2_4adapter;

    private Handler handler;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.ftagment_geology_, null);
        ButterKnife.bind(this, root);
        handler = new Handler();
        initData();
        initTopbar();
        initView();
        return root;
    }

    List<String> g1_5select = new ArrayList<>();
    List<String> g2_4select = new ArrayList<>();

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
        g1_5adapter = new AccidentGridAdapter(getActivity(),data1_5);
        geology1_5.setAdapter(g1_5adapter);
        geology1_5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data1_5.get(position).isSelect()) {
                    data1_5.get(position).setSelect(false);
                    g1_5select.remove(data1_5.get(position).getName());
                } else {
                    data1_5.get(position).setSelect(true);
                    g1_5select.add(data1_5.get(position).getName());
                }
                g1_5adapter.notifyDataSetChanged();
            }
        });


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
        g2_4adapter = new AccidentGridAdapter(getActivity(),data2_4);
        geology2_4.setAdapter(g2_4adapter);
        geology2_4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data2_4.get(position).isSelect()) {
                    data2_4.get(position).setSelect(false);
                    g2_4select.remove(data2_4.get(position).getName());
                } else {
                    data2_4.get(position).setSelect(true);
                    g2_4select.add(data2_4.get(position).getName());
                }
                g2_4adapter.notifyDataSetChanged();
            }
        });

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
        for(String str : data5_1.keySet()){
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
    private List<AccidentGridModel> data1_5 = new ArrayList<>();
    private List<String> data1_6 = new ArrayList<>();
    private List<String> data1_7 = new ArrayList<>();
    private List<String> data2_1 = new ArrayList<>();
    private List<String> data2_2 = new ArrayList<>();
    private List<AccidentGridModel> data2_4 = new ArrayList<>();
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


        data1_5.add(new AccidentGridModel("高温",false));
        data1_5.add(new AccidentGridModel("低温",false));
        data1_5.add(new AccidentGridModel("干燥",false));
        data1_5.add(new AccidentGridModel("热浪",false));
        data1_5.add(new AccidentGridModel("寒潮",false));
        data1_5.add(new AccidentGridModel("其他",false));

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


        data2_4.add(new AccidentGridModel("暴雨",false));
        data2_4.add(new AccidentGridModel("少雨",false));
        data2_4.add(new AccidentGridModel("冰雹",false));
        data2_4.add(new AccidentGridModel("暴雪",false));
        data2_4.add(new AccidentGridModel("潮湿",false));
        data2_4.add(new AccidentGridModel("其他",false));

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
                            postJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void postJson() {

        String geologyStr = "本地区属于"+ geology5_1.getSelectedItem().toString() + "区，本地区" + data5_1.get(geology5_1.getSelectedItem().toString()) +"，出露的地层以"
                 + geology5_2.getSelectedItem().toString() +"为主。该地区的地质灾害类型主要以" + geology5_3.getSelectedItem().toString() + "为主，地质灾害" + geology5_4.getSelectedItem()
                +"。环境地质问题相对"+geology5_5.getSelectedItem().toString()+"，主要环境地质问题为"+geology5_6.getSelectedItem().toString()+"，该地区的环境地质问题"
                +geology5_7.getSelectedItem().toString() +"危及到厂区安全。综合调查分析认为，本地区存在的地质灾害"+geology5_8.getSelectedItem().toString()
                +"对企业的生产造成影响,"+geology5_9.getSelectedItem().toString()+"给企业造成经济损失。本地区"+ geology5_10.getSelectedItem().toString() +"断裂构造，地质环境"
                +geology5_11.getSelectedItem().toString() + "，" + geology5_12.getSelectedItem().toString();

        if (geology5_12.getSelectedItem().toString().equals("容易")){
            geologyStr += "发生地震，建议标的企业按照国家有关技术要求，做好防震防灾减损工作，做好防震防灾预案准备。通过本次汛期防灾减损勘察工作，综合认为标的企业发生地质灾害的风险"
            + geology5_13.getSelectedItem().toString() + "。";
        }else {
            geologyStr += "发生地震。通过本次汛期防灾减损勘察工作，综合认为标的企业发生地质灾害的风险"+ geology5_13.getSelectedItem().toString() + "。";
        }

        if (geology1_4.getText().toString().equals("")){
            showToastShort("请输入异常气温出现月份");
            return;
        }
        if (geology2_3.getText().toString().equals("")){
            showToastShort("请输入异常降水出现月份");
            return;
        }
        if (geology3_1.getSelectedItem().toString().equals("是") & geology3_2.getText().toString().equals("")){
            showToastShort("请输入流域信息");
            return;
        }

        String g1_5selectStr = "";
        if (g1_5select.size() > 0){
            for (int i = 0;i<g1_5select.size();i++){
                if (i == 0){
                    g1_5selectStr += g1_5select.get(i);
                }else {
                    g1_5selectStr += "、"+g1_5select.get(i);
                }
            }
        }

        String g2_4selectStr = "";
        if (g2_4select.size() > 0){
            for (int i = 0;i<g2_4select.size();i++){
                if (i == 0){
                    g2_4selectStr += g2_4select.get(i);
                }else {
                    g2_4selectStr += "、"+g2_4select.get(i);
                }
            }
        }

        String weatherStr = "";
        if (geology3_1.getSelectedItem().toString().equals("否")){
            weatherStr += "本地区不属于任何流域";
        }else {
            weatherStr += "本地区属于" + geology3_2.getText().toString();
        }

       weatherStr += "流域，该地区属于"+ geology1_1.getSelectedItem().toString()
                +"气候，"+data1_1.get(geology1_1.getSelectedItem().toString())+"。该地区的气温与往年同期相比"+geology1_2.getSelectedItem().toString()
                +"，异常气温日数与往年同期相比"+geology1_3.getSelectedItem().toString()+"，其主要出现在"+geology1_4.getText()+"月份，主要以" + g1_5selectStr
                +"等为主，异常气温天气" + geology1_6.getSelectedItem().toString() + "对企业的生产生活造成影响，"+geology1_7.getSelectedItem().toString()
                +"造成直接的经济损失。" + "本地区的年均降雨量为900-950mm，降雨季节分配不均，5-10月的降雨量占全年的87%。降水与往年同期相比"+geology2_1.getSelectedItem().toString()
                +"，异常降水与往年同期相比"+geology2_2.getSelectedItem().toString()+"，主要出现在"+geology2_3.getText().toString()+"月份，主要以"+g2_4selectStr
                +"等为主，异常降水"+geology2_5.getSelectedItem().toString()+"对企业的生产造成影响，"+geology2_6.getSelectedItem().toString()
               +"造成直接的经济损失。综合认为标的企业发生气象灾害的风险";

        if (geology2_7.getSelectedItem().toString().equals("较高")){
            weatherStr += geology2_7.getSelectedItem().toString() + "，密切关注气象部门的气象预报，做好气象灾害应急预案及物资准备，灾害易发季节，落实专职人员做好统筹协调及巡检工作。";
        }else {
            weatherStr += geology2_7.getSelectedItem().toString() +"。";
        }

        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
        String url = Constants.TEST_SERVICE + "/company/postStr";

        OkHttpClient client = new OkHttpClient();

        Long companyId = Constants.REPORT_ID;

        RequestBody body = new FormBody.Builder()
                .add("companyId",companyId.toString())
                .add("geologyStr",geologyStr)
                .add("weatherStr",weatherStr)
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
                tipDialogDismiss();
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    Intent intent = new Intent(getActivity(),PhotoUploadActivity.class);
                    getActivity().startActivity(intent);
                    handler.post(finishAble);
                }
            }
        });
    }

    Runnable finishAble = new Runnable() {
        @Override
        public void run() {
            popBackStack();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
