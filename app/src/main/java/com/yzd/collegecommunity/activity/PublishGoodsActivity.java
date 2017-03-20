package com.yzd.collegecommunity.activity;

import android.os.Bundle;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yzd.collegecommunity.R;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class PublishGoodsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity_goods);

        //侧滑效果
        SwipeBackHelper.onCreate(this);
    }

    //侧滑效果
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
