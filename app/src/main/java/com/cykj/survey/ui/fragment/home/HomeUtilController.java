package com.cykj.survey.ui.fragment.home;

import android.content.Context;

import com.cykj.survey.manager.DataManager;

public class HomeUtilController extends HomeController {

    public HomeUtilController(Context context){
        super(context);
    }

    @Override
    protected String getTitle() {
        return "工具";
    }

    @Override
    protected ItemAdapter getItemAdapter() {
        return new ItemAdapter(getContext(), DataManager.getInstance().getUtilDescriptions());
    }
}
