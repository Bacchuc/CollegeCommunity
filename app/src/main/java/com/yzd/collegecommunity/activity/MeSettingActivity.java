package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.PopupWindowSelectUtil;
import com.yzd.collegecommunity.util.RetrofitUtil;
import com.yzd.collegecommunity.util.SPUtil;
import com.yzd.collegecommunity.util.SelectImageUtil;
import com.yzd.collegecommunity.util.ToastUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class MeSettingActivity extends BaseActivity {

    @BindView(R.id.iv_username)
    EditText ivUsername;
    @BindView(R.id.iv_email)
    EditText ivEmail;
    @BindView(R.id.iv_school)
    EditText ivSchool;
    @BindView(R.id.iv_password)
    EditText ivPassword;
    @BindView(R.id.ib_head_picture)
    CircleImageView ibHeadPicture;
    @BindView(R.id.bt_commit)
    Button btCommit;

    PopupWindowSelectUtil popupWindowSelect;
    SelectImageUtil selectImageUtilResult;
    private SubscriberOnNextListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_setting);
        ButterKnife.bind(this);
        //侧滑效果
        SwipeBackHelper.onCreate(this);

        initView();
    }

    private void initView() {
        popupWindowSelect = new PopupWindowSelectUtil(this, MeSettingActivity.this, R.layout.me_activity_setting);
        popupWindowSelect.setOnPopWindowOptionListener(new PopupWindowSelectUtil.OnPopWindowOptionListener() {
            @Override
            public void onTakePhoto() {
                selectImageUtilResult.takePicture();
            }

            @Override
            public void onChoosePhoto() {
                selectImageUtilResult.choosePicture();
            }
        });

        selectImageUtilResult = new SelectImageUtil(this, ibHeadPicture);
    }

    //侧滑效果
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    /**
     * 重写上传照片拍照与选取照片功能时需要的onActivityResult，回调PopupWindowSelectUtil中的onActivityResult，
     * onActivityResult再回调选择照片的工具类SelectImageUtil
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectImageUtilResult = new SelectImageUtil(this, ibHeadPicture);
        selectImageUtilResult.setOnSelectImageOptionListener(new SelectImageUtil.OnSelectImageOptionListener() {
            @Override
            public void uploadSingleImage(File file) {
                mListener = new SubscriberOnNextListener<HttpWrapper<String>>() {
                    @Override
                    public void onNext(HttpWrapper<String> httpWrapperResponse) {
                        if (httpWrapperResponse.getCode() == 200) {
                            //登陆成功后得到data中的token
                            SPUtil.refreshToken(httpWrapperResponse.getData());
                            ToastUtil.showShort(MeSettingActivity.this, "Commit Success!");
                        } else {
                            ToastUtil.showLong(MeSettingActivity.this, httpWrapperResponse.getInfo());
                        }
                    }
                };
                RetrofitUtil.getInstance().uploadSingleFile(file,
                        new ProgressSubscriber<HttpWrapper<String>>(mListener, MeSettingActivity.this));
            }
        });
        selectImageUtilResult.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.ib_head_picture, R.id.bt_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_head_picture:
                popupWindowSelect.show();
                break;
            case R.id.bt_commit:
                mListener=new SubscriberOnNextListener<HttpWrapper<String>>() {
                    @Override
                    public void onNext(HttpWrapper<String> httpWrapperResponse) {
                        if (httpWrapperResponse.getCode() == 200) {
                            //登陆成功后得到data中的token
                            SPUtil.refreshToken(httpWrapperResponse.getData());
                            ToastUtil.showShort(MeSettingActivity.this, "Commit Success!");
                        } else {
                            ToastUtil.showLong(MeSettingActivity.this, httpWrapperResponse.getInfo());
                        }
                    }
                };
                RetrofitUtil.getInstance().commitUserInfo(ivUsername.getText().toString(),
                        ivEmail.getText().toString(),
                        ivSchool.getText().toString(),
                        ivPassword.getText().toString(),
                        new ProgressSubscriber<HttpWrapper<String>>(
                                mListener, MeSettingActivity.this));
                break;
        }
    }
}
