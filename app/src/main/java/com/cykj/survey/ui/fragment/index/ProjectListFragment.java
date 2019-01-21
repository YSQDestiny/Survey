package com.cykj.survey.ui.fragment.index;

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
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.ResultBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.ui.activity.project.ProjectReportDetailAccivyty;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.ui.fragment.adapter.ProjectListAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.ProjectModel;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.util.HttpUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscription;

@Widget(group = Group.Home, name = "工程", iconRes = R.mipmap.icon_fragment_engineering)
public class ProjectListFragment extends BaseFragment {

    private AppComponent component;
    private ResultInteractor resultInteractor;

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

        //获取到AppComponent组件
        component = SurveyApplication.get(getActivity()).component();
        //通过AppComponent拿到ResultInteractor
        resultInteractor = component.getResultInteractor();


        handler = new Handler();
        initTopbar();
        getData();
        initView();
        return root;
    }

    private void initTopbar() {
        topbar.setTitle("报告列表");
        topbar.addRightTextButton("新建",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ProjectActivity.class);
//                getActivity().startActivity(intent);
            BaseFragment baseFragment = new ProjectIndexFragment();
            startFragment(baseFragment);
            }
        });
    }

    private void getData(){

        String uniqueId = DeviceUtils.getUniqueId(getActivity());

        Subscription subscription = resultInteractor.getProjectList(uniqueId, new BaseSubsribe<ResultBean>() {
            @Override
            public void onSuccess(ResultBean result) {
               projects = result.getData();
               initView();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                showToastShort("列表获取失败");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
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
            adapter.setOnItemClickListener(new ProjectListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), ProjectReportDetailAccivyty.class);
                    intent.putExtra("id",projects.get(position).getId().toString());
                    startActivity(intent);
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
