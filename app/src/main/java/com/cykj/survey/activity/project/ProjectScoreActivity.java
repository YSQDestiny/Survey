package com.cykj.survey.activity.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectScoreActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.score_a1)
    EditText scoreA1;
    @BindView(R.id.score_a2)
    EditText scoreA2;
    @BindView(R.id.score_a3)
    EditText scoreA3;
    @BindView(R.id.score_a4)
    EditText scoreA4;
    @BindView(R.id.score_a5)
    EditText scoreA5;
    @BindView(R.id.score_a6)
    EditText scoreA6;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_score);
        ButterKnife.bind(this);
    }

    private void initTopbar() {
        topbar.setTitle("工程评分");
        topbar.addRightTextButton("提交", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postData() {

        showToastShort("提交成功");

    }


}
