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
import com.alibaba.fastjson.TypeReference;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.property.CreatePropertyActivity;
import com.cykj.survey.activity.property.ProertyDetailActivirt;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.ProPertyAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Property;
import com.cykj.survey.model.PropertyArea;
import com.cykj.survey.model.PropertyOption;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.util.LocalDataCache;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Widget(group = Group.Home, name = "物业", iconRes = R.mipmap.icon_fragment_property)
public class PropertyListFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.property_recycler)
    RecyclerView propertyRecycler;

    private Handler handler;

    private List<Property> properties;

    private ProPertyAdapter adapter;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_property_list, null);
        handler = new Handler();
        ButterKnife.bind(this,root);
        handler = new Handler();
        initTopbar();
        getList();
        return root;
    }

    /**
     * 初始化标题栏
     */
    private void initTopbar() {

        topbar.setTitle("物业查勘列表");
        topbar.addRightTextButton("新建", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePropertyActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getList(){
        Object object = LocalDataCache.getLoaclData(getContext(),DeviceUtils.getUniqueId(getContext()) + "property");
        if (object == null){
            String url = Constants.TEST_SERVICE + "/property/getList";

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
                        properties = JSONObject.parseArray(result.getData(),Property.class);
                        handler.post(UIable);
                        if (properties != null){
                            LocalDataCache.save(getContext(),properties,DeviceUtils.getUniqueId(getContext()) + "property");
                        }
                    }
                }
            });
        }else {
            properties = (List<Property>) object;
            handler.post(UIable);
        }

        Object object2 = LocalDataCache.getLoaclData(getContext(),"propertyArea");
        if (object2 == null){
            String url = Constants.TEST_SERVICE + "/property/getAreaAndOptions";
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                    if (result.getCode() == 0){
                        Map<PropertyArea,List<PropertyOption>> optionsMap = JSONObject.parseObject(result.getData(),new TypeReference<Map<PropertyArea, List<PropertyOption>>>(){});
                        if (optionsMap != null){
                            LocalDataCache.save(getContext(),optionsMap,"propertyArea");
                        }
                    }
                }
            });
        }
    }

    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private void initView() {

        if (properties != null){
            adapter = new ProPertyAdapter(getActivity(),properties);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            propertyRecycler.setLayoutManager(layoutManager);
            propertyRecycler.setAdapter(adapter);
            propertyRecycler.setItemAnimator(new DefaultItemAnimator());
            propertyRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            adapter.setOnItemClickListener(new ProPertyAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), ProertyDetailActivirt.class);
                    intent.putExtra("id",properties.get(position).getId().toString());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }
}
