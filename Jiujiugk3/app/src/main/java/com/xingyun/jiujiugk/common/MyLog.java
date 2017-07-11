package com.xingyun.jiujiugk.common;

import android.content.Context;
import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class MyLog {
    private final static boolean isDebug = BuildConfig.DEBUG;
    private final static String TAG = "jiujiugk";

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (isDebug) {
            Log.e(TAG, msg, tr);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }
}
