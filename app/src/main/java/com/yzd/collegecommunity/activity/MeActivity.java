package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.yzd.collegecommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/14.
 */

public class MeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_username)
    AnimatedGradientTextView tvUsername;
    @BindView(R.id.tv_school)
    AnimatedGradientTextView tvSchool;
    @BindView(R.id.rl_task)

    RelativeLayout rlTask;
    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.rl_contact)
    RelativeLayout rlContact;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;

    SwipeBackActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity);
        ButterKnife.bind(this);

        helper = new SwipeBackActivityHelper();

        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);
    }

    @OnClick({R.id.rl_task, R.id.rl_goods, R.id.rl_collect, R.id.rl_contact, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_task:
                Intent intent = new Intent(MeActivity.this, MeTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_goods:
                Intent intent1 = new Intent(MeActivity.this, MeGoodsActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_collect:
                Intent intent2 = new Intent(MeActivity.this, MeCollectActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_contact:
                Intent intent3 = new Intent(MeActivity.this, MeContactActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_setting:
                Intent intent4 = new Intent(MeActivity.this, MeSettingActivity.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        helper.finish();
    }
}
