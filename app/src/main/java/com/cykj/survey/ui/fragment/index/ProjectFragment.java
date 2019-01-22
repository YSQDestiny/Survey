package com.cykj.survey.ui.fragment.index;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.model.ProjectModel;
import com.cykj.survey.model.ResultModel;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProjectFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_area)
    TextView projectArea;
    @BindView(R.id.projiect_edit_addr)
    EditText projiectEditAddr;
    @BindView(R.id.projiect_edit_construction)
    EditText projiectEditConstruction;
    @BindView(R.id.project_type_select)
    TextView projectTypeSelect;
    @BindView(R.id.projiect_edit_build)
    EditText projiectEditBuild;
    @BindView(R.id.project_review_type)
    TextView projectReviewType;
    @BindView(R.id.projiect_edit_commission)
    EditText projiectEditCommission;
    @BindView(R.id.projiect_edit_contact)
    EditText projiectEditContact;
    @BindView(R.id.projiect_edit_contact_tel)
    EditText projiectEditContactTel;
    @BindView(R.id.projiect_edit_twon)
    EditText projiectEditTwon;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private CityPickerView mPicker = new CityPickerView();

    private ProjectModel projectModel;

    private AppComponent component;
    private ResultInteractor resultInteractor;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project, null);
        ButterKnife.bind(this, root);
        mPicker.init(getActivity());
        projectModel = new ProjectModel();

        component = SurveyApplication.get(getActivity()).component();
        resultInteractor = component.getResultInteractor();

        initTopbar();
        initView();

        /**
         * 区域选择列表
         */
        projectArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityConfig cityConfig = new CityConfig.Builder().build();
                cityConfig.setDefaultProvinceName("四川省");
                cityConfig.setDefaultCityName("成都市");
                cityConfig.setDefaultDistrict("市辖区");
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份
                        if (province == null) {
                            showToastShort("省份信息获取失败");
                            return;
                        } else {
                            projectModel.setProvince(province.getName());
                        }


                        //城市
                        if (city == null) {
                            showToastShort("城市信息获取失败");
                            return;
                        } else {
                            projectModel.setCity(city.getName());
                            ProjectConstants.sqlMap.put("city", city.getName());
                        }

                        //地区
                        if (district == null) {
                            showToastShort("地区信息获取失败");
                            return;
                        } else {
                            projectModel.setCounty(district.getName());
                            ProjectConstants.sqlMap.put("county", district.getName());
                        }
                        String str = province.getName() + "-" + city.getName() + "-" + district.getName();
                        projectArea.setText(str);
                        projectArea.setTextColor(getResources().getColor(R.color.bank_bg01));
                    }

                    @Override
                    public void onCancel() {
                        showToastShort("操作取消");
                    }
                });
                mPicker.showCityPicker();
            }
        });

        return root;
    }

    private void initView() {
        projectTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog();
            }
        });
        projectReviewType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewTypeMenu();
            }
        });
    }

    /**
     * 显示工程类型菜单
     */
    private void showMenuDialog() {
        final String[] items = new String[]{"公路工程", "轨道交通", "房屋建筑工程", "水利工程", "航电工程"};
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        projectTypeSelect.setText(items[which]);
                        projectTypeSelect.setTextColor(getResources().getColor(R.color.qmui_config_color_black));
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 显示查勘类型菜单
     */
    private void showReviewTypeMenu(){
        final String[] items = new String[]{"现场查勘","保前风勘"};
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      projectReviewType.setText(items[which]);
                      projectReviewType.setTextColor(getResources().getColor(R.color.qmui_config_color_black));
                      dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }


    private boolean isNull = true;

    private void postProjectInfo() {
        ProjectConstants.sqlMap.put("town", projiectEditTwon.getText().toString());

        //小镇输入框验证
        if (projiectEditTwon.getText().toString().equals("")){
            projiectEditTwon.setError(projiectEditTwon.getHint());
        }else {
            projectModel.setTown(projiectEditTwon.getText().toString());
        }

        //详细地址验证
        if (projiectEditAddr.getText().toString().equals("")){
            projiectEditAddr.setError(projiectEditAddr.getHint());
            isNull = false;
        }else {
            projectModel.setAddr(projiectEditAddr.getText().toString());
        }

        //施工单位验证
        if (projiectEditConstruction.getText().toString().equals("")){
            projiectEditConstruction.setError(projiectEditConstruction.getHint());
            isNull = false;
        }else {
            projectModel.setConstruction(projiectEditConstruction.getText().toString());
        }

        //建设单位验证
        if (projiectEditBuild.getText().toString().equals("")){
            projiectEditBuild.setError(projiectEditBuild.getHint());
            isNull = false;
        }else {
            projectModel.setBuilding(projiectEditBuild.getText().toString());
        }

        //工程类型验证
        if (projectTypeSelect.getText().equals("请选择工程类型")){
            showToastShort("请选择工程类型");
            isNull = false;
        }else {
            projectModel.setType(projectTypeSelect.getText().toString());
        }

        //查勘类型验证
        if (projectReviewType.getText().toString().equals("请选择查勘类别")){
            showToastShort("请选择查勘类别");
            isNull = false;
        }else {
            projectModel.setReviewType(projectReviewType.getText().toString());
        }

        //委托方联系人
        if (projiectEditContact.getText().toString().equals("")){
            projiectEditContact.setError(projiectEditConstruction.getHint());
            isNull = false;
        }else {
            projectModel.setClientContact(projiectEditContact.getText().toString());
        }

        //委托方
        if (projiectEditCommission.getText().toString().equals("")){
            projiectEditCommission.setError(projiectEditCommission.getHint());
            isNull = false;
        }else {
            projectModel.setClient(projiectEditCommission.getText().toString());
        }

        //委托方联系电话
        if (projiectEditContactTel.getText().toString().equals("")){
            projiectEditContactTel.setError(projiectEditContactTel.getHint());
            isNull = false;
        }else {
            projectModel.setClientPhone(projiectEditContactTel.getText().toString());
        }

        if (!isNull){
            isNull = true;
            return;
        }else {
            String json = JSONObject.toJSONString(projectModel);
            resultInteractor.saveProject(ProjectConstants.PROJECT_ID, json, new BaseSubsribe<StringBean>() {
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

    }

    private void initTopbar() {
        topbar.setTitle("项目信息");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postProjectInfo();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
