package com.yzd.collegecommunity.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzd.collegecommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yzd.collegecommunity.R.id.bt_send;
import static com.yzd.collegecommunity.R.id.et_chat;
import static com.yzd.collegecommunity.R.id.rv_chat;

/**
 * Created by Laiyin on 2017/3/11.
 */

public class ChatActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    @BindView(R.id.et_chat)
    EditText etChat;
    @BindView(R.id.bt_send)
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        rvChat.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({R.id.rv_chat, R.id.et_chat, R.id.bt_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case bt_send:

                break;
            case et_chat:

                break;
            case rv_chat:

                break;
            default:
                break;
        }
    }
}
