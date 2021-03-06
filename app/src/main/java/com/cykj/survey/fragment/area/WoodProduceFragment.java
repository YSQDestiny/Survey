package com.cykj.survey.fragment.area;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodDryFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodDustremovalFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodGlueFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodLaminationFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodTailoringFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 木质加工企业-生产区
 */
public class WoodProduceFragment extends BaseFragment{

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    @Override
    protected View onCreateView() {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_produce_layout, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initGroupList();
        return root;
    }

    private void initGroupList() {
        QMUIGroupListView.Section section = new QMUIGroupListView.Section(getActivity());

        QMUICommonListItemView wood_dry = mGroupListView.createItemView("裁、切、干燥");
        wood_dry.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_glue = mGroupListView.createItemView("制胶、涂胶、组坯");
        wood_glue.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_lamination = mGroupListView.createItemView("压合（冷、热）");
        wood_lamination.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_tailoring = mGroupListView.createItemView("修补、砂光、剪裁");
        wood_tailoring.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_dustRemoval = mGroupListView.createItemView("除尘系统");
        wood_dustRemoval.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence str = ((QMUICommonListItemView) v).getText();
                    switch (str.toString()){
                        case "裁、切、干燥":
                            QMUIFragment wood_dry = new WoodDryFragment();
                            startFragment(wood_dry);
                            break;
                        case "制胶、涂胶、组坯":
                            QMUIFragment wood_glue = new WoodGlueFragment();
                            startFragment(wood_glue);
                            break;
                        case "压合（冷、热）":
                            QMUIFragment wood_lamination = new WoodLaminationFragment();
                            startFragment(wood_lamination);
                            break;
                        case "修补、砂光、剪裁":
                            QMUIFragment wood_tailoring = new WoodTailoringFragment();
                            startFragment(wood_tailoring);
                            break;
                        case "除尘系统":
                            QMUIFragment wood_dustRemoval = new WoodDustremovalFragment();
                            startFragment(wood_dustRemoval);
                            break;
                        default:
                            Toast.makeText(getActivity(),"该页面还在施工中",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        section.setTitle("生产区")
                .addItemView(wood_dry,onClickListener)
                .addItemView(wood_glue,onClickListener)
                .addItemView(wood_lamination,onClickListener)
                .addItemView(wood_tailoring,onClickListener)
                .addItemView(wood_dustRemoval,onClickListener)
                .addTo(mGroupListView);
    }

    private void initTopbar() {
        mTopbar.setTitle("生产区");

        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
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

}
