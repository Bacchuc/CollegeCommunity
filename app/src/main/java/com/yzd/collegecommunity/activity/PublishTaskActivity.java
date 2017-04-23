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
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.AppCenterUtil;
import com.yzd.collegecommunity.util.PopupWindowSelectUtil;
import com.yzd.collegecommunity.util.RetrofitUtil;
import com.yzd.collegecommunity.util.SelectImageUtil;
import com.yzd.collegecommunity.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class PublishTaskActivity extends BaseActivity {

    @BindView(R.id.ib_commit)
    ImageButton ibCommit;
    @BindView(R.id.et_task_title)
    EditText etTaskTitle;
    @BindView(R.id.et_task_price)
    EditText etTaskPrice;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.ib_task_photo)
    ImageView ibTaskPhoto;
    @BindView(R.id.et_begin_time)
    EditText etBeginTime;
    @BindView(R.id.et_end_time)
    EditText etEndTime;

    PopupWindowSelectUtil popupWindowSelect;
    SelectImageUtil selectImageUtilResult;
    private SubscriberOnNextListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity_task);
        ButterKnife.bind(this);
        initView();
        //侧滑效果
        SwipeBackHelper.onCreate(this);
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

    private void initView() {
        popupWindowSelect = new PopupWindowSelectUtil(this, PublishTaskActivity.this, R.layout.publish_activity_task);
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

        selectImageUtilResult = new SelectImageUtil(this, ibTaskPhoto);

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
        selectImageUtilResult = new SelectImageUtil(this, ibTaskPhoto);
        selectImageUtilResult.setOnSelectImageOptionListener(new SelectImageUtil.OnSelectImageOptionListener() {
            @Override
            public void uploadSingleImage(byte[] bitmapByte) {
                mListener = new SubscriberOnNextListener() {
                    @Override
                    public void onNext(Object o) {
                        ToastUtil.showShort(PublishTaskActivity.this, "Upload Success");
                    }
                };
                RetrofitUtil.getInstance().uploadSingleFile(bitmapByte,
                        new ProgressSubscriber<HttpWrapper<String>>(mListener, PublishTaskActivity.this));
            }
        });
        selectImageUtilResult.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.ib_commit, R.id.ib_task_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_commit:
                mListener = new SubscriberOnNextListener() {
                    @Override
                    public void onNext(Object o) {
                        ToastUtil.showShort(AppCenterUtil.getContextObject(), "Commit Success!");
                    }
                };

                RetrofitUtil.getInstance().commitPublishTask(etEndTime.getText().toString(),
                        etDescribe.getText().toString(),
                        Double.valueOf(etTaskPrice.getText().toString()),
                        new ProgressSubscriber<HttpWrapper<String>>(
                                mListener, this));
                break;
            case R.id.ib_task_photo:
                popupWindowSelect.show();
                break;
        }
    }
}
