package com.cykj.survey.fragment.project;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.ProjectAnalysisAdapter;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工程地质分析界面
 */
public class ProjectAnalysisFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_analysis_list)
    ListView projectAnalysisList;

    private ProjectAnalysisAdapter analysisAdapter;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_project_geology_analysis, null);
        initTopbar();
        getDisaster();
        initView();
        ButterKnife.bind(this, root);
        return root;
    }

    private void getDisaster() {

        String url = Constants.TEST_SERVICE + "/project/searchDisaster";



    }

    private void initView() {

    }

    private void initTopbar() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
