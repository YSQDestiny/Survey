package com.cykj.survey.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.MapProjectActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.ProjectLocalRecAdapter;
import com.cykj.survey.model.ProjectAccident;
import com.cykj.survey.model.ResultModel;
import com.qmuiteam.qmui.widget.QMUITopBar;

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

public class ProjectAccidentActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.local_rec)
    RecyclerView localRec;
    @BindView(R.id.project_accident_btn)
    Button projectAccidentBtn;

    private ProjectLocalRecAdapter adapter;
    private List<ProjectAccident> accidents = Constants.projectAccidentList;


    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_local);
        ButterKnife.bind(this);
        initTopbar();

        adapter = new ProjectLocalRecAdapter(this, accidents);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        localRec.setLayoutManager(layoutManager);
        localRec.setAdapter(adapter);
        localRec.setItemAnimator(new DefaultItemAnimator());
        localRec.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new ProjectLocalRecAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                showToastLong("ssssss");
                Intent intent = new Intent(ProjectAccidentActivity.this, ProjectAccidentDescActivity.class);
                intent.putExtra("accident", JSONObject.toJSONString(Constants.projectAccidentList.get(position)));
                startActivity(intent);
                Constants.projectAccidentList.remove(Constants.projectAccidentList.get(position));
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        projectAccidentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    private void postData() {

        String url = Constants.TEST_SERVICE + "/project/postAccident";

        String json = JSONObject.toJSONString(Constants.projectAccidentList);

        OkHttpClient client = new OkHttpClient();

        Long projectId = Constants.PROJECT_ID;

        RequestBody body = new FormBody.Builder()
                .add("accidentStr",json)
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
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    Intent intent = new Intent(ProjectAccidentActivity.this, ProjectScoreActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initTopbar() {
        topbar.setTitle("现场查勘");
        topbar.addRightTextButton("添加", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectAccidentActivity.this, ProjectAccidentDescActivity.class);
                startActivity(intent);
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accidents = null;
        accidents = Constants.projectAccidentList;
        adapter.notifyDataSetChanged();
    }
}
