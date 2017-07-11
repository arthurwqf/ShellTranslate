package com.xingyun.jiujiugk;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Process;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xingyun.jiujiugk.common.CommonField;
import com.xingyun.jiujiugk.common.ConstantValue;
import com.xingyun.jiujiugk.common.MyLog;
import com.xingyun.jiujiugk.model.ModelUser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class JiujiugkApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);

        SharedPreferences shared = getSharedPreferences(ConstantValue.SHARED_PREFERENCES, MODE_PRIVATE);
        int id = shared.getInt(ConstantValue.SHARED_USER_ID, -1);
        if (id > -1) {
            CommonField.user = new ModelUser();
            CommonField.user.setUser_id(id);
            CommonField.user.setAvatar(shared.getString(ConstantValue.SHARED_USER_AVATAR, ""));
            CommonField.user.setIm_token(shared.getString(ConstantValue.SHARED_USER_TOKEN, ""));
            CommonField.user.setQr_code(shared.getString(ConstantValue.SHARED_USER_QR_CODE, ""));
            CommonField.user.setRealname(shared.getString(ConstantValue.SHARED_USER_NAME, ""));
            CommonField.user.setGroup_id(shared.getInt(ConstantValue.SHARED_USER_GROUPD_ID, -1));
            CommonField.user.setIs_joint(shared.getInt(ConstantValue.SHARED_USER_IS_JOINT, -1));
            CommonField.user.setCerti_id(shared.getInt(ConstantValue.SHARED_USER_CERTI_ID, 0));
        }

        initOkHttp();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        MyLog.e("未捕获异常：" + t.getName(), e);
        android.os.Process.killProcess(Process.myPid());
    }

    private void initOkHttp() {
        ClearableCookieJar clearableCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("jiujiugk"))
                .cookieJar(clearableCookieJar)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
