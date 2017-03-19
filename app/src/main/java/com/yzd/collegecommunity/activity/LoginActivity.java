package com.yzd.collegecommunity.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MyFragmentPagerAdapter;
import com.yzd.collegecommunity.fragment.Login_LoginFragment;
import com.yzd.collegecommunity.fragment.Login_RegisterFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yzd.collegecommunity.R.id.bt_login;
import static com.yzd.collegecommunity.R.id.bt_register;

/**
 * Created by Laiyin on 2017/2/25.
 * <p>
 * 登录页面
 */

public class LoginActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.vp_login)
    ViewPager vpLogin;

    public String TAG = "LoginActivity";

    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;

    //按钮的集合
    private Button[] btArgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        initView();
        initListener();

        onPageSelected(0);
    }

    private void initView() {

        fragments = new ArrayList<Fragment>();
        fragments.add(new Login_LoginFragment());
        fragments.add(new Login_RegisterFragment());

        btArgs = new Button[]{btLogin, btRegister};

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        vpLogin.setAdapter(adapter);
    }

    private void initListener() {
        vpLogin.addOnPageChangeListener(this);
    }

    //重置按钮颜色 浅黑色
    public void resetButtonTextColor() {
        btLogin.setTextColor(Color.parseColor("#D0D0D0"));
        btRegister.setTextColor(Color.parseColor("#D0D0D0"));
    }

    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case bt_login:
                vpLogin.setCurrentItem(0);
                break;
            case bt_register:
                vpLogin.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetButtonTextColor();
        btArgs[position].setTextColor(Color.parseColor("#FCFCFC"));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override

//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.bt_login:
////                OkHttpUtils
////                        .post()
////                        .url(Constants.BASEURL+"textPost.action")
////                        .addParams("username", et_username.getText().toString())
////                        .build()
////                        .execute(new StringCallback() {
////                            @Override
////                            public void onError(Call call, Exception e, int id) {
////
////                            }
////
////                            @Override
////                            public void onResponse(String response, int id) {
////
//                                final String res=response;
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        password.setText(res);
//                                    }
//                                });
////                            }
////                        });
//
//                OkHttpUtils
//                        .get()
//                        .url(Constants.BASEURL+"download")
//                        .build()
//                        .execute(new BitmapCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
////                                password.setText("onError:"+e.getMessage());
//                            }
//
//                            @Override
//                            public void onResponse(Bitmap response, int id) {
//
//                                final Bitmap bitmap=response;
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
////                                        iv_login.setImageBitmap(bitmap);
//                                    }
//                                });
//                            }
//                        });
//
////
////                File filePhoto=new File(iv_login.getDrawingCache());
////
////                iv_login.setDrawingCacheEnabled(true);
////                Bitmap bitmap=iv_login.getDrawingCache();
////                iv_login.setDrawingCacheEnabled(false);
////
////
////
////                OkHttpUtils
////                        .postFile()
////                        .url(Constants.BASEURL+"alterPhoto")
////                        .file(filePhoto)
////                        .build()
////                        .execute(new StringCallback() {
////                            @Override
////                            public void onError(Call call, Exception e, int id) {
////
////                            }
////
////                            @Override
////                            public void onResponse(String response, int id) {
////
////                            }
////                        });
//
//                break;
////            case R.id.tv_forget:
////                break;
//            case R.id.bt_third:
//                BlurBehind.getInstance().execute(LoginActivity.this, new OnBlurCompleteListener() {
//                    @Override
//                    public void onBlurComplete() {
//                        Intent intent1 = new Intent(LoginActivity.this, ThirdActivity.class);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent1);
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }


}
