package com.yzd.collegecommunity.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.util.AnimatorUtil;
import com.yzd.collegecommunity.util.BlurBehind;

/**
 * Created by Laiyin on 2017/2/28.
 */

public class ThirdActivity extends BaseActivity {

    private ImageButton ib_qq;
    private ImageButton ib_weixin;
    private ImageButton ib_xinlang;

    AnimatorUtil animatorUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();
        BlurBehind();
    }

    public void initView(){

        ib_qq= (ImageButton) findViewById(R.id.ib_qq);
        ib_weixin= (ImageButton) findViewById(R.id.ib_weixin);
        ib_xinlang= (ImageButton) findViewById(R.id.ib_xinlang);

        animatorUtil=new AnimatorUtil();

        animatorUtil.scaleAndTranslationAnimator(ib_qq,0.3f, 1F, 0.3f, 1F, 0, -180F, 0, -250, 400,null);
        animatorUtil.scaleAndTranslationAnimator(ib_weixin,0.3f, 1F, 0.3f, 1F, 0, -250F, 0, 0, 400,null);
        animatorUtil.scaleAndTranslationAnimator(ib_xinlang,0.3f, 1F, 0.3f, 1F, 0, -180F, 0, 250, 400,null);

    }

    public void BlurBehind(){
        BlurBehind.getInstance().setBackground(this);
        BlurBehind.getInstance()
                .withAlpha(80)
                .withFilterColor(Color.parseColor("#0075c0"))
                .setBackground(this);
    }
}
