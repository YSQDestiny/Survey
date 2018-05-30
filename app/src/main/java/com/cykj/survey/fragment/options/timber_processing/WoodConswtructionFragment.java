package com.cykj.survey.fragment.options.timber_processing;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.OptionsAdapter;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 木材加工企业-建、构筑物
 */
public class WoodConswtructionFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.options_recycler)
    RecyclerView mRecycler;

    private List<String> options;
    private OptionsAdapter optionsAdapter;

    @Override
    protected View onCreateView() {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_produce_options, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initData();
        optionsAdapter = new OptionsAdapter(getActivity(),options);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setAdapter(optionsAdapter);
        mRecycler.setItemAnimator( new DefaultItemAnimator());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return root;
    }

    private void initData() {
        options = new ArrayList<>();
        options.add(getString(R.string.wood_construction_options_1));
        options.add(getString(R.string.wood_construction_options_2));
        options.add(getString(R.string.wood_construction_options_3));

    }

    private void initTopbar() {

        mTopbar.setTitle("查勘选项");

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
