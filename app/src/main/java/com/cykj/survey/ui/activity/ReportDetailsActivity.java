package com.cykj.survey.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.ui.fragment.adapter.BusinessRecAdapter;
import com.cykj.survey.ui.fragment.adapter.RiskAdapter;
import com.cykj.survey.model.Accident;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.CompanyModel;
import com.cykj.survey.model.Record;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
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

public class ReportDetailsActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.report_details_name)
    TextView detailsName;
    @BindView(R.id.report_details_addr)
    TextView detailsAddr;
    @BindView(R.id.report_details_contacts)
    TextView detailsContacts;
    @BindView(R.id.report_details_safe)
    TextView detailsSafe;
    @BindView(R.id.report_details_insurance)
    TextView detailsInsurance;
    @BindView(R.id.report_details_coverage)
    TextView detailsCoverage;
    @BindView(R.id.report_details_manager)
    TextView detailsManager;
    @BindView(R.id.report_details_vicemanager)
    TextView detailsVicemanager;
    @BindView(R.id.report_details_assets)
    TextView detailsAssets;
    @BindView(R.id.report_details_amount)
    TextView detailsAmount;
    @BindView(R.id.report_details_woker_normal)
    TextView detailsWokerNormal;
    @BindView(R.id.report_details_woker_special)
    TextView detailsWokerSpecial;
    @BindView(R.id.report_details_business_img)
    ImageView detailsBusinessImg;
    @BindView(R.id.report_details_industry_img)
    ImageView detailsIndustryImg;
    @BindView(R.id.report_details_system_img)
    ImageView detailsSystemImg;
    @BindView(R.id.report_details_recoding)
    RecyclerView detailsRecoding;
    @BindView(R.id.report_details_risk_code)
    RecyclerView detailsRiskCode;
    @BindView(R.id.recoding_tip)
    RelativeLayout recodingTip;
    @BindView(R.id.risk_tip)
    RelativeLayout riskTip;

    private Handler handler;

    private QMUITipDialog tipDialog;

    private CompanyModel company;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        handler = new Handler();
        initTopbar();
        getData(id);

    }

    private void initTopbar() {
        topbar.setTitle("报告详情");
        topbar.addRightTextButton("下载",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadReport();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void downloadReport(){
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("下载报告");
//        progressDialog.setCancelable(false);
//        progressDialog.show();

        String url = Constants.TEST_SERVICE + "/company/exportDoc?id=" +company.getCompanyEntity().getId();
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
//        DownloadUtil.get().download(url,"download",company.getCompanyEntity().getName() +"报告",new DownloadUtil.OnDownloadListener(){
//
//            @Override
//            public void onDownloadSuccess() {
//                showToastShort("下载完成");
//            }
//
//            @Override
//            public void onDownloading(int progress) {
//                progressDialog.setMessage("下载中...");
//            }
//
//            @Override
//            public void onDownloadFailed() {
//
//            }
//        });

    }

    private BusinessRecAdapter recAdapter;
    private RiskAdapter riskAdapter;

    private void initView() {

        if (company != null) {
            Company companyentity = company.getCompanyEntity();
            detailsName.setText(companyentity.getName());
            detailsAddr.setText(companyentity.getAddr());
            detailsContacts.setText(companyentity.getLinkman());
            detailsSafe.setText(companyentity.getSafe());
            detailsInsurance.setText(companyentity.getInsurance());
            detailsCoverage.setText(companyentity.getCoverage());
            detailsManager.setText(companyentity.getManager());
            detailsVicemanager.setText(companyentity.getViceManager());
            detailsAssets.setText(companyentity.getAssets().toString());
            detailsAmount.setText(companyentity.getAmount().toString());
            detailsWokerNormal.setText(companyentity.getWokerNormal().toString());
            detailsWokerSpecial.setText(companyentity.getWokerSpecial().toString());
            if (companyentity.getBusinessPhoto() != null) {
                detailsBusinessImg.setImageBitmap(ImgUtil.base64ToBitmap(companyentity.getBusinessPhoto()));
            }
            if (companyentity.getIndustryPhoto() != null) {
                detailsIndustryImg.setImageBitmap(ImgUtil.base64ToBitmap(companyentity.getIndustryPhoto()));
            }
            if (companyentity.getSystemPhoto() != null) {
                detailsSystemImg.setImageBitmap(ImgUtil.base64ToBitmap(companyentity.getSystemPhoto()));
            }

            List<Record> records = company.getRecords();
            if (records == null || records.size() == 0) {
                detailsRecoding.setVisibility(View.GONE);
                recodingTip.setVisibility(View.VISIBLE);
            } else {
                recAdapter = new BusinessRecAdapter(this, records);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                detailsRecoding.setLayoutManager(layoutManager);
                detailsRecoding.setAdapter(recAdapter);
                detailsRecoding.setItemAnimator(new DefaultItemAnimator());
                detailsRecoding.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                detailsRecoding.setNestedScrollingEnabled(false);
            }

            List<Accident> accidents = company.getAccidents();
            if (accidents == null || accidents.size() == 0) {
                detailsRiskCode.setVisibility(View.GONE);
                riskTip.setVisibility(View.VISIBLE);
            } else {
                riskAdapter = new RiskAdapter(this,accidents);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                detailsRiskCode.setLayoutManager(layoutManager);
                detailsRiskCode.setAdapter(riskAdapter);
                detailsRiskCode.setItemAnimator(new DefaultItemAnimator());
                detailsRiskCode.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                detailsRiskCode.setNestedScrollingEnabled(false);
            }

        } else {
            Toast.makeText(this, "数据获取失败！", Toast.LENGTH_SHORT).show();
        }

    }

    Runnable uiAble = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private void getData(String id) {
        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
        String url = Constants.TEST_SERVICE + "/company/getData";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("id", id)
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
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                if (result.getCode() == 0) {
                    company = JSONObject.parseObject(result.getData(), CompanyModel.class);
                }
                handler.post(uiAble);
                tipDialogDismiss();
            }
        });

    }

    public void showTipDialog(String msg, int icon_type) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(icon_type)
                .setTipWord(msg)
                .create();
        tipDialog.show();
    }

    public void tipDialogDismiss() {
        tipDialog.dismiss();
    }
}
