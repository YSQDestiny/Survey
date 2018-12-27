package com.cykj.survey.fragment.index;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.project.ProjectGeologyActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.model.ProjectModel;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DateUtil;
import com.cykj.survey.util.DeviceUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProjectFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_area)
    TextView projectArea;
    @BindView(R.id.projiect_edit_addr)
    EditText projiectEditAddr;
    @BindView(R.id.projiect_edit_construction)
    EditText projiectEditConstruction;
    @BindView(R.id.project_type_select)
    TextView projectTypeSelect;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project, null);
        ButterKnife.bind(this, root);
        initTopbar();
        projectTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog();
            }
        });
        return root;
    }

    private void showMenuDialog() {
        final String[] items = new String[]{"公路工程", "轨道交通", "房屋建筑工程", "水利工程","航电工程"};
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        projectTypeSelect.setText(items[which]);
                        projectTypeSelect.setTextColor(getResources().getColor(R.color.qmui_config_color_black));
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void postProjectInfo() {
        ProjectModel projectModel = new ProjectModel();

//        if (isNull(projectName)) {
//            showToastShort("请输入工程名称");
//            return;
//        }
//        if (isNull(projectArea)) {
//            showToastShort("请输入所属地区信息");
//            return;
//        }
//        if (isNull(projectDepartment)) {
//            showToastShort("请输入工程项目部");
//            return;
//        }
//        if (isNull(projectTarget)) {
//            showToastShort("请输入工程标的信息");
//            return;
//        }
//        if (projectType.getText().toString().equals("点击选择工程类型")) {
//            showToastShort("请选择工程类型");
//            return;
//        }
//        if (isNull(projectStaring)) {
//            showToastShort("请输入工程起点");
//            return;
//        }
//        if (isNull(projectEnding)) {
//            showToastShort("请输入工程终点");
//            return;
//        }
//        if (isNull(projectLength)) {
//            showToastShort("请输入工程长度");
//            return;
//        }
//
//        projectModel.setName(getEditString(projectName));
//        projectModel.setArea(getEditString(projectArea));
//        projectModel.setDepartment(getEditString(projectDepartment));
//        projectModel.setProject_target(getEditString(projectTarget));
//        projectModel.setType(projectType.getText().toString());
//        projectModel.setStart(getEditString(projectStaring));
//        projectModel.setEnding(getEditString(projectEnding));
//        projectModel.setLength(Double.parseDouble(getEditString(projectLength)));
//        projectModel.setUniqueId(DeviceUtils.getUniqueId(getContext()));
//        projectModel.setMakeTime(DateUtil.parseToSQLDate(new Date(), DateUtil.yyyyMMdd));

        String projectStr = JSONObject.toJSONString(projectModel);

        String url = Constants.TEST_SERVICE + "/project/post";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json", projectStr)
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
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                if (result.getCode() == 0) {
                    ProjectConstants.PROJECT_ID = Long.parseLong(result.getData());
                }
            }
        });
    }

    private void initTopbar() {
        topbar.setTitle("项目信息");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                postProjectInfo();
            popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
