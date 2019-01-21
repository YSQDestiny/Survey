package com.cykj.survey.ui.fragment.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.ui.activity.power.BuildingActivity;
import com.cykj.survey.ui.activity.power.ElectromechanicalActivity;
import com.cykj.survey.ui.activity.power.HydroActivity;
import com.cykj.survey.ui.activity.power.HydroDisasterActivity;
import com.cykj.survey.ui.activity.power.HydroGeologyActivity;
import com.cykj.survey.ui.activity.power.OtherActivity;
import com.cykj.survey.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                            Intent intent = new Intent(getActivity(),HydroActivity.class);
                            getActivity().startActivity(intent);
                            break;
                        case "自然灾害风险":
                            Intent intent1 = new Intent(getActivity(),HydroGeologyActivity.class);
                            getActivity().startActivity(intent1);
                            break;
                        case "火灾风险":
                            Intent intent2 = new Intent(getActivity(),HydroDisasterActivity.class);
                            getActivity().startActivity(intent2);
                            break;
                        case "机电设备风险":
                            Intent intent3 = new Intent(getActivity(),ElectromechanicalActivity.class);
                            getActivity().startActivity(intent3);
                            break;
                        case "水工建筑风险":
                            Intent intent4 = new Intent(getActivity(),BuildingActivity.class);
                            getActivity().startActivity(intent4);
                            break;
                        case "其他风险":
                            Intent intent5 = new Intent(getActivity(),OtherActivity.class);
                            getActivity().startActivity(intent5);
                            break;
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext())
                .addItemView(item1,onClickListener)
                .addItemView(item2,onClickListener)
                .addItemView(item3,onClickListener)
                .addItemView(item4,onClickListener)
                .addItemView(item5,onClickListener)
                .addItemView(item6,onClickListener)
                .addTo(groupListView);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
