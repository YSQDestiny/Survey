package com.cykj.survey.fragment.index;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Company;
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

@Widget(group = Group.Home, name = "企财险", iconRes = R.mipmap.icon_fragment_business)
public class ReportListFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.options_recycler)
    RecyclerView optionsRecycler;

    private List<Company> companies;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_report_list, null);
        ButterKnife.bind(this, root);
        getList();

        return root;
    }

    private void getList(){

        String url = "http://2f6bbg.natappfree.cc/company/getList";

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
                    companies = JSONObject.parseArray(result.getData(),Company.class);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
