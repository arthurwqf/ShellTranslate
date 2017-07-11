package com.xingyun.jiujiugk.common;

import com.xingyun.jiujiugk.BuildConfig;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public interface ConstantValue {
    //测试环境
    String BASE_URL_DEBUG = "http://app.99test.xingyun.net/index.php";
    //生产环境
    String BASE_URL_RELEASE = "http://app.99.xingyun.net/index.php";
    String BASE_URL = BuildConfig.DEBUG ? BASE_URL_DEBUG : BASE_URL_RELEASE;


    String SHARED_PREFERENCES = "jiujiugk_shared";
    //保存在shared_preferences中的用户信息
    String SHARED_USER_ID = "id";
    String SHARED_USER_AVATAR = "user_avatar";
    String SHARED_USER_NAME = "user_name";
    String SHARED_USER_TOKEN = "user_token";
    String SHARED_USER_QR_CODE = "user_qr_code";
    String SHARED_USER_THEME_BG = "user_theme_bg";
    String SHARED_USER_GROUPD_ID = "user_group_id";
    String SHARED_USER_IS_JOINT = "user_is_joint";
    String SHARED_JOB_TITLE = "job_title";
    String SHARED_HOSPITAL_TITLE = "hospital_title";
    String SHARED_IS_JOINT_REVIEWER = "is_joint_reviewer";
    String SHARED_USER_CERTI_ID = "user_cerit_id";

    //加密key
    String DESKEY = "WsVcHjqWeg8fccRNYufyDuiTtETC7MPC";

    //文件夹名称
    String BASEDIRECTORYPATH = "jiujiugk";
}
