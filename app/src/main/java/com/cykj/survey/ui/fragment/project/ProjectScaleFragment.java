package com.cykj.survey.ui.fragment.project;

import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;

public class ProjectScaleFragment extends BaseFragment {
    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project_scale,null);
        return root;
    }

}