package com.cykj.survey.activity.property;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.PropertyAreaAdapter;
import com.cykj.survey.model.PropertyArea;
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

public class PropertyAreaActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.area_rec)
    RecyclerView areaRec;

    private Handler handler;

    private List<PropertyArea> areaList = new ArrayList<>();

    private PropertyAreaAdapter areaAdapter;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_area);
        ButterKnife.bind(this);
        handler = new Handler();
        initTopbar();
        getArea();
    }

    private void initTopbar() {
        topbar.setTitle("区域选择");
    }

    private void initView(){
        if (areaList != null){
            areaAdapter = new PropertyAreaAdapter(this,areaList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            areaRec.setLayoutManager(layoutManager);
            areaRec.setAdapter(areaAdapter);
            areaRec.setItemAnimator(new DefaultItemAnimator());
            areaRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            areaAdapter.setOnItemClickListener(new PropertyAreaAdapter.OnItemClickListener() {
                @Override
                public void onClick(int postion) {
                    PropertyArea area = areaList.get(postion);
                    String str = JSONObject.toJSONString(area);
                    Intent intent = new Intent(PropertyAreaActivity.this,PropertyOptionActivity.class);
                    intent.putExtra("area",str);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }
    }


    private void getArea(){
        String url = Constants.TEST_SERVICE + "/property/getArea";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                areaList = JSONObject.parseArray(result.getData(), PropertyArea.class);
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
}
