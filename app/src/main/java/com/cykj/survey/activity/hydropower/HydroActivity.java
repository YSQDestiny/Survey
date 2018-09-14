package com.cykj.survey.activity.hydropower;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.activity.project.ProjectAccidentActivity;
import com.cykj.survey.activity.project.ProjectAccidentDescActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HydroActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_edit_name)
    MaterialEditText hydroEditName;
    @BindView(R.id.hydro_area_select_text)
    TextView hydroAreaSelectText;
    @BindView(R.id.hydro_area_select)
    RelativeLayout hydroAreaSelect;
    @BindView(R.id.hydro_edit_address)
    MaterialEditText hydroEditAddress;
    @BindView(R.id.hydro_form_select_text)
    TextView hydroFormSelectText;
    @BindView(R.id.hydro_form_select)
    RelativeLayout hydroFormSelect;
    @BindView(R.id.hydro_dam_type_text)
    TextView hydroDamTypeText;
    @BindView(R.id.hydro_dam_type)
    RelativeLayout hydroDamType;
    @BindView(R.id.hydro_purpose_text)
    TextView hydroPurposeText;
    @BindView(R.id.hydro_purpose)
    RelativeLayout hydroPurpose;
    @BindView(R.id.hydro_edit_year)
    MaterialEditText hydroEditYear;
    @BindView(R.id.hydro_edit_crew_number)
    MaterialEditText hydroEditCrewNumber;
    @BindView(R.id.hydro_crew_type_text)
    TextView hydroCrewTypeText;
    @BindView(R.id.hydro_crew_type)
    RelativeLayout hydroCrewType;
    @BindView(R.id.hydro_edit_capacity)
    MaterialEditText hydroEditCapacity;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;

    private CityPickerView mPicker = new CityPickerView();

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_info);
        ButterKnife.bind(this);
        mPicker.init(this);
        initTopbar();
        initView();
    }

    private void initTopbar() {
        topbar.setTitle("基本信息");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        hydroAreaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份
                        if (province == null) {
                            showToastShort("省份信息获取失败");
                            return;
                        } else {

                        }

                        //城市
                        if (city == null) {
                            showToastShort("城市信息获取失败");
                            return;
                        } else {

                        }

                        //地区
                        if (district == null) {
                            showToastShort("地区信息获取失败");
                            return;
                        } else {

                        }

                        hydroAreaSelectText.setText(province.getName() + "-" + city.getName() + "-" + district.getName());
                        hydroAreaSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }

                    @Override
                    public void onCancel() {
                        showToastShort("操作取消");
                    }
                });
                mPicker.showCityPicker();
            }
        });

        final String[] formItem = {"引水式","坝式","抽水蓄能","其他形式"};

        hydroFormSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroFormSelectText,formItem);
            }
        });

        final String[] damItem = {"重力坝（浆砌条石；混凝土）","拱坝","支墩坝"};
        hydroDamType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroDamTypeText,damItem);
            }
        });

        final String[] purpose = {"发电","灌溉","航运"};
        hydroPurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroPurposeText,purpose);
            }
        });

        final String[] crewItems = {"立式轴流式","立式混流式","卧式冲击式","卧式混流式","贯流式"};
        hydroCrewType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroCrewTypeText,crewItems);
            }
        });

    }

    private void showMenuDialog(final TextView tv, final String[] items){
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

}
