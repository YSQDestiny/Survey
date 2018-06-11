package com.cykj.survey.base;

import android.view.View;
import android.widget.Toast;

import com.cykj.survey.manager.QDUpgradeManager;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public abstract class BaseFragment extends QMUIFragment{

    private QMUITipDialog tipDialog;

    public BaseFragment(){

    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(),100);
    }

    @Override
    public void onResume() {
        super.onResume();
        QDUpgradeManager.getInstance(getContext()).runUpgradeTipTaskIfExist(getActivity());
    }

    public void showToastShort(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void showTipDialog(String msg,int icon_type){
        tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(icon_type)
                .setTipWord(msg)
                .create();
        tipDialog.show();
    }

    public void tipDialogDismiss(){
        tipDialog.dismiss();
    }
}
