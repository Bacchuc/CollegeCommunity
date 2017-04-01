package com.yzd.collegecommunity.util;

import android.widget.Toast;
import android.content.Context;
/**
 * Created by Laiyin on 2017/3/29.
 */

public class ToastUtil {

    public static void showShort(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }

}
