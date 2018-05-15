package com.cykj.survey.fragment.index;

import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;

@Widget(group = Group.Home, name = "工程险",iconRes = R.mipmap.icon_fragment_engineering)
public class ProjectFragment extends BaseFragment{

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.layout_working,null);
        return root;
    }

}
