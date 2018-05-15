package com.cykj.survey.fragment.index;

import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;

@Widget(group = Group.Home, name = "企财险",iconRes = R.mipmap.icon_fragment_business)
public class BusinessFragment extends BaseFragment{

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business,null);
        return root;
    }

}
