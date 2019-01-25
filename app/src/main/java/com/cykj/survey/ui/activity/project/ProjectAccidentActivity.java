package com.cykj.survey.ui.activity.project;

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
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.ui.fragment.adapter.ProjectLocalRecAdapter;
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

    private ProjectLocalRecAdapter adapter;
    private List<ProjectAccident> accidents = Constants.projectAccidentList;

    private AppComponent component;
    private ResultInteractor resultInteractor;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_local);
        ButterKnife.bind(this);
        component = SurveyApplication.get(this).component();
        resultInteractor = component.getResultInteractor();
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
                Intent intent = new Intent(ProjectAccidentActivity.this, ProjectAccidentDescActivity.class);
                intent.putExtra("accident", JSONObject.toJSONString(Constants.projectAccidentList.get(position)));
                startActivity(intent);
                Constants.projectAccidentList.remove(Constants.projectAccidentList.get(position));
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }

    private void postData() {

        String json = JSONObject.toJSONString(Constants.projectAccidentList);

        resultInteractor.postAccident(json, new BaseSubsribe<StringBean>() {
            @Override
            public void onSuccess(StringBean result) {
                if (result.getCode() == 0){
                    showToastShort("保存成功");
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                showToastShort("保存失败");
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
                postData();
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
