package com.yzd.collegecommunity.adapter;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class chat_room_message {
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SENT=1;

    private String content;
    private int type;

    public chat_room_message(String content, int type) {
        this.content=content;
        this.type=type;

    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }



}
