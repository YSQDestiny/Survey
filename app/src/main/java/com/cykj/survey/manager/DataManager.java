package com.cykj.survey.manager;

import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.utils.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager _sInstance;


    public DataManager() {

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
        mOtherNames = new ArrayList<>();
        mOtherNames.add(TestFragment.class);
    }

    private void initUtilDesc() {
    }

    private void initHomeDesc() {
    }
}
