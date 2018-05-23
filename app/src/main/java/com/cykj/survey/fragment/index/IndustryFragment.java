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
import com.cykj.survey.model.Industry;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IndustryFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.industry_list)
    ListView mIndustryList;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_industry_select, null);
        ButterKnife.bind(this, root);
        initTopBar();
        inintData();

        try {
            mAdapter = new SimpleTreeAdapter<IndustryBean>(mIndustryList,getActivity(),mDatas,10);
            mIndustryList.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mIndustryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.expandOrCollapse(position);
                Toast.makeText(getContext(),mDatas.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    private void inintData() {
        String jsonStr = JsonUtil.getJson("industry.json",getActivity());
        Industry industry = JSONObject.parseObject(jsonStr,Industry.class);
        mDatas = industry.getBean();
    }

    private void initTopBar(){
        mTopbar.setTitle("行业选择");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
