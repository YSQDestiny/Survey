package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HydroIndexFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_hydro_index, null);
        ButterKnife.bind(this, root);
        return null;
    }

    private void initTopbar(){
        topbar.setTitle("步骤选择");
        topbar.addLeftBackImageButton();
    }

    private void initGroupList() {
        QMUICommonListItemView item1 = groupListView.createItemView("基础信息");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item2 = groupListView.createItemView("自然灾害风险");
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item3 = groupListView.createItemView("火灾风险");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item4 = groupListView.createItemView("机电设备风险");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item5 = groupListView.createItemView("水工建筑风险");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item6 = groupListView.createItemView("其他风险");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    switch (text.toString()){
                        case "基础信息":
                            break;
                        case "自然灾害风险":
                            break;
                        case "火灾风险":
                            break;
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext())
                .addItemView(item1,onClickListener)
                .addItemView(item2,onClickListener)
                .addItemView(item3,onClickListener)
                .addTo(groupListView);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
