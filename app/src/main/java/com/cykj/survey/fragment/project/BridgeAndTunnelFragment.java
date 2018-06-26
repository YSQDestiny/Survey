package com.cykj.survey.fragment.project;

import android.app.Dialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.BridgeListAdapter;
import com.cykj.survey.model.Bridge;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

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

    private QMUIDialog dialog;
    private List<Bridge> bridgeData = new ArrayList<>();
    private BridgeListAdapter bridgeAdapter;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project_bridge_and_tunnel, null);
        ButterKnife.bind(this, root);
        projectBridgeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBridgeDialog();
            }
        });
        return root;
    }

    private void showBridgeDialog(){
        dialog = null;
        dialog = new QMUIDialog(getActivity(),R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.layout_bridge_add_dialog);
        dialog.setCanceledOnTouchOutside(false);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dialog.onWindowAttributesChanged(lp);
        final MaterialEditText beidgeName = dialog.findViewById(R.id.bridgeName);
        final MaterialEditText stationNumber = dialog.findViewById(R.id.stationNumber);
        final MaterialEditText length = dialog.findViewById(R.id.length);
        final MaterialEditText porespanType = dialog.findViewById(R.id.porespanType);

        QMUIRoundButton confirmButton = dialog.findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bridge bridge = new Bridge();
                if (beidgeName.getText().toString().equals("")){
                    beidgeName.setError("请输入桥梁名称");
                    return;
                }else {
                    bridge.setBridgeName(beidgeName.getText().toString());
                }
                if (stationNumber.getText().toString().equals("")){
                    stationNumber.setError("请输入桩号");
                    return;
                }else {
                    bridge.setStationNumber(stationNumber.getText().toString());
                }
                if(length.getText().toString().equals("")){
                    length.setError("请输入桥长");
                    return;
                }else {
                    double len = Double.parseDouble(length.getText().toString());
                    bridge.setLength(len);
                    if (len > 1000){
                        bridge.setType("特大桥");
                    }else if (len >= 100 & len <= 1000){
                        bridge.setType("大桥");
                    }else if (len > 30 & len < 100){
                        bridge.setType("中桥");
                    }else if (len >= 8 & len <= 30){
                        bridge.setType("小桥");
                    }
                }
                if (porespanType.getText().toString().equals("")){
                    porespanType.setError("请输入孔跨类型");
                    return;
                }else {
                    bridge.setPorespanType(porespanType.getText().toString());
                }
                bridgeData.add(bridge);
                dialog.dismiss();
                initData();
            }
        });
        QMUIRoundButton cancleButton = dialog.findViewById(R.id.cancel);
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initData() {

        if (bridgeData.size() == 0){
            return;
        }else if (bridgeData.size() == 1){
            bridgeTip.setVisibility(View.GONE);
            projectBridgeList.setVisibility(View.VISIBLE);
            bridgeAdapter = new BridgeListAdapter(getActivity(),bridgeData);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            projectBridgeList.setLayoutManager(layoutManager);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            projectBridgeList.setAdapter(bridgeAdapter);
            projectBridgeList.setItemAnimator(new DefaultItemAnimator());
            projectBridgeList.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            projectBridgeList.setNestedScrollingEnabled(false);
        }else if (bridgeData.size() > 1){
            bridgeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
