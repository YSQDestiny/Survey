package com.cykj.survey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.BasicOptionAdapter;
import com.cykj.survey.model.Area;
import com.cykj.survey.model.Industry;
import com.cykj.survey.model.Options;
import com.cykj.survey.model.OptionsConstants;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.Utils;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.ArrayList;
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

public class OptionsActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.options_rec)
    RecyclerView optionsRec;
    @BindView(R.id.options_rec_layout)
    RelativeLayout optionsRecLayout;
    @BindView(R.id.options_tip)
    RelativeLayout optionsTip;

    private String industry;

    private String area;

    private Handler handler;

    private List<Options> optionsList = new ArrayList<>();

    private BasicOptionAdapter adapter;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);
        handler = new Handler();
        Intent intent = this.getIntent();
        industry = intent.getStringExtra("industry");
        area = intent.getStringExtra("area");
        initTopbar();
        getOptions(industry,area);
    }

    private void initTopbar() {
        topbar.setTitle("查勘选项");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getOptions(String industry,String area){
        String url = Constants.TEST_SERVICE + "/options/getOptions";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("industry", industry)
                .add("area",area)
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                optionsList = JSONObject.parseArray(result.getData(),Options.class);
                handler.post(UIable);
            }
        });
    }

    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private void initView(){
        if (optionsList != null){
            List<Options> data = Utils.insuranceJudge(optionsList, OptionsConstants.INSURANCE);
            adapter = new BasicOptionAdapter(this,data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            optionsRec.setLayoutManager(layoutManager);
            optionsRec.setAdapter(adapter);
            optionsRec.setItemAnimator(new DefaultItemAnimator());
            optionsRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        }else {
            optionsRecLayout.setVisibility(View.GONE);
            optionsTip.setVisibility(View.VISIBLE);
        }
    }
}
