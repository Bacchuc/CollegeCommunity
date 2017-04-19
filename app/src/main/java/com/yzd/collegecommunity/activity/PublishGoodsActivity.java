package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.Test;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.AppCenterUtil;
import com.yzd.collegecommunity.util.PopupWindowSelectUtil;
import com.yzd.collegecommunity.util.RetrofitUtil;
import com.yzd.collegecommunity.util.SPUtil;
import com.yzd.collegecommunity.util.SelectImageUtil;
import com.yzd.collegecommunity.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class PublishGoodsActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.ib_commit)
    ImageButton ibCommit;
    @BindView(R.id.ib_photo)
    ImageView ibPhoto;

    PopupWindowSelectUtil popupWindowSelect;
    SelectImageUtil selectImageUtilResult;
    private SubscriberOnNextListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity_goods);
        ButterKnife.bind(this);

        //侧滑效果
        SwipeBackHelper.onCreate(this);
        popupWindowSelect = new PopupWindowSelectUtil(this, PublishGoodsActivity.this, R.layout.publish_activity_goods);
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

        selectImageUtilResult.setOnSelectImageOptionListener(new SelectImageUtil.OnSelectImageOptionListener() {
            @Override
            public void uploadSingleImage(byte[] bitmapByte) {
                mListener = new SubscriberOnNextListener() {
                    @Override
                    public void onNext(Object o) {
                        ToastUtil.showShort(AppCenterUtil.getContextObject(), "Upload Success");
                    }
                };
                RetrofitUtil.getInstance().uploadSingleFile(bitmapByte, SPUtil.getToken(),
                        new ProgressSubscriber<HttpWrapper<Test>>(mListener, AppCenterUtil.getContextObject()));
            }
        });
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
        selectImageUtilResult = new SelectImageUtil(this, ibPhoto);
        selectImageUtilResult.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.ib_commit, R.id.ib_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_commit:
                break;
            case R.id.ib_photo:
                popupWindowSelect.show();
                break;
        }
    }
}
