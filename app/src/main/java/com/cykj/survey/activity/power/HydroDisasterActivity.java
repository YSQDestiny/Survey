package com.cykj.survey.activity.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        data15.add("是");
        data15.add("否");

        data16.add("整齐（线路按要求走线槽，设有防火封堵）");
        data16.add("较整齐（动力电缆和大部分控制电缆走线槽，有防火封堵）");
        data16.add("不整齐（线路布置杂乱，动力电缆和控制电缆混合布线）");

        data17.add("是");
        data17.add("否");

        data18.add("厂房外部");
        data18.add("厂房内部");

        data20.add("是");
        data20.add("否");
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

    }

    private void initTopbar() {
        topbar.setTitle("火灾风险");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HydroDisasterActivity.this, HydroDisasterPhotoActivity.class);
                startActivity(intent);
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

    private void setData(){
        String disaster = "";
        disaster += "箭板电站有" + hydroDisasterMain.getText().toString() + "座主厂房，"+hydroDisasterVice.getText().toString()+"座副厂房，厂房主体为"
                 + hydroDisaster2.getSelectedItem().toString() + "，根据对厂房结构材料进行观测，初步判定为";
        switch (hydroDisaster2.getSelectedItem().toString()){
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

        disaster += "厂房区域" + hydroDisaster4.getSelectedItem().toString() +"禁烟火标志，" + hydroDisaster5.getSelectedItem().toString() +"自动消防报警装置。其内部物资等放置"
                 + hydroDisaster6.getSelectedItem().toString() + "，消防间距" + hydroDisaster7.getSelectedItem().toString() + "，消防器材配置充足且按期点检，透平油等易燃物"
                 + hydroDisaster8.getSelectedItem().toString() + "保存，发生火灾风险小；重点区域安装视频监控，中控室24小时有人值班，能及时发现并扑灭初期火灾。";
    }

}
