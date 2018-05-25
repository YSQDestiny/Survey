package com.cykj.survey.fragment.index;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.bean.IndustryBean;
import com.cykj.survey.bean.Node;
import com.cykj.survey.bean.SimpleTreeAdapter;
import com.cykj.survey.bean.TreeListViewAdapter;
import com.cykj.survey.fragment.industry.WoodenFurnitureFragment;
import com.cykj.survey.model.Industry;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndustryFragment extends BaseFragment{
    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.industry_list)
    ListView mIndustryList;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;
    private String industry;

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
                        showMessagePositiveDialog();
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
                        spikFragment();
                        dialog.dismiss();
                    }
                }).create(mCurrentDialogStyle).show();
    }

    private void spikFragment(){
        switch (industry){
            case "木质家具制造":
                QMUIFragment fragment = new WoodenFurnitureFragment();
                startFragment(fragment);
                break;
            default:
                Toast.makeText(getActivity(),industry,Toast.LENGTH_LONG).show();
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
