package com.cykj.survey.fragment.home;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {

    private final static String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabs;
    private HashMap<Pager,HomeController> mPages;
    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        private int mChildCount = 0;

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            HomeController page = mPages.get(Pager.getPagerFromPositon(position));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(page,params);
            return page;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            if (mChildCount == 0){
                return POSITION_NONE;
            }

            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }
    };

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);
        initTabs();
        return layout;
    }

    private void initTabs() {

        int normalColor = QMUIResHelper.getAttrColor(getActivity(),R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(),R.attr.qmui_config_color_blue);

        mTabs.setDefaultNormalColor(normalColor);
        mTabs.setDefaultSelectedColor(selectColor);

        QMUITabSegment.Tab home = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_component_selected),
                "主页",false
        );

        QMUITabSegment.Tab util = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_util),
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_util_selected),
                "工具",false
        );

        QMUITabSegment.Tab other = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_lab),
                ContextCompat.getDrawable(getContext(),R.mipmap.icon_tabbar_lab_selected),
                "其他",false
        );

        mTabs.addTab(home)
             .addTab(util)
             .addTab(other);

    }

    private void initPagers(){

        HomeController.HomeControlListener listener = new HomeController.HomeControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                HomeFragment.this.startFragment(fragment);
            }
        };

        mPages = new HashMap<>();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    enum Pager{
        HOME,UTIL,OTHER;

        public static Pager getPagerFromPositon(int position){
            switch (position){
                case 0:
                    return HOME;
                case 1:
                    return UTIL;
                case 2:
                    return OTHER;
                default:
                    return HOME;
            }
        }
    }
}
