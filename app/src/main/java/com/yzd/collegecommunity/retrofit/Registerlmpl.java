package com.yzd.collegecommunity.retrofit;

import com.yzd.collegecommunity.modeal.HttpWrapper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Laiyin on 2017/3/29.
 *
 * 用户注册网络请求接口
 */

public interface Registerlmpl {

    /**
     * 发送验证码
     * @param email 用户邮箱
     * @return
     */
    @FormUrlEncoded
    @POST("sendCode")
    Observable<HttpWrapper<String>>sendCode(@Field("email") String email);

    @FormUrlEncoded
    @POST("register")
    Observable<HttpWrapper<String>>register(@Field("username") String username,@Field("email") String email,@Field("password") String password,@Field("verCode") String verCode);

}
