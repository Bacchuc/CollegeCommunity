package com.yzd.collegecommunity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import android.content.Intent;

import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.yzd.collegecommunity.R;

/**
 * Created by Laiyin on 2017/3/14.
 */

public class MeActivity extends BaseActivity implements View.OnClickListener{

    private AnimatedGradientTextView tv_username;
    private AnimatedGradientTextView tv_school;
    private RelativeLayout ll_task;
    private RelativeLayout ll_goods;
    private RelativeLayout ll_collect;
    private RelativeLayout ll_contact;
    private RelativeLayout ll_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity);

        initListener();

        initView();

    }

    private void initView() {
        tv_username= (AnimatedGradientTextView) findViewById(R.id.tv_username);
        tv_school= (AnimatedGradientTextView) findViewById(R.id.tv_school);
        ll_task= (RelativeLayout) findViewById(R.id.ll_task);
        ll_goods= (RelativeLayout) findViewById(R.id.ll_goods);
        ll_collect= (RelativeLayout) findViewById(R.id.ll_collect);
        ll_contact= (RelativeLayout) findViewById(R.id.ll_contact);
        ll_setting= (RelativeLayout) findViewById(R.id.ll_setting);
    }

    private void initListener() {
        tv_username.setOnClickListener(this);
        tv_school.setOnClickListener(this);
        ll_task.setOnClickListener(this);
        ll_goods.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_contact.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_username:
                Intent intent=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_school:
                Intent intent2=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_task:
                Intent intent3=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_goods:
                Intent intent4=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_collect:
                Intent intent5=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_contact:
                Intent intent6=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent6);
                break;
            case R.id.ll_setting:
                Intent intent7=new Intent(MeActivity.this,MeActivity.class);
                startActivity(intent7);
                break;
            default:
                break;
        }
    }
}
