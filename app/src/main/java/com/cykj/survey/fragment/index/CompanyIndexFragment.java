package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.activity.IndustryActivity;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.model.CompanyConstants;
import com.cykj.survey.model.ProgressModel;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyIndexFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment_company_index, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initGroupList();
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

    private void initGroupList(){
        QMUICommonListItemView item1 = groupListView.createItemView("基础信息");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item3 = groupListView.createItemView("照片上传");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item4 = groupListView.createItemView("现场查勘");
        item4.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    switch (text.toString()){
                        case "基础信息":
                            if (CompanyConstants.CP_MODEL != null){
                                showToastLong("基本信息已经录入，暂无法编辑，将会尽快开放");
                            }else {
                                BaseFragment fragment = new BasicsBusinessFragment();
                                startFragment(fragment);
                            }
                            break;
                        case "照片上传":
                            if (ProgressModel.COMPANY_1){
                                Intent intent = new Intent(getActivity(),PhotoUploadActivity.class);
                                getActivity().startActivity(intent);
                            }else {
                                showToastShort("请先填写基本信息");
                            }
                            break;
                        case "现场查勘":
                            if (ProgressModel.COMPANY_1){
                                Intent intent = new Intent(getActivity(),IndustryActivity.class);
                                getActivity().startActivity(intent);
                            }else {
                                showToastShort("请先填写基本信息");
                            }
                            break;
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext())
                .addItemView(item1,onClickListener)
                .addItemView(item3,onClickListener)
                .addItemView(item4,onClickListener)
                .addTo(groupListView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
