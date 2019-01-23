package com.cykj.survey.ui.fragment.index;

import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.SurveyApplication;
import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.config.AppComponent;
import com.cykj.survey.bean.IDBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.ui.activity.project.ProjectAccidentActivity;
import com.cykj.survey.ui.activity.project.ProjectGeologyActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.ui.fragment.project.ProjectAnalysisFragment;
import com.cykj.survey.ui.fragment.project.ProjectScaleFragment;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.util.DeviceUtils;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectIndexFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    private AppComponent component;
    private ResultInteractor resultInteractor;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment_company_index, null);
        ButterKnife.bind(this, root);
        ProjectConstants.sqlMap.clear();
        ProjectConstants.PROJECT_ID = null;

        component = SurveyApplication.get(getContext()).component();
        resultInteractor = component.getResultInteractor();

        initTopbar();
        initView();
        return root;
    }

    private void initTopbar(){
        topbar.setTitle("步骤选择");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    private void initView(){
        showEditTextDialog();
        QMUICommonListItemView item1 = groupListView.createItemView("基础信息");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item2 = groupListView.createItemView("建设规模");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item3 = groupListView.createItemView("地形地貌");
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item4 = groupListView.createItemView("地质分析");
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item6 = groupListView.createItemView("现场风险");
        item4.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    switch (text.toString()){
                        case "基础信息":
                            BaseFragment baseFragment = new ProjectFragment();
                            startFragment(baseFragment);
                            break;
                        case "建设规模":
                            BaseFragment baseFragment1 = new ProjectScaleFragment();
                            startFragment(baseFragment1);
                            break;
                        case "地形地貌":
                            Intent intent = new Intent(getActivity(),ProjectGeologyActivity.class);
                            getActivity().startActivity(intent);
                            break;
                        case "地质分析":
//                            Intent intent = new Intent(getActivity(),ProjectGeologyActivity.class);
//                            getActivity().startActivity(intent);
                            BaseFragment baseFragment2 = new ProjectAnalysisFragment();
                            startFragment(baseFragment2);
                            break;
                        case "现场风险":
                            Intent intent2 = new Intent(getActivity(),ProjectAccidentActivity.class);
                            getActivity().startActivity(intent2);
                            break;
                        default:

                            break;
                    }
                }
            }
        };

        if (ProjectConstants.REVIEW_TYPE.equals("现场查勘")){
            QMUIGroupListView.newSection(getContext())
                    .addItemView(item1,onClickListener)
                    .addItemView(item2,onClickListener)
                    .addItemView(item3,onClickListener)
                    .addItemView(item4,onClickListener)
                    .addItemView(item6,onClickListener)
                    .addTo(groupListView);
        }else {
            QMUIGroupListView.newSection(getContext())
                    .addItemView(item1,onClickListener)
                    .addItemView(item2,onClickListener)
                    .addItemView(item3,onClickListener)
                    .addItemView(item4,onClickListener)
                    .addTo(groupListView);
        }

    }

    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("项目名称")
                .setPlaceholder("在此输入项目名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            createProject(text.toString());
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请填入项目名称", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void createProject(String name){
        String uniqueId = DeviceUtils.getUniqueId(getActivity());

        resultInteractor.createProject(name,uniqueId, new BaseSubsribe<IDBean>() {
            @Override
            public void onSuccess(IDBean result) {
                ProjectConstants.PROJECT_ID = result.getData();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                showToastShort("项目创建失败");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
