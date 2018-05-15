package com.cykj.survey.fragment.utils;

import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;

@Widget(group = Group.Util, name = "流水账",iconRes = R.mipmap.icon_fragment_bill)
public class BillFragment extends BaseFragment{

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.layout_working,null);
        return root;
    }

}
