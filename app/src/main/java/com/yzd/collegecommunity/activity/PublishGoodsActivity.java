package com.yzd.collegecommunity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.util.PopupWindowSelectUtil;

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
    ImageButton ibPhoto;

    PopupWindowSelectUtil popupWindowSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity_goods);
        ButterKnife.bind(this);

        //侧滑效果
        SwipeBackHelper.onCreate(this);
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

    @OnClick({R.id.ib_commit, R.id.ib_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_commit:
                break;
            case R.id.ib_photo:
                popupWindowSelect=new PopupWindowSelectUtil(this, PublishGoodsActivity.this, R.layout.publish_activity_goods);
                popupWindowSelect.show();
                break;
        }
    }
}
