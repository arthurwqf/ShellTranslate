package com.xingyun.jiujiugk.common.http;

import com.xingyun.jiujiugk.common.CommonField;
import com.xingyun.jiujiugk.common.ConstantValue;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class HttpUtils {

    public static String getURL(Map<String, String> params) {
        StringBuffer stringBuffer = new StringBuffer(ConstantValue.BASE_URL);
        if (null != params) {
            Iterator<Map.Entry<String, String>> i = params.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, String> sEntry = i.next();
                if (stringBuffer.length() == ConstantValue.BASE_URL.length()) {
                    stringBuffer.append("?");
                } else {
                    stringBuffer.append("&");
                }
                stringBuffer.append(sEntry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(sEntry.getValue());
            }
            stringBuffer.append(params.size() > 0 ? "&" : "?").append("v=android_").append(CommonField.VersionName);
        }
        return stringBuffer.toString();
    }

    public static void get(Map<String, String> params, StringCallback stringCallback) {
        OkHttpUtils.get()
                .url(ConstantValue.BASE_URL)
                .params(params)
                .addParams("v", CommonField.VersionName)
                .build()
                .execute(stringCallback);
    }

    public static void post(Map<String, String> urlParams, Map<String, String> params, StringCallback stringCallback) {
        OkHttpUtils.post()
                .url(getURL(urlParams))
                .params(params)
                .build()
                .execute(stringCallback);
    }

    public static void postFile(Map<String, String> urlParams, File file, StringCallback stringCallback) {
        OkHttpUtils.postFile()
                .url(getURL(urlParams))
                .file(file)
                .build()
                .execute(stringCallback);
    }

    public static void downloadFile(Map<String, String> params, FileCallBack fileCallBack) {
        OkHttpUtils.get()
                .url(ConstantValue.BASE_URL)
                .params(params)
                .build()
                .execute(fileCallBack);
    }
}
