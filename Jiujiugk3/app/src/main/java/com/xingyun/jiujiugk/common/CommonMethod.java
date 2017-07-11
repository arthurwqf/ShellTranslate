package com.xingyun.jiujiugk.common;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class CommonMethod {

    /**
     * 获取sd卡上的保存路径
     *
     * @return
     */
    public static String getSDCardBaseDirectory() {
        String baseDir = Environment.getExternalStorageDirectory() + "/" + ConstantValue.BASEDIRECTORYPATH;
        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return baseDir;
    }

    public static void hideInputMethod(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
