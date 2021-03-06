package com.cykj.survey.fragment.area;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.options.wooden_furniture.DangerFragment;
import com.cykj.survey.fragment.options.wooden_furniture.MaterialFragmet;
import com.cykj.survey.fragment.options.wooden_furniture.SpareFragment;
import com.cykj.survey.fragment.options.wooden_furniture.WasteFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockFragment extends BaseFragment{

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

        QMUICommonListItemView material = mGroupListView.createItemView("成品、原材料库");
        material.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView danger = mGroupListView.createItemView("危化品库");
        danger.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView waste = mGroupListView.createItemView("危废库");
        waste.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView spare = mGroupListView.createItemView("备品备件库");
        spare.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence str = ((QMUICommonListItemView) v).getText();
                    switch (str.toString()){
                        case "成品、原材料库":
                            QMUIFragment materialFragmet = new MaterialFragmet();
                            startFragment(materialFragmet);
                            break;
                        case "危化品库":
                            QMUIFragment dangerFragment = new DangerFragment();
                            startFragment(dangerFragment);
                            break;
                        case "危废库":
                            QMUIFragment wasteFragment = new WasteFragment();
                            startFragment(wasteFragment);
                            break;
                        case "备品备件库":
                            QMUIFragment spareFragment = new SpareFragment();
                            startFragment(spareFragment);
                            break;
                        default:
                            Toast.makeText(getActivity(),"该页面还在施工中",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        section.setTitle("库存区")
                .addItemView(material,onClickListener)
                .addItemView(danger,onClickListener)
                .addItemView(waste,onClickListener)
                .addItemView(spare,onClickListener)
                .addTo(mGroupListView);
    }

    private void initTopbar() {
        mTopbar.setTitle("库存区");

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
