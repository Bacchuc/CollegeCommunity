package com.yzd.collegecommunity.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/19.
 */

public class MeTaskOtherPublishFragment extends Fragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.bt_accept)
    Button btAccept;
    @BindView(R.id.bt_under_way)
    Button btUnderWay;
    @BindView(R.id.bt_finished)
    Button btFinished;
    @BindView(R.id.iv_tab)
    ImageView ivTab;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    //fragment的集合，对应每个子页面
    private ArrayList<android.support.v4.app.Fragment> fragments;

    //按钮的集合
    private Button[] btArgs;

    //标签偏移
    private int index; //当前页卡;
    private int imgleth; //图片宽度
    private int offset; //偏移量

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_task_otherpublish, container, false);

        ButterKnife.bind(this, view);
        initView();
        onPageSelected(0);
        return view;
    }

    private void initView() {

        fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(new Main_TaskFragment());
        fragments.add(new Main_GoodsFragment());
        fragments.add(new Main_RankingFragment());

        btArgs = new Button[]{btAccept, btUnderWay, btFinished};     //将滑动的buttonTab放进一个集合

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);

        //获取图片宽度
        imgleth = BitmapFactory.decodeResource(getResources(), R.drawable.tab).getWidth();
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        // 把屏幕尺寸信息赋值给DisplayMetrics dm，注意不是set
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度
        int count = dm.widthPixels;
        //计算偏移量
        offset = (count / 3 - imgleth) / 2;

        //平移动画(第一页的)
        Animation an = new TranslateAnimation(0, offset, 0, 0);
        an.setFillAfter(true);
        an.setDuration(200);
        ivTab.setAnimation(an);

        vpMain.addOnPageChangeListener(this);

    }

    //重置按钮颜色 为浅黑色
    public void resetButtonTextColor() {
        btAccept.setTextColor(Color.parseColor("#D0D0D0"));
        btUnderWay.setTextColor(Color.parseColor("#D0D0D0"));
        btFinished.setTextColor(Color.parseColor("#D0D0D0"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int i) {

        int one = offset * 2 + imgleth;//相邻页面的偏移量

        resetButtonTextColor();
        btArgs[i].setTextColor(Color.parseColor("#272727"));

        //评议动画
        Animation anima = new TranslateAnimation(index * one + offset, i * one + offset, 0, 0);
        index = i; //当前页跟着变
        anima.setFillAfter(true); // 动画终止时停留在最后一帧，不然会回到没有执行前的状态
        anima.setDuration(200);// 动画持续时间0.2秒
        ivTab.startAnimation(anima);// 是用ImageView来显示动画的
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.bt_accept, R.id.bt_under_way, R.id.bt_finished})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_accept:
                vpMain.setCurrentItem(0);
                break;
            case R.id.bt_under_way:
                vpMain.setCurrentItem(1);
                break;
            case R.id.bt_finished:
                vpMain.setCurrentItem(2);
                break;
        }
    }
}
