package com.cykj.survey.activity.property;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.bean.IndustryBean;
import com.cykj.survey.bean.Node;
import com.cykj.survey.bean.SimpleTreeAdapter;
import com.cykj.survey.bean.TreeListViewAdapter;
import com.cykj.survey.fragment.adapter.PropertyAreaAdapter;
import com.cykj.survey.model.Industry;
import com.cykj.survey.model.PropertyArea;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.io.IOException;
import java.util.ArrayList;
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

public class PropertyAreaActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.industry_list)
    ListView industryList;


    private Handler handler;

    private List<PropertyArea> areaList = new ArrayList<>();

    private PropertyAreaAdapter areaAdapter;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;

    private String area = "";

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_area);
        ButterKnife.bind(this);
        handler = new Handler();
        initData();
        initTopbar();

        try {
            mAdapter = new SimpleTreeAdapter<IndustryBean>(industryList,this,mDatas,0);
            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()){
                        Node parent = node.getParent();
                        area += parent.getName()+"-";
                        area += node.getName();
                        showMessagePositiveDialog(area);
                    }
                }
            });
            industryList.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void showMessagePositiveDialog(final String areastr){
        area = "";
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("开始查勘")
                .setMessage("确定要开始查勘吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        PropertyArea temp = null;
                        for (PropertyArea area1 : areaList){
                            if (area1.getName().equals(areastr)){
                                temp = area1;
                            }
                        }
                        String str = JSONObject.toJSONString(temp);
                        Intent intent = new Intent(PropertyAreaActivity.this,PropertyOptionActivity.class);
                        intent.putExtra("area",str);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).create(mCurrentDialogStyle).show();
    }

    private void initData() {
        Constants.removeDeduction();
        String jsonStr = JsonUtil.getJson("property.json",this);
        Industry industry = JSONObject.parseObject(jsonStr,Industry.class);
        mDatas = industry.getBean();
        getArea();
    }

    private void initTopbar() {
        topbar.setTitle("区域选择");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constants.deductionList.size() > 0){
            postDeduction(JSONObject.toJSONString(Constants.deductionList));
        }
    }

    private void postDeduction(String json){
        String url = Constants.TEST_SERVICE + "/property/postDeduction";
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("id",Constants.PROPERTY_ID.toString())
                .add("json",json)
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                if(result.getCode() == 0){
                    handler.post(Tipable);
                }
            }
        });

    }

    Runnable Tipable = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存成功");
        }
    };

    private void getArea(){
        String url = Constants.TEST_SERVICE + "/property/getArea";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                areaList = JSONObject.parseArray(result.getData(), PropertyArea.class);
            }
        });
    }


}
