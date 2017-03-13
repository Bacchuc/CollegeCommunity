package com.yzd.collegecommunity.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.util.AnimatorUtil;
import com.yzd.collegecommunity.util.BlurBehind;

/**
 * Created by Laiyin on 2017/3/6.
 */

public class MainFabPublicActivity extends BaseActivity  {

    private ImageButton ib_task;
    private ImageButton ib_goods;

    AnimatorUtil animatorUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fab);
        initView();
        BlurBehind();
    }

    public void initView(){

        ib_task= (ImageButton) findViewById(R.id.ib_task);
        ib_goods= (ImageButton) findViewById(R.id.ib_goods);

        animatorUtil=new AnimatorUtil();

        animatorUtil.scaleAndTranslationAnimator(ib_task,0.3f, 1F, 0.3f, 1F, 0, -230F, 0, -180, 400,null);
        animatorUtil.scaleAndTranslationAnimator(ib_goods,0.3f, 1F, 0.3f, 1F, 0, -230F, 0, 180, 400,null);

    }

    public void BlurBehind(){
        BlurBehind.getInstance().setBackground(this);
        BlurBehind.getInstance()
                .withAlpha(80)
                .withFilterColor(Color.parseColor("#0075c0"))
                .setBackground(this);
    }
}
