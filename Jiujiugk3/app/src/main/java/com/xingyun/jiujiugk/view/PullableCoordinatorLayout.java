package com.xingyun.jiujiugk.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jingchen.pulltorefresh.Pullable;

/**
 * Created by wangqf on 2017/2/27 0027.
 */

public class PullableCoordinatorLayout extends CoordinatorLayout implements Pullable, AppBarLayout.OnOffsetChangedListener, ViewPager.OnPageChangeListener {
    private boolean mAppBarLayoutCanPull = true;  //appbarLayout满足下拉要求
    private boolean mViewPagerCanPull = true;   //viewPager满足下拉要求

    public PullableCoordinatorLayout(Context context) {
        super(context);
    }

    public PullableCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean canPullDown() {
        return (mAppBarLayoutCanPull && mViewPagerCanPull);
    }

    @Override
    public boolean canPullUp() {
        return false;
    }

    public void setCanRefreshListener() {
        View child0 = getChildAt(0);
        if (child0 instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) child0;
            appBarLayout.addOnOffsetChangedListener(this);
        }
        View child1 = getChildAt(1);
        if (child1 instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) child1;
            viewPager.addOnPageChangeListener(this);
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mAppBarLayoutCanPull = (verticalOffset >= 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mViewPagerCanPull = (positionOffset == 0);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
