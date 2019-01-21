package com.cykj.survey.ui.activity;

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
import com.cykj.survey.ui.fragment.adapter.SurveyOptionsAdapter;
import com.cykj.survey.model.Area;
import com.cykj.survey.model.ResultModel;
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

public class AreaActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.area_rec)
    RecyclerView areaRec;
    @BindView(R.id.area_rec_layout)
    RelativeLayout areaRecLayout;
    @BindView(R.id.area_tip)
    RelativeLayout areaTip;

    private List<Area> areaList = new ArrayList<>();

    private String industry;

    private Handler handler;

    private SurveyOptionsAdapter adapter;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        handler = new Handler();
        Intent intent = this.getIntent();
        industry = intent.getStringExtra("industry");
        initTopbar();
        getArea(industry);

    }

    private void initTopbar() {
        topbar.setTitle("选择区域");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getArea(String industry) {

        String url = Constants.TEST_SERVICE + "/area/getArea";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("industry", industry)
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                areaList = JSONObject.parseArray(result.getData(), Area.class);
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

    private void initView() {
        if (areaList != null){
            List<String> data = new ArrayList<>();
            for (Area area : areaList){
                data.add(area.getName());
            }
            adapter = new SurveyOptionsAdapter(this,data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            areaRec.setLayoutManager(layoutManager);
            areaRec.setAdapter(adapter);
            areaRec.setItemAnimator(new DefaultItemAnimator());
            areaRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            adapter.setOnItemClockListener(new SurveyOptionsAdapter.OnItemClickListener() {
                @Override
                public void onClick(int postion) {
                    Intent intent = new Intent(AreaActivity.this,OptionsActivity.class);
                    intent.putExtra("industry",industry);
                    intent.putExtra("area",areaList.get(postion).getName());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }else {
            areaRecLayout.setVisibility(View.GONE);
            areaTip.setVisibility(View.VISIBLE);
        }
    }
}
