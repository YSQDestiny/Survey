package com.cykj.survey.ui.fragment.project;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.R;
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.model.ProjectConstants;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectScaleFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.projiect_scale_edit_length)
    EditText projiectScaleEditLength;
    @BindView(R.id.projiect_scale_edit_culvert)
    EditText projiectScaleEditCulvert;
    @BindView(R.id.projiect_scale_edit_bridge_extra)
    EditText projiectScaleEditBridgeExtra;
    @BindView(R.id.projiect_scale_edit_bridge_big)
    EditText projiectScaleEditBridgeBig;
    @BindView(R.id.projiect_scale_edit_medium)
    EditText projiectScaleEditMedium;
    @BindView(R.id.projiect_scale_edit_tunnel_extra)
    EditText projiectScaleEditTunnelExtra;
    @BindView(R.id.projiect_scale_edit_tunnel_medium)
    EditText projiectScaleEditTunnelMedium;
    @BindView(R.id.projiect_scale_edit_tunnel_short)
    EditText projiectScaleEditTunnelShort;
    @BindView(R.id.project_scale_road)
    LinearLayout projectScaleRoad;

    private AppComponent component;
    private ResultInteractor resultInteractor;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project_scale, null);
        ButterKnife.bind(this, root);
        component = SurveyApplication.get(getActivity()).component();
        resultInteractor = component.getResultInteractor();
        initTopbar();
        initView();
        return root;
    }

    private void initTopbar() {
        topbar.setTitle("建设规模");
        topbar.addRightTextButton("完成",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postProjectInfo();
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    private void initView() {

    }

    private boolean test = true;
    private void editTest(EditText editText){
        if (editText.getText().toString().equals("")){
            editText.setError(editText.getHint());
            test = false;
        }
    }

    private void postProjectInfo(){

        editTest(projiectScaleEditLength);
        editTest(projiectScaleEditCulvert);
        editTest(projiectScaleEditBridgeExtra);
        editTest(projiectScaleEditBridgeBig);
        editTest(projiectScaleEditMedium);
        editTest(projiectScaleEditTunnelExtra);
        editTest(projiectScaleEditTunnelMedium);
        editTest(projiectScaleEditTunnelShort);

        if (!test){
            test = true;
            return;
        }else {
            Map<String,String> map = new HashMap<>();
            map.put("路线长度(KM)",projiectScaleEditLength.getText().toString());
            map.put("涵洞(m/座)",projiectScaleEditCulvert.getText().toString());
            map.put("特大桥(m/座)",projiectScaleEditBridgeExtra.getText().toString());
            map.put("大桥(m/座)",projiectScaleEditBridgeBig.getText().toString());
            map.put("中桥(m/座)",projiectScaleEditMedium.getText().toString());
            map.put("长隧道(m/座)",projiectScaleEditTunnelExtra.getText().toString());
            map.put("中隧道(m/座)",projiectScaleEditTunnelMedium.getText().toString());
            map.put("短隧道(m/座)",projiectScaleEditTunnelShort.getText().toString());

            String json = JSONObject.toJSONString(map);
            String target = "scale";

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
                    showToastShort("保存失败，请重试");
                }
            });

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}