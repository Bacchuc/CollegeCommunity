package com.yzd.collegecommunity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yzd.collegecommunity.util.ActivityCollectorUtil;

/**
 * Created by Laiyin on 2017/2/27.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
