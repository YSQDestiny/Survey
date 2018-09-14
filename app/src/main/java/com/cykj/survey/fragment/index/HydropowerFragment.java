package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.activity.hydropower.HydroActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Widget(group = Group.Home, name = "水电站", iconRes = R.mipmap.icon_fragment_hydropower_station)
public class HydropowerFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_recycler)
    RecyclerView hydroRecycler;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hydro_list, null);
        ButterKnife.bind(this, root);
        initTopbar();
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
}
