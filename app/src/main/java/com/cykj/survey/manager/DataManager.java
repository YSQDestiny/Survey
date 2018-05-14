package com.cykj.survey.manager;

import com.cykj.survey.base.BaseFragment;

import java.util.List;

public class DataManager {

    private static DataManager _sInstance;
    private WidgetContainer mWidgetContainer;

    public DataManager() {
        mWidgetContainer = WidgetContainer.getInstance();
        initHomeDesc();
        initUtilDesc();
        initOtherDesc();
    }

    private List<Class<? extends BaseFragment>> mHomeNames;
    private List<Class<? extends BaseFragment>> mUtilNames;
    private List<Class<? extends BaseFragment>> mOtherNames;

    public static DataManager getInstance() {
        if (_sInstance == null) {
            _sInstance = new DataManager();
        }
        return _sInstance;
    }

    private void initOtherDesc() {
    }

    private void initUtilDesc() {
    }

    private void initHomeDesc() {
    }
}
