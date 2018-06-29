package com.cykj.survey.activity;

import android.os.Bundle;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.home.HomeFragment;
import com.cykj.survey.fragment.index.IndustryFragment;

public class IndustryActivity extends BaseFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            BaseFragment fragment = new IndustryFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(),fragment,fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
