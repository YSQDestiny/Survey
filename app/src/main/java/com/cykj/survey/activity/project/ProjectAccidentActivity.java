package com.cykj.survey.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.ProjectLocalRecAdapter;
import com.cykj.survey.fragment.adapter.ReportAdapter;
import com.cykj.survey.model.ProjectAccident;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectAccidentActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.local_rec)
    RecyclerView localRec;

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
        localRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new ReportAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(ProjectAccidentActivity.this,ProjectAccidentDescActivity.class);
                intent.putExtra("accident", JSONObject.toJSONString(Constants.projectAccidentList.get(position)));
                startActivity(intent);
                Constants.projectAccidentList.remove(Constants.projectAccidentList.get(position));
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }

    private void initTopbar(){
        topbar.setTitle("现场查勘");
        topbar.addRightTextButton("添加",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectAccidentActivity.this,ProjectAccidentDescActivity.class);
                startActivity(intent);
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
