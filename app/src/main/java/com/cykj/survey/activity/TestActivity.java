package com.cykj.survey.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_test, null);
        ButterKnife.bind(this,root);
        initTopBar();
        QMUIStatusBarHelper.translucent(this);//沉浸式状态栏
        setContentView(root);
    }

    private void initTopBar() {

        topbar.setBackgroundColor(ContextCompat.getColor(this,R.color.app_color_theme_4));
        topbar.setTitle("测试");

    }
}
