package com.yzd.collegecommunity.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzd.collegecommunity.R;

/**
 * Created by Laiyin on 2017/3/11.
 */

public class ChatActivity extends BaseActivity implements View.OnClickListener{

    private Button bt_send;
    private EditText et_chat;
    private RecyclerView rv_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.chat_activity);

        initView();

        initListener();
    }

    private void initView() {
        bt_send= (Button) findViewById(R.id.bt_send);
        et_chat= (EditText) findViewById(R.id.et_chat);
        rv_chat= (RecyclerView) findViewById(R.id.rv_chat);

        rv_chat.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initListener() {
        bt_send.setOnClickListener(this);
        et_chat.setOnClickListener(this);
        rv_chat.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_send:

                break;
            case R.id.et_chat:

                break;
            case R.id.rv_chat:

                break;
            default:
                break;
        }
    }
}
