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
import com.cykj.survey.activity.power.HydroActivity;
import com.cykj.survey.activity.power.HydroDetailActivirty;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.HydroListAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Hydro;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
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

@Widget(group = Group.Home, name = "能源", iconRes = R.mipmap.icon_fragment_hydropower_station)
public class HydropowerFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_recycler)
    RecyclerView hydroRecycler;

    private Handler handler;
    private List<Hydro> hydroList;
    private HydroListAdapter adapter;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hydro_list, null);
        ButterKnife.bind(this, root);
        handler = new Handler();
        initTopbar();
        getList();
        return root;
    }

    private void initTopbar() {
        topbar.setTitle("报告列表");
        topbar.addRightTextButton("新建",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HydroActivity.class);
                getActivity().startActivity(intent);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }

    private void getList(){
        String url = Constants.TEST_SERVICE + "/hydro/getList";

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
                    hydroList = JSONObject.parseArray(result.getData(),Hydro.class);
                    handler.post(UIable);
                }
            }
        });
    }

    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private void initView() {

        if (hydroList != null){
            adapter = new HydroListAdapter(getActivity(),hydroList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            hydroRecycler.setLayoutManager(layoutManager);
            hydroRecycler.setAdapter(adapter);
            hydroRecycler.setItemAnimator(new DefaultItemAnimator());
            hydroRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            adapter.setOnItemClickListener(new HydroListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), HydroDetailActivirty.class);
                    intent.putExtra("id",hydroList.get(position).getId().toString());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }

    }
}
