package com.cykj.survey.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.MapProjectActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("工程评分");
        topbar.addRightTextButton("提交", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
//        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void postData() {

        int a1 = Integer.parseInt(scoreA1.getText().toString());
        int a2 = Integer.parseInt(scoreA2.getText().toString());
        int a3 = Integer.parseInt(scoreA3.getText().toString());
        int a4 = Integer.parseInt(scoreA4.getText().toString());
        int a5 = Integer.parseInt(scoreA5.getText().toString());
        int a6 = Integer.parseInt(scoreA6.getText().toString());

        Integer score = a1 + a2 + a3 + a4 + a5 + a6;

        String level = "";

        if (score >= 14){
            level = "等级Ⅳ (极高风险)";
        }else if (score < 13 & score >= 8){
            level = "等级Ⅲ (高度风险)";
        }else if (score < 8 & score >= 5){
            level = "等级II (中度风险)";
        }else if (score < 4 & score >= 0){
            level = "等级I  (低度风险)";
        }

        String url = Constants.TEST_SERVICE + "/project/postScore";

        OkHttpClient client = new OkHttpClient();

        Long projectId = Constants.PROJECT_ID;

        RequestBody body = new FormBody.Builder()
                .add("projectId",projectId.toString())
                .add("score", score.toString())
                .add("level",level)
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
                    finish();
                }
            }
        });
    }


}
