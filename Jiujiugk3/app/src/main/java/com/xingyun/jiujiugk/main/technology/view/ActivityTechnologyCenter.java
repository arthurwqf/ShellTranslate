package com.xingyun.jiujiugk.main.technology.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.xingyun.jiujiugk.R;
import com.xingyun.jiujiugk.common.ViewPagerAdapter;
import com.xingyun.jiujiugk.common.imageloader.GlideImageLoader;
import com.xingyun.jiujiugk.main.BaseActivity;
import com.xingyun.jiujiugk.view.PullableCoordinatorLayout;
import com.xingyun.jiujiugk.view.ViewPagerIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class ActivityTechnologyCenter extends BaseActivity implements ITechnologyCenter {

    private static final String[] tabs = new String[]{"全部", "收藏", "已报名"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_center);
        initView();
    }

    @Override
    protected void initTitle() {
        setActivityTitle(R.string.technology_center);
    }

    private void initView() {
        initBanner();

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_bottom);
        ViewPagerIndicator indicator = (ViewPagerIndicator) findViewById(R.id.vpi);

        ArrayList<Fragment> fragments = new ArrayList<>();
        FragmentTechnologyCenter fragmentTechnologyCenter1 = new FragmentTechnologyCenter();
        FragmentTechnologyCenter fragmentTechnologyCenter2 = new FragmentTechnologyCenter();
        FragmentTechnologyCenter fragmentTechnologyCenter3 = new FragmentTechnologyCenter();
        fragments.add(fragmentTechnologyCenter1);
        fragments.add(fragmentTechnologyCenter2);
        fragments.add(fragmentTechnologyCenter3);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));

        PullableCoordinatorLayout pullCl = (PullableCoordinatorLayout) findViewById(R.id.pull_cl);
        pullCl.setCanRefreshListener();
        indicator.setVisibleTabCount(3);
        indicator.setTabItemTitle(tabs);
        indicator.setViewPager(viewPager, 0);
    }

    private void initBanner() {
        Banner banner = (Banner) findViewById(R.id.banner_header);

        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.b1);
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b3);

        banner.setImages(list)
                .setIndicatorGravity(BannerConfig.NOT_INDICATOR)
                .setImageLoader(GlideImageLoader.getInstance())
                .start();
    }


    private class RefreshTechnologyListener implements PullToRefreshLayout.OnPullListener {

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

        }
    }
}
