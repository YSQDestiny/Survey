package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.fragment.adapter.BusinessRecAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.CompanyModel;
import com.cykj.survey.model.Record;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DateUtil;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

public class BusinessFragment extends BaseFragment {

    @BindView(R.id.grid)
    CustomGridView mGrid;
    @BindView(R.id.business_recview)
    RecyclerView mRecview;
    @BindView(R.id.business_add_text)
    TextView addText;
    @BindView(R.id.tip)
    RelativeLayout tip;

    private Handler handler;

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;

    @BindView(R.id.business_edit_name)
    EditText businessEditName;

    @BindView(R.id.business_edit_addr)
    EditText businessEditAddr;

    @BindView(R.id.business_edit_contacts)
    EditText businessEditContacts;

    @BindView(R.id.business_edit_manager)
    EditText businessEditManager;

    @BindView(R.id.business_edit_deputy_manager)
    EditText businessEditDeputyManager;

    @BindView(R.id.business_edit_safe)
    EditText businessEditSafe;

    @BindView(R.id.business_edit_worker)
    EditText businessEditWorker;

    @BindView(R.id.business_edit_special_worker)
    EditText businessEditSpecialWorker;

    @BindView(R.id.business_edit_assets)
    EditText businessEditAssets;

    @BindView(R.id.business_edit_amount)
    EditText businessEditAmount;

    @BindView(R.id.business_edit_coverage)
    EditText businessEditCoverage;


    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initData();
        initView();
        handler = new Handler();
        return root;
    }

    private List<AccidentGridModel> dataList;

    /**
     * 初始化数据
     */
    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new AccidentGridModel("综合险", false));
        dataList.add(new AccidentGridModel("基本险", false));
        dataList.add(new AccidentGridModel("一切险", false));
        dataList.add(new AccidentGridModel("雇主责任险", false));
        dataList.add(new AccidentGridModel("公众责任险", false));
    }

    private AccidentGridAdapter adapter;
    private BusinessRecAdapter recAdapter;
    private List<Record> recordList = new ArrayList<>();
    private boolean isFirst = true;
    private Record record;

    private List<String> insuranceList = new ArrayList<>();
    /**
     * 初始化View
     */
    private void initView() {
        adapter = new AccidentGridAdapter(getActivity(), dataList);
        mGrid.setAdapter(adapter);
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeEditTextDialog();
            }
        });

    }

    private void showTimeEditTextDialog(){
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("出险时间")
                .setPlaceholder("在此输入出险时间")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0){
                            record = new Record();
                            record.setTime(text.toString());
                            dialog.dismiss();
                            showAmountEditTextDialog();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showAmountEditTextDialog(){
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("损失金额")
                .setPlaceholder("在此输入损失金额")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0){
                            record.setAmount(text.toString());
                            dialog.dismiss();
                            showReasonEditTextDialog();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showReasonEditTextDialog(){
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("出险原因")
                .setPlaceholder("在此输入出险原因")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0){
                            record.setReason(text.toString());
                            dialog.dismiss();
                            initRecView();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void initRecView(){
        if (isFirst){
            recordList.add(record);
            record = null;
            recAdapter = new BusinessRecAdapter(getActivity(),recordList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecview.setLayoutManager(layoutManager);
            mRecview.setAdapter(recAdapter);
            mRecview.setItemAnimator(new DefaultItemAnimator());
            mRecview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            tip.setVisibility(View.GONE);
            mRecview.setVisibility(View.VISIBLE);
            isFirst = false;
        }else {
            recordList.add(record);
            recAdapter.notifyDataSetChanged();
            record = null;
        }
    }

    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        mTopbar.setTitle("企业信息");

        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopbar.addRightTextButton("下一页", R.id.topbar_right_text_button)
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

        QMUIStatusBarHelper.translucent(getActivity());

    }

    /**
     * 提交json数据
     *
     * @throws IOException
     */
    private void postJson() throws IOException {
        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
        String url = Constants.TEST_SERVICE + "/company/post";

        CompanyModel companyModel = new CompanyModel();
        Date date = new Date();
        Company company = new Company();
        company.setName(businessEditName.getText().toString());
        company.setAddr(businessEditAddr.getText().toString());
        company.setLinkman(businessEditContacts.getText().toString());
        company.setManager(businessEditManager.getText().toString());
        company.setViceManager(businessEditDeputyManager.getText().toString());
        company.setSafe(businessEditSafe.getText().toString());
        company.setWokerNormal(Integer.parseInt(businessEditWorker.getText().toString()));
        company.setWokerSpecial(Integer.parseInt(businessEditSpecialWorker.getText().toString()));
        company.setMakeTime(DateUtil.parseToSQLDate(date, DateUtil.yyyyMMddHHmmss));
        company.setUniqueId(DeviceUtils.getUniqueId(getActivity()));
        company.setAssets(Integer.parseInt(businessEditAssets.getText().toString()));
        company.setAmount(Integer.parseInt(businessEditAmount.getText().toString()));
        company.setCoverage(businessEditCoverage.getText().toString());
        if (insuranceList.size() > 0){
            String insurance = "";
            int i = 0;
            for (String str : insuranceList){
                if (i == 0){
                    insurance += str;
                    i++;
                }else {
                    insurance += "," + str;
                }
            }
            company.setInsurance(insurance);
        }
        if (recordList.size() > 0){
            companyModel.setRecords(recordList);
        }
        companyModel.setCompanyEntity(company);
        String json = JSONObject.toJSONString(companyModel);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json", json)
                .build();

        Request request = new Request.Builder()
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
                    handler.post(seccessRun);
                    Constants constants = new Constants();
                    constants.setReportId(Long.parseLong(result.getData()));
//                    QMUIFragment fragment = new LicenseUploadFragment();
//                    startFragmentAndDestroyCurrent(fragment);
                    Intent intent = new Intent(getActivity(), PhotoUploadActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    handler.post(failRun);
                    return;
                }
            }
        });

    }

    Runnable seccessRun = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存成功");
        }
    };

    Runnable failRun = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存失败");
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
