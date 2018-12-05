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
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.ReportAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;


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

@Widget(group = Group.Home, name = "企财", iconRes = R.mipmap.icon_fragment_business)
public class ReportListFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.options_recycler)
    RecyclerView mRecycler;

    private Handler handler;

    private ReportAdapter adapter;

    private List<Company> companies;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_report_list, null);
        ButterKnife.bind(this, root);
        initTopbar();
        handler = new Handler();
        getList();
        return root;
    }

    private void initView() {

        if (companies != null){
            adapter = new ReportAdapter(getActivity(),companies);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecycler.setLayoutManager(layoutManager);
            mRecycler.setAdapter(adapter);
            mRecycler.setItemAnimator(new DefaultItemAnimator());
            mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            adapter.setOnItemClickListener(new ReportAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), ReportDetailsActivity.class);
                    intent.putExtra("id",companies.get(position).getId().toString());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }

    }

    private void initTopbar(){
        topbar.setTitle("报告列表");
        topbar.addRightTextButton("新建",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QMUIFragment fragment = new CompanyIndexFragment();
                startFragment(fragment);
            }
        });
    }

    private void getList(){
        final QMUITipDialog tipDialog;
        tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请稍等...")
                .create();
        tipDialog.show();
//        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
        String url = Constants.TEST_SERVICE + "/company/getList";

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
                tipDialog.dismiss();
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    companies = JSONObject.parseArray(result.getData(),Company.class);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }
}
