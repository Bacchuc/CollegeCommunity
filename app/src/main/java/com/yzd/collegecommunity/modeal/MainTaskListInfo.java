package com.yzd.collegecommunity.modeal;

import android.graphics.Bitmap;

/**
 * Created by Laiyin on 2017/3/6.
 */

public class MainTaskListInfo {

    private String username;   //用户名

    private String introduce;  //描述

    private Bitmap pic;     //描述图片

    private Bitmap picHead;  //头像图片

    public Bitmap getPicHead() {
        return picHead;
    }

    public void setPicHead(Bitmap picHead) {
        this.picHead = picHead;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
