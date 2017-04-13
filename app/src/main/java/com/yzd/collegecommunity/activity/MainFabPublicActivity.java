package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.util.AnimatorUtil;
import com.yzd.collegecommunity.util.BlurBehind;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/6.
 *
 * 悬浮窗页面 背景透明
 *
 */

public class MainFabPublicActivity extends BaseActivity implements View.OnClickListener {

    AnimatorUtil animatorUtil;

    @BindView(R.id.ib_task)
    ImageButton ibTask;
    @BindView(R.id.ib_goods)
    ImageButton ibGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_fab_publish);
        ButterKnife.bind(this);
        initView();
        BlurBehind();
    }

    public void initView() {

        animatorUtil = new AnimatorUtil();
        animatorUtil.scaleAndTranslationAnimator(ibTask, 0.3f, 1F, 0.3f, 1F, 0, -230F, 0, -180, 400, null);
        animatorUtil.scaleAndTranslationAnimator(ibGoods, 0.3f, 1F, 0.3f, 1F, 0, -230F, 0, 180, 400, null);
    }

    public void BlurBehind() {
        BlurBehind.getInstance().setBackground(this);
        BlurBehind.getInstance()
                .withAlpha(80)
                .withFilterColor(Color.parseColor("#0075c0"))
                .setBackground(this);
    }

    @OnClick({R.id.ib_task, R.id.ib_goods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_task:
                Intent intent = new Intent(MainFabPublicActivity.this, PublishTaskActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ib_goods:
                Intent intent1 = new Intent(MainFabPublicActivity.this, PublishGoodsActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
