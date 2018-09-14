package com.cykj.survey.activity.hydropower;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.fragment.adapter.CrewrecAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.Crew;
import com.cykj.survey.view.CustomGridView;
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
import java.util.List;

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
    @BindView(R.id.hydro_edit_year)
    MaterialEditText hydroEditYear;
    @BindView(R.id.hydro_crew_type_text)
    TextView hydroCrewTypeText;
    @BindView(R.id.hydro_crew_type)
    RelativeLayout hydroCrewType;
    @BindView(R.id.hydro_edit_capacity)
    MaterialEditText hydroEditCapacity;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.crew_add_text)
    TextView crewAddText;
    @BindView(R.id.crew_recview)
    RecyclerView crewRecview;
    @BindView(R.id.tip)
    RelativeLayout tip;
    @BindView(R.id.grid)
    CustomGridView grid;

    private CityPickerView mPicker = new CityPickerView();

    private List<Crew> crewList = new ArrayList<>();

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private List<AccidentGridModel> dataList = new ArrayList<>();


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
                Intent intent = new Intent(HydroActivity.this, HydroGeologyActivity.class);
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

    private AccidentGridAdapter accidentGridAdapteradapter;

    private void initView() {

        dataList.add(new AccidentGridModel("发电",false));
        dataList.add(new AccidentGridModel("灌溉",false));
        dataList.add(new AccidentGridModel("航运",false));

        accidentGridAdapteradapter = new AccidentGridAdapter(this, dataList);
        grid.setAdapter(accidentGridAdapteradapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dataList.get(position).isSelect()) {
                    dataList.get(position).setSelect(false);

                } else {
                    dataList.get(position).setSelect(true);

                }
                accidentGridAdapteradapter.notifyDataSetChanged();
            }
        });

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

        final String[] formItem = {"引水式", "坝式", "其他形式"};

        hydroFormSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroFormSelectText, formItem);
            }
        });

        final String[] damItem = {"重力坝", "拱坝", "支墩坝"};
        hydroDamType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroDamTypeText, damItem);
            }
        });

        final String[] crewItems = {"立式轴流式", "立式混流式", "卧式冲击式", "卧式混流式", "贯流式"};
        hydroCrewType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroCrewTypeText, crewItems);
            }
        });

        crewAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Crew crew = new Crew();
                final QMUIDialog dialog = new QMUIDialog(HydroActivity.this, R.style.QMUI_Dialog);
                dialog.setContentView(R.layout.layout_crew_add_dialog);
                dialog.setCanceledOnTouchOutside(false);
                Window w = dialog.getWindow();
                WindowManager.LayoutParams lp = w.getAttributes();
                lp.x = 0;
                lp.y = 40;
                dialog.onWindowAttributesChanged(lp);
                final MaterialEditText crewPower = dialog.findViewById(R.id.dialog_crew_power);
                final MaterialEditText crewCount = dialog.findViewById(R.id.dialog_crew_count);

                QMUIRoundButton confirmButton = dialog.findViewById(R.id.crew_confirm);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editVerify(crewPower)) {
                            crew.setPower(Integer.parseInt(crewPower.getText().toString()));
                        } else {
                            return;
                        }

                        if (editVerify(crewCount)) {
                            crew.setCount(Integer.parseInt(crewCount.getText().toString()));
                        } else {
                            return;
                        }
                        crewList.add(crew);
                        dialog.dismiss();
                        initRecList();
                    }
                });

                QMUIRoundButton cancelButton = dialog.findViewById(R.id.crew_cancel);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    private CrewrecAdapter adapter;

    private void initRecList() {
        if (crewList.size() == 0) {
            return;
        } else if (crewList.size() == 1) {
            adapter = new CrewrecAdapter(this, crewList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            crewRecview.setLayoutManager(layoutManager);
            crewRecview.setAdapter(adapter);
            crewRecview.setItemAnimator(new DefaultItemAnimator());
            crewRecview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            tip.setVisibility(View.GONE);
            crewRecview.setVisibility(View.VISIBLE);
        } else if (crewList.size() > 1) {
            adapter.notifyDataSetChanged();
        }
    }

    private boolean editVerify(final MaterialEditText materialEdit) {
        if (materialEdit.getText().toString().equals("")) {
            materialEdit.setError("请输入" + materialEdit.getHint().toString());
            return false;
        }
        return true;
    }

    private void showMenuDialog(final TextView tv, final String[] items) {
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setText(items[which]);
                        tv.setTextColor(getResources().getColor(R.color.bank_bg01));
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

}
