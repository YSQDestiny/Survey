package com.cykj.survey.activity.power;

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

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.fragment.adapter.CrewrecAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.Crew;
import com.cykj.survey.model.Hydro;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
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

public class HydroActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_edit_name)
    EditText hydroEditName;
    @BindView(R.id.hydro_area_select_text)
    TextView hydroAreaSelectText;
    @BindView(R.id.hydro_area_select)
    RelativeLayout hydroAreaSelect;
    @BindView(R.id.hydro_edit_address)
    EditText hydroEditAddress;
    @BindView(R.id.hydro_edit_year)
    EditText hydroEditYear;
    @BindView(R.id.hydro_form_select_text)
    TextView hydroFormSelectText;
    @BindView(R.id.hydro_form_select)
    RelativeLayout hydroFormSelect;
    @BindView(R.id.hydro_dam_type_text)
    TextView hydroDamTypeText;
    @BindView(R.id.hydro_dam_type)
    RelativeLayout hydroDamType;
    @BindView(R.id.hydro_dam_type_material)
    TextView hydroDamTypeMaterial;
    @BindView(R.id.hydro_dam_material)
    RelativeLayout hydroDamMaterial;
    @BindView(R.id.hydro_edit_axial_length)
    EditText hydroEditAxialLength;
    @BindView(R.id.hydro_edit_hight)
    EditText hydroEditHight;
    @BindView(R.id.hydro_edit_normal_water)
    EditText hydroEditNormalWater;
    @BindView(R.id.hydro_edit_design_water)
    EditText hydroEditDesignWater;
    @BindView(R.id.hydro_edit_check_water)
    EditText hydroEditCheckWater;
    @BindView(R.id.grid)
    CustomGridView grid;
    @BindView(R.id.hydro_crew_type_text)
    TextView hydroCrewTypeText;
    @BindView(R.id.hydro_crew_type)
    RelativeLayout hydroCrewType;
    @BindView(R.id.crew_add_text)
    TextView crewAddText;
    @BindView(R.id.crew_recview)
    RecyclerView crewRecview;
    @BindView(R.id.tip)
    RelativeLayout tip;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;


    private CityPickerView mPicker = new CityPickerView();

    private List<Crew> crewList = new ArrayList<>();

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private List<AccidentGridModel> dataList = new ArrayList<>();

    private Hydro hydro;

    private Map<String, String> useMap = new HashMap<>();

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
        topbar.addRightTextButton("照片采集", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
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

    private AccidentGridAdapter accidentGridAdapteradapter;

    private void initView() {
        hydro = new Hydro();

        initGrid();

        initPicker();

        initMenuSelect();

        initCrewDialog();

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

    private void initGrid() {
        dataList.add(new AccidentGridModel("发电", false));
        dataList.add(new AccidentGridModel("灌溉", false));
        dataList.add(new AccidentGridModel("航运", false));

        accidentGridAdapteradapter = new AccidentGridAdapter(this, dataList);
        grid.setAdapter(accidentGridAdapteradapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dataList.get(position).isSelect()) {
                    dataList.get(position).setSelect(false);
                    useMap.remove(dataList.get(position).getName());
                } else {
                    dataList.get(position).setSelect(true);
                    useMap.put(dataList.get(position).getName(), dataList.get(position).getName());
                }
                accidentGridAdapteradapter.notifyDataSetChanged();
            }
        });
    }

    private void initPicker() {

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
                            hydro.setProvince(province.getName());
                        }

                        //城市
                        if (city == null) {
                            showToastShort("城市信息获取失败");
                            return;
                        } else {
                            hydro.setCity(city.getName());
                        }

                        //地区
                        if (district == null) {
                            showToastShort("地区信息获取失败");
                            return;
                        } else {
                            hydro.setCounty(district.getName());
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
    }

    private void initMenuSelect() {
        final String[] formItem = {"引水式", "坝式", "其他形式"};

        hydroFormSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroFormSelectText, formItem);
            }
        });

        final String[] damItem = {"重力坝", "拱坝", "支墩坝","底格阑珊坝"};
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

        final String[] damMaterial = {"浆砌石", "混凝土"};
        hydroDamMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog(hydroDamTypeMaterial, damMaterial);
            }
        });
    }

    private void initCrewDialog() {
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


    private void setData() {

        if (hydroEditName.getText().toString().equals("")){
            hydroEditName.setError("请输入电站名称");
            return;
        }else{
            hydro.setName(hydroEditName.getText().toString());
        }

        if (hydroEditAddress.getText().toString().equals("")){
            hydroEditAddress.setError("请输入详细地址");
            return;
        }else{
            hydro.setAddr(hydroEditAddress.getText().toString());
        }

        if (hydroEditYear.getText().toString().equals("")){
            hydroEditYear.setError("请输入投产年限");
            return;
        }else{
            hydro.setYear(Integer.parseInt(hydroEditYear.getText().toString()));
        }

        if (hydroFormSelectText.getText().toString().equals("请选择水电站形式")) {
            showToastShort("请选择水电站形式");
            return;
        } else {
            hydro.setForm(hydroFormSelectText.getText().toString());
        }

        if (hydroDamTypeText.getText().toString().equals("请选择大坝类型")) {
            showToastShort("请选择大坝类型");
            return;
        } else {
            hydro.setType(hydroDamTypeText.getText().toString());
        }

        if (hydroDamTypeMaterial.getText().toString().equals("请选择大坝材质")) {
            showToastShort("请选择大坝材质");
            return;
        } else {
            hydro.setMaterial(hydroDamTypeMaterial.getText().toString());
        }

        if (!hydroEditAxialLength.getText().toString().equals("")){
            hydro.setLength(Double.parseDouble(hydroEditAxialLength.getText().toString()));
        }

        if (!hydroEditHight.getText().toString().equals("")){
            hydro.setHigh(Double.parseDouble(hydroEditHight.getText().toString()));
        }

        if (!hydroEditNormalWater.getText().toString().equals("")){
            hydro.setNormal(Double.parseDouble(hydroEditNormalWater.getText().toString()));
        }

        if (!hydroEditDesignWater.getText().toString().equals("")){
            hydro.setDesign(Double.parseDouble(hydroEditDesignWater.getText().toString()));
        }

        if (!hydroEditCheckWater.getText().toString().equals("")){
            hydro.setCheckWater(Double.parseDouble(hydroEditCheckWater.getText().toString()));
        }

        if (useMap.size() > 0) {
            String purpose = "";
            int i = 0;
            for (String str : useMap.keySet()) {
                if (i == 0) {
                    purpose += str;
                    i++;
                } else {
                    purpose += "," + str;
                }
            }
            hydro.setPurpose(purpose);
        } else {
            showToastShort("请选择水电站主要用途");
            return;
        }

        if (hydroCrewTypeText.getText().toString().equals("请选择机组样式")) {
            showToastShort("请选择机组样式");
            return;
        } else {
            hydro.setCrewStyle(hydroCrewTypeText.getText().toString());
        }

        if (crewList.size() > 0) {
            hydro.setCrew(JSONObject.toJSONString(crewList));
        } else {
            showToastShort("请添加机组信息");
            return;
        }

        hydro.setUniqueId(DeviceUtils.getUniqueId(this));

        if (isNull) {
            return;
        } else {
            String url = Constants.TEST_SERVICE + "/hydro/saveHydro";

            String json = JSONObject.toJSONString(hydro);

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("json", json)
                    .build();

            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showToastShort("网络连接失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resultStr = response.body().string();
                    ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                    if (result.getCode() == 0) {
                        Constants.HYDRO_ID = Long.parseLong(result.getData());
                        Constants.HYDRO_ID = Long.parseLong(result.getData());
                        Intent intent = new Intent(HydroActivity.this, TakePhotoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }


    }
}
