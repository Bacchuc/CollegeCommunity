package com.yzd.collegecommunity.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Laiyin on 2017/3/29.
 */

public class AppCenterUtil extends Application {

    private static Context context;

    @Override
    public void onCreate(){
        //获取Context
        context=getApplicationContext();
    }

    public static Context getContextObject(){
        return context;
    }
}
