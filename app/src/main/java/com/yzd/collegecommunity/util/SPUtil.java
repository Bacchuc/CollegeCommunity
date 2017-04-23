package com.yzd.collegecommunity.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.yzd.collegecommunity.constants.Constants;

/**
 * Created by Laiyin on 2017/4/16.
 */

public class SPUtil {
    SPUtil() {/* cannot be instantiated */}

    public static void refresh(String userId, String session) {

        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(Constants.USER_ID, userId);
        editor.putString(Constants.SESSION_ID, session);
        editor.apply();

    }

    /**
     * 刷新token 在登陆成功后得到token，存到SP中
     * @param token
     */
    public static void refreshToken(String token) {

        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(Constants.TOKEN, token);
        editor.apply();

    }

    /**
     * 所有需要用到token的请求，从SP中取token
     * @return token
     */
    public static String getToken() {
        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        return sp.getString(Constants.TOKEN, "nullToken");
    }

    public static String getUserId() {
        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(Constants.USER_ID, "");
    }

    public static String getSessionId() {
        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(Constants.SESSION_ID, "");
    }

    /**
     * 保存登录类型，1为学生，0为老师。
     *
     * @param type 类型
     */
    public static void saveLoginType(int type) {
        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.SP_LOGIN_TYPE, type);
        editor.apply();
    }

    /**
     * 获取登录类型，-1为未登录
     *
     * @return 登录类型
     */
    public static int getLoginType() {
        SharedPreferences sp = AppCenterUtil.getContextObject().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(Constants.SP_LOGIN_TYPE, -1);
    }
}
