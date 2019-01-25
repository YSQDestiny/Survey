package com.cykj.survey.ui.fragment.project;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.R;
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.DisasterBean;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.model.Disaster;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.ui.fragment.adapter.ProjectAnalysisAdapter;
import com.cykj.survey.ui.fragment.adapter.ProjectAnalysisAdapter2;
import com.cykj.survey.util.ListViewUtil;
import com.cykj.survey.view.NoScrollListview;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工程地质分析界面
 */
public class ProjectAnalysisFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_analysis_1)
    Spinner projectAnalysis1;
    @BindView(R.id.project_analysis_2)
    Spinner projectAnalysis2;
    @BindView(R.id.project_analysis_3)
    Spinner projectAnalysis3;
    @BindView(R.id.project_analysis_4)
    Spinner projectAnalysis4;
    @BindView(R.id.project_analysis_5)
    Spinner projectAnalysis5;
    @BindView(R.id.project_analysis_list)
    NoScrollListview projectAnalysisList;
    @BindView(R.id.project_analysis_list_2)
    NoScrollListview projectAnalysisList2;


    private List<Disaster> disasterList;

    private ProjectAnalysisAdapter analysisAdapter;
    private ProjectAnalysisAdapter2 analysisAdapter2;

    private AppComponent component;
    private ResultInteractor resultInteractor;

    private Handler handler;

    private List<String> data_1;
    private List<String> data_2;
    private List<String> data_3;
    private List<String> data_4;
    private List<String> data_5;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_project_geology_analysis, null);
        ButterKnife.bind(this, root);
        handler = new Handler();
        component = SurveyApplication.get(getActivity()).component();
        resultInteractor = component.getResultInteractor();
        initTopbar();
        initData();
        initView();
        getDisaster();
        return root;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        data_1 = new ArrayList<>();
        data_2 = new ArrayList<>();
        data_3 = new ArrayList<>();
        data_4 = new ArrayList<>();
        data_5 = new ArrayList<>();

        data_1.add("强烈");
        data_1.add("中等");
        data_1.add("不发育");

        data_2.add("复杂");
        data_2.add("简单、单一");
        data_2.add("较简单、单一");

        data_3.add("复杂、变化大、不良");
        data_3.add("较复杂、不稳定、较差");
        data_3.add("简单、单一、良好");

        data_4.add("不良");
        data_4.add("较差");
        data_4.add("良好");

        data_5.add("活动强烈");
        data_5.add("活动较强烈");
        data_5.add("活动一般");

    }

    /**
     * 获取隐患点数据
     *
     *
     */
    private void getDisaster() {

        if (ProjectConstants.sqlMap.size() < 3) {
            showToastShort("地理信息录入不完整，请检查");
            popBackStack();
        } else {
            String json = JSONObject.toJSONString(ProjectConstants.sqlMap);

            resultInteractor.searchYHD(json, new BaseSubsribe<DisasterBean>() {
                @Override
                public void onSuccess(DisasterBean result) {
                    if (result.getMessage().equals("success")) {
                        disasterList = result.getData();
                        initList();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    showToastLong("隐患点数据获取失败！");
                }
            });

        }

    }

    /**
     * 初始化view
     */
    private void initView() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_5);

        spinnerSetAdapter(adapter1, projectAnalysis1);
        spinnerSetAdapter(adapter2, projectAnalysis2);
        spinnerSetAdapter(adapter3, projectAnalysis3);
        spinnerSetAdapter(adapter4, projectAnalysis4);
        spinnerSetAdapter(adapter5, projectAnalysis5);

        analysisAdapter2 = new ProjectAnalysisAdapter2(getActivity(), ProjectConstants.PROJECT_TYPE);
        projectAnalysisList2.setAdapter(analysisAdapter2);
        ListViewUtil.setListViewHeightBasedOnChildren(projectAnalysisList2);
    }

    /**
     * 初始化list
     */
    private void initList() {
        if (disasterList != null) {
            analysisAdapter = new ProjectAnalysisAdapter(getActivity(), disasterList);
            projectAnalysisList.setAdapter(analysisAdapter);
            ListViewUtil.setListViewHeightBasedOnChildren(projectAnalysisList);
        }
    }

    private void initTopbar() {
        topbar.setTitle("地质灾害分析");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void postData(){
        List<String> complexList = new ArrayList<>();
        List<String> importantList = new ArrayList<>();
        Map<String,String> YHDMap = null;
        if (analysisAdapter != null){
             YHDMap = analysisAdapter.map;
        }
        List<String> choseDate = analysisAdapter2.checkData;

        String complex;
        String important;
        String lv = "";

        if (choseDate.size() == 0){
            showToastShort("请勾选项目类型");
            return;
        }

        List<String> stringList = new ArrayList<>();
        stringList.add(projectAnalysis1.getSelectedItem().toString());
        stringList.add(projectAnalysis2.getSelectedItem().toString());
        stringList.add(projectAnalysis3.getSelectedItem().toString());
        stringList.add(projectAnalysis4.getSelectedItem().toString());
        stringList.add(projectAnalysis5.getSelectedItem().toString());

        for(String str : stringList){
            complexList.add(ProjectConstants.COMPLEX_MAP.get(str));
        }

        for (String str : choseDate){
            importantList.add(ProjectConstants.IMPORTANT_MAP.get(str));
        }

        if (stringList.contains("复杂")){
            complex = "复杂";
        }else if (stringList.contains("中等")){
            complex = "中等";
        }else {
            complex = "简单";
        }

        if (importantList.contains("重要")){
            important = "重要";
        }else if (importantList.contains("较重要")){
            important = "较重要";
        }else {
            important = "一般";
        }

        switch (complex){
            case "复杂":
                switch (important){
                    case "重要":
                        lv = "一级";
                        break;
                    case "较重要":
                        lv = "一级";
                        break;
                    case "一般":
                        lv = "二级";
                        break;
                }
                break;
            case "中等":
                switch (important){
                    case "重要":
                        lv = "一级";
                        break;
                    case "较重要":
                        lv = "二级";
                        break;
                    case "一般":
                        lv = "三级";
                        break;
                }
                break;
            case "简单":
                switch (important){
                    case "重要":
                        lv = "一级";
                        break;
                    case "较重要":
                        lv = "三级";
                        break;
                    case "一般":
                        lv = "三级";
                        break;
                }
                break;
        }

        Map<String,Object> map = new HashMap<>();
        if (YHDMap != null){
            map.put("YHD",YHDMap);
        }else {
            map.put("YHD","");
        }
        map.put("lv",lv);
        String json = JSONObject.toJSONString(map);
        String target = "analysis";

        resultInteractor.updateProject(ProjectConstants.PROJECT_ID, target, json, new BaseSubsribe<StringBean>() {
            @Override
            public void onSuccess(StringBean result) {
                if (result.getMessage().equals("success")){
                    showToastShort("保存成功");
                    popBackStack();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                showToastShort("保存失败");
            }
        });

    }

    /**
     * spinner设置adapter
     *
     * @param arrayAdapter
     * @param spinner
     */
    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
