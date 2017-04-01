package com.yzd.collegecommunity.retrofit;

import com.yzd.collegecommunity.modeal.HttpWrapper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Laiyin on 2017/3/28.
 *
 * 实现用户登陆的网络请求接口
 */

public interface LoginImpl {
    @FormUrlEncoded
    @POST("login")
    Observable<HttpWrapper<String>> login(@Field("username") String email, @Field("password") String password);
}
