package com.yzd.collegecommunity.util;

import android.util.Log;

/**
 * Created by Laiyin on 2017/2/28.
 * 设置debug
 */

public class LUtil {
    private static final String TAG="http://www.baidu.com";
    private static boolean debug=true;   //debug为开关

    public static void e(String msg){
        if (debug)
            Log.e(TAG,msg);
    }
}
