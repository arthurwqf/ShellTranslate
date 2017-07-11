package com.xingyun.jiujiugk.main.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xingyun.jiujiugk.R;
import com.xingyun.jiujiugk.common.imageloader.GlideImageLoader;
import com.xingyun.jiujiugk.main.technology.view.ActivityTechnologyCenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        Banner banner = (Banner) findViewById(R.id.banner);

        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b3);

        banner.setImages(list)
                .setIndicatorGravity(BannerConfig.NOT_INDICATOR)
                .setImageLoader(GlideImageLoader.getInstance())
                .start();

        findViewById(R.id.item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityTechnologyCenter.class));
            }
        });

    }
}
