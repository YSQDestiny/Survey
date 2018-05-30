package com.cykj.survey.fragment.industry;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.area.WoodProduceFragment;
import com.cykj.survey.fragment.area.WoodStockFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodAidFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodConswtructionFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodOfficeFragment;
import com.cykj.survey.fragment.options.timber_processing.WoodSurroundingsFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimberProcessingFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_wooden_furniture, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initGroupList();
        return root;
    }

    private void initGroupList() {

        QMUIGroupListView.Section section = new QMUIGroupListView.Section(getActivity());

        QMUICommonListItemView produce = mGroupListView.createItemView("生产区");
        produce.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        final QMUICommonListItemView stock = mGroupListView.createItemView("库存区");
        stock.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView office = mGroupListView.createItemView("办公区");
        office.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView surroundings = mGroupListView.createItemView("周边环境");
        surroundings.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView construction = mGroupListView.createItemView("建.构筑物");
        construction.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView aid = mGroupListView.createItemView("生产辅助");
        aid.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    switch (text.toString()){
                        case "生产区":
                            QMUIFragment wood_produce = new WoodProduceFragment();
                            startFragment(wood_produce);
                            break;
                        case "库存区":
                            QMUIFragment wood_stock = new WoodStockFragment();
                            startFragment(wood_stock);
                            break;
                        case "办公区":
                            QMUIFragment wood_office = new WoodOfficeFragment();
                            startFragment(wood_office);
                            break;
                        case "周边环境":
                            QMUIFragment wood_surroundings = new WoodSurroundingsFragment();
                            startFragment(wood_surroundings);
                            break;
                        case "建.构筑物":
                            QMUIFragment wood_construction = new WoodConswtructionFragment();
                            startFragment(wood_construction);
                            break;
                        case "生产辅助":
                            QMUIFragment wood_aid = new WoodAidFragment();
                            startFragment(wood_aid);
                            break;
                        default:
                            Toast.makeText(getActivity(),"该页面还在施工中",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        section.setTitle("区域")
                .addItemView(produce,onClickListener)
                .addItemView(stock,onClickListener)
                .addItemView(office,onClickListener)
                .addItemView(surroundings,onClickListener)
                .addItemView(construction,onClickListener)
                .addItemView(aid,onClickListener)
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
}
