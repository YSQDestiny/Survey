package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.activity.property.CreatePropertyActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(group = Group.Home, name = "物业", iconRes = R.mipmap.icon_fragment_engineering)
public class PropertyListFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.property_recycler)
    RecyclerView propertyRecycler;
    private Handler handler;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_property_list, null);
        handler = new Handler();
        ButterKnife.bind(this,root);
        initTopbar();
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

}
