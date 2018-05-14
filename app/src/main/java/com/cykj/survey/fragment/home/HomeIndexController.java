package com.cykj.survey.fragment.home;

import android.content.Context;

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
        return null;
    }


}
