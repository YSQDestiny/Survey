package com.cykj.survey.fragment.index;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.bean.IndustryBean;
import com.cykj.survey.bean.Node;
import com.cykj.survey.bean.SimpleTreeAdapter;
import com.cykj.survey.bean.TreeListViewAdapter;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Industry;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Widget(group = Group.Home, name = "工程险", iconRes = R.mipmap.icon_fragment_engineering)
public class ProjectFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_name)
    EditText projectName;
    @BindView(R.id.project_addr)
    EditText projectAddr;
    @BindView(R.id.project_investors)
    EditText projectInvestors;
    @BindView(R.id.project_investment)
    EditText projectInvestment;
    @BindView(R.id.project_developers)
    EditText projectDevelopers;
    @BindView(R.id.project_developersAddr)
    EditText projectDevelopersAddr;
    @BindView(R.id.project_duration)
    EditText projectDuration;
    @BindView(R.id.project_warrantyDate)
    EditText projectWarrantyDate;
    @BindView(R.id.project_)
    EditText project;
    @BindView(R.id.project_type)
    TextView projectType;
    @BindView(R.id.project_type_select)
    RelativeLayout projectTypeSelect;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initData();
        final Dialog dialog = new Dialog(getActivity(),R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.layout_dialog_project_type_select);
        ListView listView = dialog.findViewById(R.id.project_type_list);
        try {
            mAdapter = new SimpleTreeAdapter<IndustryBean>(listView,getActivity(),mDatas,0);
            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()){
                        projectType.setText(node.getName());
                        projectType.setTextColor(getResources().getColor(R.color.qmui_config_color_black));
                        dialog.dismiss();
                    }
                }
            });
            listView.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        dialog.setCanceledOnTouchOutside(true);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dialog.onWindowAttributesChanged(lp);
        projectTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return root;
    }

    private void initData() {
        String jsonStr = JsonUtil.getJson("projectType.json",getActivity());
        Industry industry = JSONObject.parseObject(jsonStr,Industry.class);
        mDatas = industry.getBean();
    }

    private void initTopbar() {
        topbar.setTitle("工程信息");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
