package com.yzd.collegecommunity.retrofit;

import com.yzd.collegecommunity.modeal.HttpWrapper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 *
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 *
 * 放所有的网络接口
 */

public interface ApiService {

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("textPost")
    Observable<HttpWrapper<String>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 用户通过邮箱请求发送验证码请求
     * @param email 用户邮箱
     * @return
     */
    @FormUrlEncoded
    @POST("textPost")
    Observable<HttpWrapper<String>> sendCode(@Field("email") String email);

    /**
     * 用户注册 先通过验证码验证 再输入4个参数信息判断邮箱与用户名是否已注册，验证码是否正确，条件满足则注册成功，反之注册失败
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱地址
     * @param code 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("textPost")
    Observable<HttpWrapper<String>> register(@Field("username") String username, @Field("password") String password, @Field("email") String email, @Field("code") String code);
}
