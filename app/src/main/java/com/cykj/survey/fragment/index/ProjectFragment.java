package com.cykj.survey.fragment.index;

import android.app.Dialog;
import android.content.DialogInterface;
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
import com.cykj.survey.fragment.project.BridgeAndTunnelFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Industry;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

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

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project, null);
        ButterKnife.bind(this, root);
        initTopbar();
        projectTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog();
            }
        });
        return root;
    }

    private void showMenuDialog(){
        final String[] items = new String[]{"公路工程","轨道交通","房屋建筑工程","水利，水电与航道工程"};
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        projectType.setText(items[which]);
                        projectType.setTextColor(getResources().getColor(R.color.qmui_config_color_black));
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }


    private void initTopbar() {
        topbar.setTitle("工程信息");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QMUIFragment fragment = new BridgeAndTunnelFragment();
                startFragmentAndDestroyCurrent(fragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
