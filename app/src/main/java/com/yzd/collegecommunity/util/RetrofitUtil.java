package com.yzd.collegecommunity.util;

import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.Test;
import com.yzd.collegecommunity.retrofit.ApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo.util
 * @date 2016/12/12  10:38
 */

public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

//    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    /**
     * 用户登陆
     *
     * @param username   用户名
     * @param password   密码
     * @param subscriber
     */
    public void login(String username, String password, Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.login(username, password)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 发送验证码
     *
     * @param email      用户邮箱
     * @param subscriber
     */
    public void sendCode(String email, Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.sendCode(email)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 用户注册
     *
     * @param username   用户名
     * @param password   密码
     * @param email      邮箱
     * @param code       验证码
     * @param subscriber
     */
    public void register(String username, String password, String email, String code, Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.register(username, email, password, code)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 上传单张图片
     *
     * @param bytes      图片byte字节流
     * @param token
     * @param subscriber
     */
    public void uploadSingleFile(byte[] bytes, String token, Subscriber<HttpWrapper<Test>> subscriber) {
        Map<String, RequestBody> bodyMap = new HashMap<>();

        bodyMap.put("file" + "\";filename=\"" + Constants.SINGLE_IMAGE,
                RequestBody.create(MediaType.parse("image/jpg"), bytes));
        mApiService.uploadSingleFile(bodyMap, token)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }
}
