package com.cykj.survey.manager;

import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.index.BusinessFragment;
import com.cykj.survey.fragment.index.HydropowerFragment;
import com.cykj.survey.fragment.index.ProjectFragment;
import com.cykj.survey.fragment.index.ReportListFragment;
import com.cykj.survey.fragment.utils.BillFragment;
import com.cykj.survey.model.ItemDescription;

import java.util.ArrayList;
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
        mUtilNames = new ArrayList<>();
        mUtilNames.add(BillFragment.class);
    }

    private void initHomeDesc() {
        mHomeNames = new ArrayList<>();
        mHomeNames.add(ReportListFragment.class);
        mHomeNames.add(HydropowerFragment.class);
        mHomeNames.add(ProjectFragment.class);
    }

    public ItemDescription getDescription(Class<? extends BaseFragment> cls) {
        return mWidgetContainer.get(cls);
    }

    public String getName(Class<? extends BaseFragment> cls) {
        ItemDescription itemDescription = getDescription(cls);
        if (itemDescription == null) {
            return null;
        }
        return itemDescription.getName();
    }

    public List<ItemDescription> getHomeDescriptions() {
        List<ItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mHomeNames.size(); i++) {
            list.add(mWidgetContainer.get(mHomeNames.get(i)));
        }
        return list;
    }

    public List<ItemDescription> getUtilDescriptions() {
        List<ItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mUtilNames.size(); i++) {
            list.add(mWidgetContainer.get(mUtilNames.get(i)));
        }
        return list;
    }

    public List<ItemDescription> getOtherDescriptions() {
        List<ItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mOtherNames.size(); i++) {
            list.add(mWidgetContainer.get(mOtherNames.get(i)));
        }
        return list;
    }
}
