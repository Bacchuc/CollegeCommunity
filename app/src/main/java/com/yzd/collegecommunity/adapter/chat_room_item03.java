package com.yzd.collegecommunity.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.chat_room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class chat_room_item03 extends AppCompatActivity {
    private ListView lis3;
    private ListView lis2;
    private ImageButton imb2;
    private chat_room_modeladapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room_item03);
        lis2= (ListView) findViewById(R.id.listView2);
        lis3= (ListView) findViewById(R.id.listView3);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("text","聊天置顶");
        map1.put("button"," ");
        list.add(map1);

        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("text","消息免打扰");
        map2.put("button"," ");
        list.add(map2);

        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("text","屏蔽该用户");
        map3.put("button"," ");
        list.add(map3);
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.chat_room_item01,new String[]{"text","button"}
                ,new int[]{R.id.textView2,R.id.toggleButton});
        lis3.setAdapter(adapter);

        imb2= (ImageButton) findViewById(R.id.imageButton2);
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chat_room_item03.this,chat_room.class);
                startActivity(intent);
                finish();
            }
        });
        adapter1=new chat_room_modeladapter(this,getData());
        lis2.setAdapter(adapter1);
    }
    private List<chat_room_model> getData(){
        List<chat_room_model> list=new ArrayList<chat_room_model>();
        chat_room_model model=new chat_room_model();
        model.setAvatar(R.drawable.chat_room_headphoto1);
        model.setTv3("小兽星日单铺");
        model.setTv4("社区优店:小兽星单铺");
        list.add(model);
        return list;
    }
}
