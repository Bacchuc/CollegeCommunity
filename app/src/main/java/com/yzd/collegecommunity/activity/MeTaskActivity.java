package com.yzd.collegecommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.yzd.collegecommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/15.
 */

public class MeTaskActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.bt_my_publish)
    ImageButton btMyPublish;
    @BindView(R.id.bt_publish)
    ImageButton btPublish;
    @BindView(R.id.bt_others_publish)
    ImageButton btOthersPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_task);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_my_publish, R.id.bt_publish, R.id.bt_others_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_my_publish:
                break;
            case R.id.bt_publish:
                Intent intent = new Intent(MeTaskActivity.this, PublishTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_others_publish:
                break;
        }
    }
}
