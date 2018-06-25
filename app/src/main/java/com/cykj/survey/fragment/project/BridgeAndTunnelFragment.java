package com.cykj.survey.fragment.project;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BridgeAndTunnelFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_bridge_add)
    TextView projectBridgeAdd;
    @BindView(R.id.bridge_tip)
    RelativeLayout bridgeTip;
    @BindView(R.id.project_bridge_list)
    RecyclerView projectBridgeList;

    private Dialog dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project_bridge_and_tunnel, null);
        ButterKnife.bind(this, root);
        projectBridgeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity(),R.style.edit_AlertDialog_style);
                dialog.setContentView(R.layout.layout_bridge_add_dialog);
                dialog.setCanceledOnTouchOutside(true);
                Window w = dialog.getWindow();
                WindowManager.LayoutParams lp = w.getAttributes();
                lp.x = 0;
                lp.y = 40;
                dialog.onWindowAttributesChanged(lp);
                dialog.show();
            }
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
