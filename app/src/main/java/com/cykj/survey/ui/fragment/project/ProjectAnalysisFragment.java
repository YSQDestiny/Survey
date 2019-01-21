package com.cykj.survey.ui.fragment.project;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.ui.fragment.adapter.ProjectAnalysisAdapter;
import com.cykj.survey.model.Disaster;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.HttpUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 工程地质分析界面
 */
public class ProjectAnalysisFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_analysis_list)
    ListView projectAnalysisList;

    private List<Disaster> disasterList;

    private ProjectAnalysisAdapter analysisAdapter;

    private Handler handler;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_project_geology_analysis, null);
        handler = new Handler();
        initTopbar();
        getDisaster();
        ButterKnife.bind(this, root);
        return root;
    }

    private void getDisaster() {

        String url = Constants.TEST_SERVICE + "/project/searchYHD";

        if (ProjectConstants.sqlMap.size() < 3){
            showToastShort("地理信息录入不完整，请检查");
            popBackStack();
        }else {
            String json  = JSONObject.toJSONString(ProjectConstants.sqlMap);
            Map<String,String> params = new HashMap<>();
            params.put("json",json);
            HttpUtil.doPost(url, params, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resultStr = response.body().string();
                    ResultModel result = JSONObject.parseObject(resultStr, ResultModel.class);
                    if (result.getCode() == 0){
                        disasterList = JSONObject.parseArray(result.getData(),Disaster.class);
                        handler.post(UIable);
                    }
                }
            });
        }

    }

    private void initView() {
        if (disasterList != null){
            analysisAdapter = new ProjectAnalysisAdapter(getActivity(),disasterList);
            projectAnalysisList.setAdapter(analysisAdapter);
        }
    }

    private void initTopbar() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };
}
