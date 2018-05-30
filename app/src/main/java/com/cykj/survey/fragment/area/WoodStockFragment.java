package com.cykj.survey.fragment.area;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodDangerFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodMaterialFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodSpareFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodWasteFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 木材加工企业-库存区-成品、木料堆场
 */
public class WoodStockFragment extends BaseFragment {

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

        QMUICommonListItemView wood_material = mGroupListView.createItemView("成品、木料堆场");
        wood_material.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_danger = mGroupListView.createItemView("危化品库、储罐");
        wood_danger.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_waste = mGroupListView.createItemView("危废库");
        wood_waste.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView wood_spare = mGroupListView.createItemView("备品备件库");
        wood_spare.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence str = ((QMUICommonListItemView) v).getText();
                    switch (str.toString()){
                        case "成品、木料堆场":
                            QMUIFragment wood_material = new WoodMaterialFragment();
                            startFragment(wood_material);
                            break;
                        case "危化品库、储罐":
                            QMUIFragment wood_danger = new WoodDangerFragment();
                            startFragment(wood_danger);
                            break;
                        case "危废库":
                            QMUIFragment wood_waste = new WoodWasteFragment();
                            startFragment(wood_waste);
                            break;
                        case "备品备件库":
                            QMUIFragment wood_spare = new WoodSpareFragment();
                            startFragment(wood_spare);
                            break;
                        default:
                            Toast.makeText(getActivity(),"该页面还在施工中",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        section.setTitle("库存区")
                .addItemView(wood_material,onClickListener)
                .addItemView(wood_danger,onClickListener)
                .addItemView(wood_waste,onClickListener)
                .addItemView(wood_spare,onClickListener)
                .addTo(mGroupListView);
    }

    private void initTopbar() {
        mTopbar.setTitle("区域选择");

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
