package com.yzd.collegecommunity.modeal;

import java.io.Serializable;

/**
 * Created by Laiyin on 2017/3/9.
 *
 * 核心包装类 封装数据集合
 *
 */

public class HttpWrapper<T> implements Serializable {

    /**
     * 响应码  服务器返回200为成功，201为失败
     */
    public int code;


    /**
     * 返回的数据类型 不确定
     */
    public T data;


    /**
     * 对应的响应信息
     */
    public String info;



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
