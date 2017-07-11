package com.xingyun.jiujiugk.main;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xingyun.jiujiugk.R;

/**
 * Created by wangqf on 2017/2/24 0024.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private Toolbar mBaseToolbar;
    private View mRootView;
    private FrameLayout mContentView;
    private boolean mTitleLeftIsReturn = true; //顶部左侧是返回按钮吗
    private TextView mTvBaseTitle;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mContext = BaseActivity.this;
        init(layoutResID);
        initTitle();
        setTopLeftReturn();
    }

    private void init(int layoutResID) {
        mBaseToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRootView = findViewById(R.id.root);

        mTvBaseTitle = (TextView) findViewById(R.id.tv_base_title);

        mContentView = (FrameLayout) findViewById(R.id.contentView);
        mContentView.addView(getLayoutInflater().inflate(layoutResID, null),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setTopLeftReturn() {
        setSupportActionBar(mBaseToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (mTitleLeftIsReturn) {
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            mBaseToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    protected void setMenu(){

    }

    protected abstract void initTitle();

    protected void setActivityTitle(@StringRes int id) {
        mTvBaseTitle.setText(getString(id));
    }

    protected void setTitleLeftIcon(@DrawableRes int id, View.OnClickListener listener) {
        mBaseToolbar.setNavigationIcon(id);
        mBaseToolbar.setNavigationOnClickListener(listener);
    }

    protected void setTitleLeftIsReturn(boolean isReturn) {
        mTitleLeftIsReturn = isReturn;
    }
}
