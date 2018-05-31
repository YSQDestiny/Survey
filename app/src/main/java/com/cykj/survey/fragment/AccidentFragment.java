package com.cykj.survey.fragment;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccidentFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.accident_site_img)
    ImageView accidentSiteImg;
    @BindView(R.id.accident_surroundings_img)
    ImageView accidentSurroundingsImg;
    @BindView(R.id.accident_type_grid)
    GridView accidentTypeGrid;
    @BindView(R.id.accident_result_grid)
    GridView accidentResultGrid;

    private AccidentGridAdapter typeGridAdapter;
    private AccidentGridAdapter resultAdapter;
    private List<AccidentGridModel> typeData;
    private List<AccidentGridModel> resultData;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_accident_description, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initData();
        initResultData();

        typeGridAdapter = new AccidentGridAdapter(getActivity(),typeData);
        accidentTypeGrid.setAdapter(typeGridAdapter);
        accidentTypeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (typeData.get(position).isSelect()){
                    typeData.get(position).setSelect(false);
                }else {
                    typeData.get(position).setSelect(true);
                }
                typeGridAdapter.notifyDataSetChanged();
            }
        });

        resultAdapter = new AccidentGridAdapter(getActivity(),resultData);
        accidentResultGrid.setAdapter(resultAdapter);
        accidentResultGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (resultData.get(position).isSelect()){
                    resultData.get(position).setSelect(false);
                    resultData = null;
                    initResultData();
                    resultAdapter.notifyDataSetChanged();
                }else {
                    resultData.get(position).setSelect(true);
                    showSingleChoiceDialog(resultData.get(position).getName(),position);
                    resultAdapter.notifyDataSetChanged();
                }

            }
        });
        return root;
    }

    private void initTopbar() {

        topbar.setTitle("事故说明");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

    }

    private void initData(){
        typeData = new ArrayList<>();
        typeData.add(new AccidentGridModel("触电事故",false));
        typeData.add(new AccidentGridModel("火灾、爆炸",false));
        typeData.add(new AccidentGridModel("雷击事故",false));
        typeData.add(new AccidentGridModel("暴乱事件",false));
        typeData.add(new AccidentGridModel("踩踏事故",false));
        typeData.add(new AccidentGridModel("交通事故",false));
        typeData.add(new AccidentGridModel("淹溺、灼烫",false));
        typeData.add(new AccidentGridModel("物体打击",false));
        typeData.add(new AccidentGridModel("机械伤害",false));
        typeData.add(new AccidentGridModel("化学伤害",false));
        typeData.add(new AccidentGridModel("其他",false));

    }

    private void initResultData(){
        resultData = new ArrayList<>();
        resultData.add(new AccidentGridModel("财产损失",false));
        resultData.add(new AccidentGridModel("受伤",false));
        resultData.add(new AccidentGridModel("死亡",false));
        resultData.add(new AccidentGridModel("群死群伤",false));
    }

    String[] items;
    private void showSingleChoiceDialog(String str, final int positon){
        QMUIDialog.CheckableDialogBuilder dialogBuilder = new QMUIDialog.CheckableDialogBuilder(getActivity());
        switch (str){
            case "财产损失":
                items = new String[]{"0-5万","5-10万","10-50万","50-100万","100-500万","500万以上"};
                break;
            case "受伤":
                items = new String[]{"轻伤","重伤","致残"};
                break;
            case "死亡":
                items = new String[]{"1人死亡","1-3人死亡","3人以上死亡"};
                break;
            default:
                return;
        }
        dialogBuilder.addItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resultData.get(positon).setName(items[which]);
                resultAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        })
        .create(mCurrentDialogStyle).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
