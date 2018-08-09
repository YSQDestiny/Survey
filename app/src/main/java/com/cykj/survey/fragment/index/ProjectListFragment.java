package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.ReportDetailsActivity;
import com.cykj.survey.activity.project.ProjectActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.ProjectListAdapter;
import com.cykj.survey.fragment.adapter.ReportAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.ProjectModel;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Widget(group = Group.Home, name = "工程险", iconRes = R.mipmap.icon_fragment_engineering)
public class ProjectListFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.projectr_recycler)
    RecyclerView projectrRecycler;

    private List<ProjectModel> projects;
    private Handler handler;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project_list, null);
        ButterKnife.bind(this, root);
        handler = new Handler();
        initTopbar();
        getData();
        return root;
    }

    private void initTopbar() {

        topbar.setTitle("报告列表");
        topbar.addRightTextButton("新建",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProjectActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void getData(){
        String url = Constants.TEST_SERVICE + "/project/getList";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("uniqueId", DeviceUtils.getUniqueId(getContext()))
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    projects = JSONObject.parseArray(result.getData(),ProjectModel.class);
                    handler.post(uiable);
                }

            }
        });
    }

    Runnable uiable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private ProjectListAdapter adapter;

    private void initView() {

        if (projects != null){
            adapter = new ProjectListAdapter(getActivity(),projects);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            projectrRecycler.setLayoutManager(layoutManager);
            projectrRecycler.setAdapter(adapter);
            projectrRecycler.setItemAnimator(new DefaultItemAnimator());
            projectrRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            adapter.setOnItemClickListener(new ReportAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
//                    Intent intent = new Intent(getActivity(), ReportDetailsActivity.class);
//                    intent.putExtra("id",projects.get(position).getId().toString());
//                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
