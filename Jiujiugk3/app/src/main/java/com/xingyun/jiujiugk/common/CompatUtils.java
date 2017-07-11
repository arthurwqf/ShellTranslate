package com.xingyun.jiujiugk.common;

import android.content.Context;

/**
 * 适配单元
 * Created by wangqf on 2016/11/10 0010.
 */

public class CompatUtils {
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
