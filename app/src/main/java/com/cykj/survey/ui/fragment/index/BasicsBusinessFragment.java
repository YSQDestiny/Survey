package com.cykj.survey.ui.fragment.index;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.ui.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.ui.fragment.adapter.BusinessRecAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.CompanyConstants;
import com.cykj.survey.model.CompanyModel;
import com.cykj.survey.model.OptionsConstants;
import com.cykj.survey.model.ProgressModel;
import com.cykj.survey.model.Record;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DateUtil;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.view.CustomGridView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

public class BasicsBusinessFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.business_edit_name)
    MaterialEditText businessEditName;
    @BindView(R.id.business_edit_companyCode)
    MaterialEditText businessEditCompanyCode;
    @BindView(R.id.business_area_select_text)
    TextView businessAreaSelectText;
    @BindView(R.id.business_area_select)
    RelativeLayout businessAreaSelect;
    @BindView(R.id.business_edit_town)
    MaterialEditText businessEditTown;
    @BindView(R.id.business_edit_addr)
    MaterialEditText businessEditAddr;
    @BindView(R.id.business_edit_linkman)
    MaterialEditText businessEditLinkman;
    @BindView(R.id.business_edit_phoneNumber)
    MaterialEditText businessEditPhoneNumber;
    @BindView(R.id.business_edit_manager)
    MaterialEditText businessEditManager;
    @BindView(R.id.business_edit_viceManager)
    MaterialEditText businessEditViceManager;
    @BindView(R.id.business_edit_safe)
    MaterialEditText businessEditSafe;
    @BindView(R.id.business_edit_client)
    MaterialEditText businessEditClient;
    @BindView(R.id.business_edit_client_contact)
    MaterialEditText businessEditClientContact;
    @BindView(R.id.business_edit_client_contact_phone)
    MaterialEditText businessEditClientContactPhone;
    @BindView(R.id.business_edit_wokerNormal)
    MaterialEditText businessEditWokerNormal;
    @BindView(R.id.business_edit_wokerSpecial)
    MaterialEditText businessEditWokerSpecial;
    @BindView(R.id.business_edit_assets)
    MaterialEditText businessEditAssets;
    @BindView(R.id.business_edit_amount)
    MaterialEditText businessEditAmount;
    @BindView(R.id.grid)
    CustomGridView grid;
    @BindView(R.id.business_season)
    Spinner businessSeason;
    @BindView(R.id.business_add_text)
    TextView businessAddText;
    @BindView(R.id.business_recview)
    RecyclerView businessRecview;
    @BindView(R.id.tip)
    RelativeLayout tip;

    private CityPickerView mPicker = new CityPickerView();

    private Handler handler;

    private Company company;

    private List<String> season = new ArrayList<>();

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business_basics, null);
        ButterKnife.bind(this, root);
        mPicker.init(getActivity());
        initData();
        initTopbar();
        initView();
        businessAreaSelect.setOnClickListener(new View.OnClickListener() {
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
                            company.setProvince(province.getName());
                        }

                        //城市
                        if (city == null) {
                            showToastShort("城市信息获取失败");
                            return;
                        } else {
                            company.setCity(city.getName());
                        }

                        //地区
                        if (district == null) {
                            showToastShort("地区信息获取失败");
                            return;
                        } else {
                            company.setCounty(district.getName());
                        }

                        businessAreaSelectText.setText(province.getName() + "-" + city.getName() + "-" + district.getName());
                        businessAreaSelectText.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }

                    @Override
                    public void onCancel() {
                        showToastShort("操作取消");
                    }
                });
                mPicker.showCityPicker();
            }
        });
        if (CompanyConstants.COMPANY_ID != 0){
            initDataWithView();
        }
        return root;
    }

    private AccidentGridAdapter adapter;
    private BusinessRecAdapter recAdapter;
    private List<Record> recordList = new ArrayList<>();
    private Record record;

    private List<String> insuranceList = new ArrayList<>();

    private List<AccidentGridModel> dataList;

    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        topbar.setTitle("企业信息");

        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        topbar.addRightTextButton("完成", R.id.topbar_right_text_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            postJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        QMUIStatusBarHelper.translucent(getActivity());

    }

    private boolean isNull = false;

    Runnable seccessRun = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存成功");
            popBackStack();
        }
    };

    Runnable failRun = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存失败");
        }
    };

    /**
     * 初始化数据
     */
    private void initData() {
        handler = new Handler();
        company = new Company();
        dataList = new ArrayList<>();
        dataList.add(new AccidentGridModel("财产综合险", false));
        dataList.add(new AccidentGridModel("财产基本险", false));
        dataList.add(new AccidentGridModel("财产一切险", false));
        dataList.add(new AccidentGridModel("雇主责任险", false));
        dataList.add(new AccidentGridModel("团体意外险", false));
        season.add("春");
        season.add("夏");
        season.add("秋");
        season.add("冬");
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * 初始化view
     */
    private void initView() {

        ArrayAdapter<String> geology1_0Adapter;
        geology1_0Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, season);
        spinnerSetAdapter(geology1_0Adapter,businessSeason);

        adapter = new AccidentGridAdapter(getActivity(), dataList);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dataList.get(position).isSelect()) {
                    dataList.get(position).setSelect(false);
                    insuranceList.remove(dataList.get(position).getName());
                } else {
                    dataList.get(position).setSelect(true);
                    insuranceList.add(dataList.get(position).getName());
                }
                adapter.notifyDataSetChanged();
            }
        });

        businessAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecDialog();
            }
        });

    }

    /**
     * 设置view数据
     */
    private void initDataWithView(){
        CompanyModel companyModel = CompanyConstants.CP_MODEL;
        Company compantEntitiy = companyModel.getCompanyEntity();
        recordList = companyModel.getRecords();
        initRecList();

        businessEditName.setText(compantEntitiy.getName());
        businessEditCompanyCode.setText(compantEntitiy.getCompanyCode());
        businessEditAddr.setText(compantEntitiy.getAddr());
        businessAreaSelectText.setText(compantEntitiy.getProvince() + compantEntitiy.getCity() + compantEntitiy.getCounty());
        businessEditLinkman.setText(compantEntitiy.getLinkman());
        businessEditManager.setText(compantEntitiy.getManager());
        businessEditViceManager.setText(compantEntitiy.getViceManager());
        businessEditSafe.setText(compantEntitiy.getSafe());
        businessEditWokerNormal.setText(compantEntitiy.getWokerNormal().toString());
        businessEditWokerSpecial.setText(compantEntitiy.getWokerSpecial().toString());
        businessEditAssets.setText(compantEntitiy.getAssets().toString());
        businessEditAmount.setText(compantEntitiy.getAmount().toString());
        businessEditPhoneNumber.setText(compantEntitiy.getPhoneNumber());
        businessEditClient.setText(compantEntitiy.getClient());
        businessEditClientContact.setText(compantEntitiy.getClientContact());
        businessEditClientContactPhone.setText(compantEntitiy.getClientContactPhone());
        businessEditTown.setText(compantEntitiy.getTown());

    }

    private void postJson() {
        if (CompanyConstants.CP_MODEL != null){
            popBackStack();
            return;
        }
        String url = Constants.TEST_SERVICE + "/company/post";

        CompanyModel companyModel = new CompanyModel();
        Date date = new Date();
        if (editVerify(businessEditName)) {
            company.setName(businessEditName.getText().toString());
        }
        if (editVerify(businessEditCompanyCode)) {
            company.setCompanyCode(businessEditCompanyCode.getText().toString());
        }
        if (editVerify(businessEditAddr)) {
            company.setAddr(businessEditAddr.getText().toString());
        }
        if (editVerify(businessEditLinkman)) {
            company.setLinkman(businessEditLinkman.getText().toString());
        }
        if (editVerify(businessEditManager)) {
            company.setManager(businessEditManager.getText().toString());
        }
        if (editVerify(businessEditViceManager)) {
            company.setViceManager(businessEditViceManager.getText().toString());
        }
        if (editVerify(businessEditSafe)) {
            company.setSafe(businessEditSafe.getText().toString());
        }
        if (editVerify(businessEditWokerNormal)) {
            company.setWokerNormal(Integer.parseInt(businessEditWokerNormal.getText().toString()));
        }
        if (editVerify(businessEditWokerSpecial)) {
            company.setWokerSpecial(Integer.parseInt(businessEditWokerSpecial.getText().toString()));
        }
        if (editVerify(businessEditAssets)) {
            company.setAssets(Integer.parseInt(businessEditAssets.getText().toString()));
        }
        if (editVerify(businessEditAmount)) {
            company.setAmount(Integer.parseInt(businessEditAmount.getText().toString()));
        }
        if (editVerify(businessEditPhoneNumber)) {
            company.setPhoneNumber(businessEditPhoneNumber.getText().toString());
        }
        if (editVerify(businessEditClient)) {
            company.setClient(businessEditClient.getText().toString());
        }
        if (editVerify(businessEditClientContact)) {
            company.setClientContact(businessEditClientContact.getText().toString());
        }
        if (editVerify(businessEditClientContactPhone)) {
            company.setClientContactPhone(businessEditClientContactPhone.getText().toString());
        }
        company.setTown(businessEditTown.getText().toString());
        company.setMakeTime(DateUtil.parseToSQLDate(date, DateUtil.yyyyMMddHHmmss));
        company.setUniqueId(DeviceUtils.getUniqueId(getActivity()));
        if (insuranceList.size() > 0) {
            String insurance = "";
            int i = 0;
            for (String str : insuranceList) {
                if (i == 0) {
                    insurance += str;
                    i++;
                } else {
                    insurance += "," + str;
                }
            }
            OptionsConstants.setINSURANCE(insurance);
            company.setInsurance(insurance);
        }
        String weatherStr = "";
        String seasonStr = businessSeason.getSelectedItem().toString();
        switch (seasonStr){
            case "春":
                weatherStr += "春季：该季节雷电开始频繁，建议定期检查避雷设施的完好性、定期测试防雷接地电阻是否符合要求，将未设点的位置及时设点，将已设点的位置进行检测并保证其设施完好。";
                break;
            case "夏":
                weatherStr += "夏季：该季节降雨量增多，建议及时清掏并修缮排水沟渠、管道，保证车间、办公区、库存、变配电等重要区域的排水能力，定期检查屋顶面是否有破漏并及时修补。配备防洪防汛物资，如沙袋、铁锹、排水泵等。";
                break;
            case "秋":
                weatherStr += "秋、冬季：该季节空气湿度、温度开始降低，易出现火灾，建议企业配置足量消防设施，并定期检查其功能完好性，保证各火灾报警系统处于良好的工作状态。督促员工进入防火防爆区域时正确穿戴防静电工作服并提前进行静电释放，更需定期检查静电跨接措施，做好防火防静电。";
                break;
            case "冬":
                weatherStr += "秋、冬季：该季节空气湿度、温度开始降低，易出现火灾，建议企业配置足量消防设施，并定期检查其功能完好性，保证各火灾报警系统处于良好的工作状态。督促员工进入防火防爆区域时正确穿戴防静电工作服并提前进行静电释放，更需定期检查静电跨接措施，做好防火防静电。";
                break;
            default:
                break;
        }
        company.setWeatherStr(weatherStr);
        company.setGeologyStr("");
        companyModel.setCompanyEntity(company);
        if (recordList.size() > 0) {
            companyModel.setRecords(recordList);
        }
        if (isNull) {
            return;
        } else {
            showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
            CompanyConstants.CP_MODEL = companyModel;
            String json = JSONObject.toJSONString(companyModel);

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

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    tipDialogDismiss();
                    String resultStr = response.body().string();
                    ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                    if (result.getCode() == 0) {
                        CompanyConstants.COMPANY_ID = Long.parseLong(result.getData());
                        ProgressModel.COMPANY_1 = true;
                        handler.post(seccessRun);
                    } else {
                        handler.post(failRun);
                        return;
                    }
                }
            });
        }

    }

    private QMUIDialog dialog;

    private void showRecDialog() {
        record = null;
        record = new Record();
        dialog = null;
        dialog = new QMUIDialog(getActivity(), R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.layout_dialog_rec_add);
        dialog.setCanceledOnTouchOutside(false);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dialog.onWindowAttributesChanged(lp);
        final MaterialEditText recReson = dialog.findViewById(R.id.rec_reason);
        final MaterialEditText recAmount = dialog.findViewById(R.id.rec_amount);
        final MaterialEditText recTime = dialog.findViewById(R.id.rec_time);

        QMUIRoundButton confirmButton = dialog.findViewById(R.id.rec_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editVerify(recReson)) {
                    record.setReason(recReson.getText().toString());
                } else {
                    return;
                }
                if (editVerify(recAmount)) {
                    record.setAmount(recAmount.getText().toString());
                } else {
                    return;
                }
                if (editVerify(recTime)) {
                    record.setTime(recTime.getText().toString());
                } else {
                    return;
                }
                recordList.add(record);
                dialog.dismiss();
                initRecList();
            }
        });

        QMUIRoundButton cancelButton = dialog.findViewById(R.id.rec_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initRecList() {
        if (recordList.size() == 0) {
            return;
        } else if (recordList.size() == 1) {
            recAdapter = new BusinessRecAdapter(getActivity(), recordList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            businessRecview.setLayoutManager(layoutManager);
            businessRecview.setAdapter(recAdapter);
            businessRecview.setItemAnimator(new DefaultItemAnimator());
            businessRecview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            tip.setVisibility(View.GONE);
            businessRecview.setVisibility(View.VISIBLE);
            businessRecview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        } else if (recordList.size() > 1) {
            recAdapter.notifyDataSetChanged();
        }
    }

    private boolean editVerify(final MaterialEditText materialEdit) {
        if (materialEdit.getText().toString().equals("")) {
            materialEdit.setError("请输入" + materialEdit.getHint().toString());
            isNull = true;
            return false;
        }
        isNull = false;
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}