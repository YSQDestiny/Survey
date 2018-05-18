package com.cykj.survey;

import android.content.Intent;
import android.os.Bundle;

import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.home.HomeFragment;

public class MainActivity extends BaseFragmentActivity{

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            BaseFragment fragment = new HomeFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(),fragment,fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

}
