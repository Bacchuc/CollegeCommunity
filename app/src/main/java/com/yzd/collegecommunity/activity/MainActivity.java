package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MyFragmentPagerAdapter;
import com.yzd.collegecommunity.fragment.Main_GoodsFragment;
import com.yzd.collegecommunity.fragment.Main_RankingFragment;
import com.yzd.collegecommunity.fragment.Main_TaskFragment;
import com.yzd.collegecommunity.util.BlurBehind;
import com.yzd.collegecommunity.util.OnBlurCompleteListener;

import java.util.ArrayList;

/**
 * Created by Laiyin on 2017/3/5.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private TextView tv_left;
    private Button bt_task;
    private Button bt_goods;
    private Button bt_ranking_list;
    private ImageView iv_tab;

    private FloatingActionButton fab;

    public String TAG="MainActivity";

    private ViewPager vp_main;

    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;

    //按钮的集合
    private Button[] btArgs;

    //标签偏移
    private int index; //当前页卡;
    private int imgleth; //图片宽度
    private int offset; //偏移量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initView();

        initListener();

        onPageSelected(0);
    }


    private void initView() {
        tv_left= (TextView) findViewById(R.id.tv_left);
        bt_task= (Button) findViewById(R.id.bt_task);
        bt_goods= (Button) findViewById(R.id.bt_goods);
        bt_ranking_list= (Button) findViewById(R.id.bt_ranking_list);
        iv_tab= (ImageView) findViewById(R.id.iv_tab);

        fab= (FloatingActionButton) findViewById(R.id.fab);

        vp_main=(ViewPager)findViewById(R.id.vp_main);

        fragments=new ArrayList<Fragment>();
        fragments.add(new Main_TaskFragment());
        fragments.add(new Main_GoodsFragment());
        fragments.add(new Main_RankingFragment());

        btArgs = new Button[]{bt_task,bt_goods,bt_ranking_list};     //将滑动的buttonTab放进一个集合

        MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        vp_main.setAdapter(adapter);

        //获取图片宽度
        imgleth= BitmapFactory.decodeResource(getResources(), R.drawable.tab).getWidth();
        //获取屏幕宽度
        DisplayMetrics dm=new DisplayMetrics();
        // 把屏幕尺寸信息赋值给DisplayMetrics dm，注意不是set
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度
        int count=dm.widthPixels;
        //计算偏移量
        offset=(count/3-imgleth)/2;

        //平移动画(第一页的)
        Animation an=new TranslateAnimation(0, offset, 0, 0);
        an.setFillAfter(true);
        an.setDuration(200);
        iv_tab.setAnimation(an);

    }

    private void initListener() {
        tv_left.setOnClickListener(this);
        bt_task.setOnClickListener(this);
        bt_goods.setOnClickListener(this);
        bt_ranking_list.setOnClickListener(this);
        fab.setOnClickListener(this);

        vp_main.addOnPageChangeListener(this);
    }

    //重置按钮颜色 为浅黑色
    public void resetButtonTextColor(){
        bt_task.setTextColor(Color.parseColor("#D0D0D0"));
        bt_goods.setTextColor(Color.parseColor("#D0D0D0"));
        bt_ranking_list.setTextColor(Color.parseColor("#D0D0D0"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int i) {

        int one=offset*2+imgleth;//相邻页面的偏移量

        resetButtonTextColor();
        btArgs[i].setTextColor(Color.parseColor("#272727"));

        //评议动画
        Animation anima=new TranslateAnimation(index*one+offset,i*one+offset,0,0);
        index=i; //当前页跟着变
        anima.setFillAfter(true); // 动画终止时停留在最后一帧，不然会回到没有执行前的状态
        anima.setDuration(200);// 动画持续时间0.2秒
        iv_tab.startAnimation(anima);// 是用ImageView来显示动画的
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                Intent intent = new Intent(MainActivity.this, MeActivity.class);
                startActivity(intent);
                break;
            case R.id.fab:
                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent1 = new Intent(MainActivity.this, MainFabPublicActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                    }
                });
                break;
            case R.id.bt_task:
                vp_main.setCurrentItem(0);
                break;
            case R.id.bt_goods:
                vp_main.setCurrentItem(1);
                break;
            case R.id.bt_ranking_list:
                vp_main.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
