package com.cykj.survey.activity.property;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cykj.survey.MainActivity;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.EquipmentAdapter;
import com.cykj.survey.model.Equipment;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatePropertyActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    /**
     * 项目名称
     */
    @BindView(R.id.property_edit_name)
    MaterialEditText propertyEditName;
    /**
     * 地区选择文字
     */
    @BindView(R.id.property_area_select_text)
    TextView propertyAreaSelectText;
    /**
     * 地区选择布局
     */
    @BindView(R.id.property_area_select)
    RelativeLayout propertyAreaSelect;
    /**
     * 项目所在小镇
     */
    @BindView(R.id.property_edit_town)
    MaterialEditText propertyEditTown;
    /**
     * 项目详细地址
     */
    @BindView(R.id.property_edit_addr)
    MaterialEditText propertyEditAddr;
    /**
     * 项目建成时间
     */
    @BindView(R.id.property_date_select_text)
    TextView propertyDateSelectText;
    /**
     * 项目建成时间布局
     */
    @BindView(R.id.property_date_select)
    RelativeLayout propertyDateSelect;
    /**
     * 项目占地面积
     */
    @BindView(R.id.property_edit_acreage)
    MaterialEditText propertyEditAcreage;
    /**
     * 住宅
     */
    @BindView(R.id.property_edit_residence)
    MaterialEditText propertyEditResidence;
    /**
     *商铺
     */
    @BindView(R.id.property_edit_shops)
    MaterialEditText propertyEditShops;
    /**
     *住户
     */
    @BindView(R.id.property_edit_household)
    MaterialEditText propertyEditHousehold;
    /**
     *租户
     */
    @BindView(R.id.property_edit_tenant)
    MaterialEditText propertyEditTenant;
    /**
     *收费标准
     */
    @BindView(R.id.property_edit_charge)
    MaterialEditText propertyEditCharge;
    /**
     *地面停车位
     */
    @BindView(R.id.property_edit_ground_parking)
    MaterialEditText propertyEditGroundParking;
    /**
     *地下停车位
     */
    @BindView(R.id.property_edit_underground_parking)
    MaterialEditText propertyEditUndergroundParking;
    /**
     *添加按钮
     */
    @BindView(R.id.property_add_text)
    TextView propertyAddText;
    /**
     *展示列表
     */
    @BindView(R.id.property_recview)
    RecyclerView propertyRecview;
    /**
     *提示信息
     */
    @BindView(R.id.tip)
    RelativeLayout tip;
    /**
     *公司名称
     */
    @BindView(R.id.property_edit_company_name)
    MaterialEditText propertyEditCompanyName;
    /**
     *公司成立时间文字
     */
    @BindView(R.id.property_company_date_select_text)
    TextView propertyCompanyDateSelectText;
    /**
     *公司成立时间布局
     */
    @BindView(R.id.property_company_date_select)
    RelativeLayout propertyCompanyDateSelect;
    /**
     * 注册分局
     */
    @BindView(R.id.property_edit_company_register)
    MaterialEditText propertyEditCompanyRegister;
    /**
     *注册资金
     */
    @BindView(R.id.property_edit_company_capital)
    MaterialEditText propertyEditCompanyCapital;
    /**
     *入驻时间
     */
    @BindView(R.id.property_enter_date_select_text)
    TextView propertyEnterDateSelectText;
    /**
     * 入驻时间布局
     */
    @BindView(R.id.property_enter_date_select)
    RelativeLayout propertyEnterDateSelect;
    /**
     * 委托方
     */
    @BindView(R.id.property_edit_client)
    MaterialEditText propertyEditClient;
    /**
     * 委托方联系人
     */
    @BindView(R.id.property_edit_client_contact)
    MaterialEditText propertyEditClientContact;
    /**
     * 委托方联系电话
     */
    @BindView(R.id.property_edit_client_contact_phone)
    MaterialEditText propertyEditClientContactPhone;

    private Calendar cal;
    private int year,month,day;

    private CityPickerView mPicker = new CityPickerView();

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_create);
        ButterKnife.bind(this);
        getDate();
        mPicker.init(this);
        initTopbar();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {

        propertyAreaSelect.setOnClickListener(new View.OnClickListener() {
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

                        propertyAreaSelectText.setText(province.getName() + "-" + city.getName() + "-" + district.getName());
                        propertyAreaSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }

                    @Override
                    public void onCancel() {
                        showToastShort("操作取消");
                    }
                });
                mPicker.showCityPicker();
            }
        });

        propertyDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        propertyDateSelectText.setText(year+"年"+(++month)+"月"+day+"日");
                        propertyDateSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }
                };
                //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                DatePickerDialog dialog=new DatePickerDialog(CreatePropertyActivity.this, 0,listener,year,month,day);
                dialog.show();
            }
        });

        propertyCompanyDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        propertyCompanyDateSelectText.setText(year+"年"+(++month)+"月"+day+"日");
                        propertyCompanyDateSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }
                };
                //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                DatePickerDialog dialog=new DatePickerDialog(CreatePropertyActivity.this, 0,listener,year,month,day);
                dialog.show();
            }
        });

        propertyEnterDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        propertyEnterDateSelectText.setText(year+"年"+(++month)+"月"+day+"日");
                        propertyEnterDateSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }
                };
                //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                DatePickerDialog dialog=new DatePickerDialog(CreatePropertyActivity.this, 0,listener,year,month,day);
                dialog.show();
            }
        });

        propertyAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });


    }

    private List<Equipment> equipmentList = new ArrayList<>();
    /**
     * 显示添加dialog
     */
    private void showAddDialog(){
        final Equipment equipment = new Equipment();
        final QMUIDialog dialog = new QMUIDialog(this,R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.layout_property_add_equipment);
        dialog.setCanceledOnTouchOutside(false);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dialog.onWindowAttributesChanged(lp);
        final MaterialEditText name = dialog.findViewById(R.id.equipment_edit_name);
        final Spinner spinner = dialog.findViewById(R.id.equipment_spinner);
        List<String> data = new ArrayList<>();
        data.add("教育配套");
        data.add("医疗卫生配套");
        data.add("健身、娱乐配套");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data);
        spinnerSetAdapter(adapter,spinner);
        QMUIRoundButton confirmButton = dialog.findViewById(R.id.equipment_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editVerify(name)){
                    equipment.setName(name.getText().toString());
                }else{
                    return;
                }
                equipment.setType(spinner.getSelectedItem().toString());
                equipmentList.add(equipment);
                dialog.dismiss();
                initRec();
            }
        });

        QMUIRoundButton cancelButton = dialog.findViewById(R.id.equipment_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private EquipmentAdapter equipmentAdapter;
    private void initRec(){
        if (equipmentList.size() == 0){
            return;
        }else if (equipmentList.size() == 1){
            equipmentAdapter = new EquipmentAdapter(this,equipmentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            propertyRecview.setLayoutManager(layoutManager);
            propertyRecview.setAdapter(equipmentAdapter);
            propertyRecview.setItemAnimator(new DefaultItemAnimator());
            propertyRecview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            tip.setVisibility(View.GONE);
            propertyRecview.setVisibility(View.VISIBLE);
        }else if (equipmentList.size() > 1){
            equipmentAdapter.notifyDataSetChanged();
        }
    }

    private void initTopbar() {
        topbar.setTitle("基本信息");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePropertyActivity.this,PropertyUploadActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private boolean isNull = false;
    private boolean editVerify(final MaterialEditText materialEdit) {
        if (materialEdit.getText().toString().equals("")) {
            materialEdit.setError("请输入" + materialEdit.getHint().toString());
            isNull = true;
            return false;
        }
        isNull = false;
        return true;
    }
}
