package com.cykj.survey.model;

import com.cykj.survey.base.BaseFragment;

public class ItemDescription {

    private Class<? extends BaseFragment> mKitDemoClass;

    private String mKitName;

    private String mKitDetailDescription;

    private int mIconRes;

    public ItemDescription(Class<? extends BaseFragment> kitDemoClass,String kitName){
        this(kitDemoClass,kitName,0);
    }

    public ItemDescription(Class<? extends BaseFragment> kitDemoClass,String kitName,int iconRes){
        mKitDemoClass = kitDemoClass;
        mKitName = kitName;
        mIconRes = iconRes;
    }

    public ItemDescription(Class<? extends BaseFragment> kitDemoClass,String kitName,
                           String kitDetailDescription,int iconRes){
        mKitDemoClass = kitDemoClass;
        mKitName = kitName;
        mKitDetailDescription = kitDetailDescription;
        mIconRes = iconRes;
    }

    public void setItemDetailDescription(String kitDetailDesctription){
        mKitDetailDescription = kitDetailDesctription;
    }

    public Class<? extends BaseFragment> getDemoClass() {
        return mKitDemoClass;
    }

    public String getName() {
        return mKitName;
    }

    public String getItemDetailDescription() {
        return mKitDetailDescription;
    }

    public int getIconRes() {
        return mIconRes;
    }
}
