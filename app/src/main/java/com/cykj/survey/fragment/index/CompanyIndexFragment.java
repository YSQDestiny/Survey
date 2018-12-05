package com.cykj.survey.fragment.index;

import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.activity.BasicsBusinessActivity;
import com.cykj.survey.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CompanyIndexFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment_company_index, null);
        ButterKnife.bind(this, root);
        initGroupList();
        return root;
    }

    private void initTopbar(){
        topbar.setTitle("步骤选择");
        topbar.addLeftBackImageButton();
    }

    private void initGroupList(){
        QMUICommonListItemView item1 = groupListView.createItemView("基础信息");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item2 = groupListView.createItemView("地质气象");
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

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
                            Intent intent = new Intent(getActivity(),BasicsBusinessActivity.class);
                            getActivity().startActivity(intent);
                            break;
                        case "地质气象":
                            break;
                        case "照片上传":
                            break;
                        case "现场查勘":
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
                .addTo(groupListView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
