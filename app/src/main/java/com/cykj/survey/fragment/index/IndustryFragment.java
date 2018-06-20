package com.cykj.survey.fragment.index;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.bean.IndustryBean;
import com.cykj.survey.bean.Node;
import com.cykj.survey.bean.SimpleTreeAdapter;
import com.cykj.survey.bean.TreeListViewAdapter;
import com.cykj.survey.fragment.industry.TimberProcessingFragment;
import com.cykj.survey.fragment.industry.WoodenFurnitureFragment;
import com.cykj.survey.model.Industry;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
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

public class IndustryFragment extends BaseFragment{
    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.industry_list)
    ListView mIndustryList;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;
    private String industry;

    private String postIndustry = "";

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_industry_select, null);
        ButterKnife.bind(this, root);
        initTopBar();
        inintData();
        QMUIStatusBarHelper.translucent(getActivity());
        try {
            mAdapter = new SimpleTreeAdapter<IndustryBean>(mIndustryList,getActivity(),mDatas,0);
            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()){
                        industry = node.getName();
                        postIndustry += industry;
                        showMessagePositiveDialog();
                    }else {
                        if (node.getName().equals("常用行业")){
                            return;
                        }
                        postIndustry += node.getName();
                    }
                }
            });
            mIndustryList.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void showMessagePositiveDialog(){
        new QMUIDialog.MessageDialogBuilder(getActivity())
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

                        try {
                            postIndustry();
                        } catch (IOException e) {
                            e.printStackTrace();
                    }
                        dialog.dismiss();
                    }
                }).create(mCurrentDialogStyle).show();
    }

    private void postIndustry() throws IOException{
        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("提交中，请稍等")
                .create();
        tipDialog.show();
        String url = Constants.TEST_SERVICE + "/company/industry";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("companyId", String.valueOf(Constants.REPORT_ID))
                .add("industry",postIndustry)
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
                tipDialog.dismiss();
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                if (result.getCode() == 0){
                    spikFragment();
                }else {
                    return;
                }
            }
        });
    }

    private void spikFragment(){
        switch (industry){
            case "木质家具制造":
                QMUIFragment fragment = new WoodenFurnitureFragment();
                startFragmentAndDestroyCurrent(fragment);
                break;
            case "木材加工企业":
                QMUIFragment wood_fragment = new TimberProcessingFragment();
                startFragmentAndDestroyCurrent(wood_fragment);
                break;
            default:

                break;
        }
    }

    private void inintData() {
        String jsonStr = JsonUtil.getJson("industry.json",getActivity());
        Industry industry = JSONObject.parseObject(jsonStr,Industry.class);
        mDatas = industry.getBean();
    }

    private void initTopBar(){
        mTopbar.setTitle("行业选择");

        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
