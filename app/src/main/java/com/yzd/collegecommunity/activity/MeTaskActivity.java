package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.fragment.MeTaskMyPublishFragment;
import com.yzd.collegecommunity.fragment.MeTaskOtherPublishFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class MeTaskActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;
    @BindView(R.id.ib_publish)
    ImageButton ibPublish;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_task);
        ButterKnife.bind(this);
        initView();
        //侧滑效果
        SwipeBackHelper.onCreate(this);
    }

    private void initView() {

        fragmentManager = getSupportFragmentManager();

        ((RadioButton) rgBottom.findViewById(R.id.rb_my_publish)).setChecked(true);  //初始选中首页第一个

        checkFragment(MeTaskMyPublishFragment.class);    //初始选中首页里的第一个页面

        rgBottom.setOnCheckedChangeListener(this);

    }

    public void checkFragment(Class<?> myFragment) {
        transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        try {
			//实例化默认构造方法
            fragment = (Fragment) myFragment.newInstance();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        transaction.replace(R.id.fl_task, fragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_my_publish:
                checkFragment(MeTaskMyPublishFragment.class);
                break;
            case R.id.rb_others_publish:
                checkFragment(MeTaskOtherPublishFragment.class);
                break;
        }
    }

    //侧滑效果
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    //侧滑效果
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    @OnClick(R.id.ib_publish)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_publish:
                Intent intent = new Intent(MeTaskActivity.this, PublishTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
