package com.yzd.collegecommunity.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.chat_room_adapter;
import com.yzd.collegecommunity.adapter.chat_room_item03;
import com.yzd.collegecommunity.adapter.chat_room_message;

import java.util.ArrayList;
import java.util.List;

public class chat_room extends AppCompatActivity {
    private Button button;
    private EditText inputText;
    private Button send;
    private ListView lis;
    private ImageButton imageButton2;
    private chat_room_adapter adapter;
    private List<chat_room_message> msgList=new ArrayList<chat_room_message>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.chat_room);
        lis=(ListView) findViewById(R.id.listView);

        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setClass(chat_room.this,my_fbrw.class);
//                startActivity(intent);
//                finish();
            }
        });

        chat_room_message msg=new chat_room_message("你好!", chat_room_message.TYPE_RECEIVED);
//        chat_room_message msg1=new chat_room_message("你好,亲,有什么可以帮到你么？", chat_room_message.TYPE_SENT);
//        chat_room_message msg2=new chat_room_message("我想接这个任务", chat_room_message.TYPE_RECEIVED);
        msgList.add(msg);
//        msgList.add(msg1);
//        msgList.add(msg2);

        adapter=new chat_room_adapter(this,R.layout.chat_room_item1,msgList);
        lis.setAdapter(adapter);

        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    chat_room_message msg=new chat_room_message(content, chat_room_message.TYPE_SENT);
                    msgList.add(msg);

                    //当有新消息的时候,刷新ListView中的显示
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    lis.setSelection(msgList.size());
                    //清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chat_room.this,chat_room_item03.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
