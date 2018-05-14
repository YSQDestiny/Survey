package com.cykj.survey.fragment.home;

import android.content.Context;

import com.cykj.survey.manager.DataManager;

public class HomeIndexController extends HomeController {

    public HomeIndexController(Context context){
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Home";
    }

    @Override
    protected ItemAdapter getItemAdapter() {
        return new ItemAdapter(getContext(), DataManager.getInstance().getHomeDescriptions());
    }


}
