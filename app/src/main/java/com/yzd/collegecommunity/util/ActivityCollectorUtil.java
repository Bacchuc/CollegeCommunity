package com.yzd.collegecommunity.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laiyin on 2017/2/27.
 *
 * 活动管理工具类
 */

public class ActivityCollectorUtil {

    public static List<Activity> activities=new ArrayList<Activity>();


    /**
     * 添加继承BaseActivity的活动进栈
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }


    /**
     * 移除栈中某个继承BaseActivity的活动
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }


    /**
     * 移除栈中所有继承BaseActivity的活动
     */
    public static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
