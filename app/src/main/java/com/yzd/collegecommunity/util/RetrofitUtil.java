package com.yzd.collegecommunity.util;

import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.MainTaskListInfo;
import com.yzd.collegecommunity.retrofit.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

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
    public void register(String username, String password, String email, String code,
                         Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.register(username, email, password, code)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 上传单张图片
     *
     * @param bytes      图片byte字节流
     * @param token
     * @param subscriber
     */
    public void uploadSingleFile(byte[] bytes, String token, Subscriber<HttpWrapper<String>> subscriber) {
        Map<String, RequestBody> bodyMap = new HashMap<>();

        bodyMap.put("file" + "\";filename=\"" + Constants.SINGLE_IMAGE,
                RequestBody.create(MediaType.parse("image/jpg"), bytes));
        mApiService.uploadSingleFile(bodyMap, token)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 提交商品发布的商品信息
     *
     * @param etNumber   商品数目
     * @param etDescribe 商品描述
     * @param etPrice    商品价格
     * @param etTitle    商品标题
     * @param subscriber
     */
    public void commitPublishGoods(String etNumber, String etDescribe, String etPrice, String etTitle,
                                   Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.commitPublishGoods(etNumber, etDescribe, etPrice, etTitle, SPUtil.getToken())
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 提交任务发布的任务信息
     *
     * @param etTaskPrice 任务奖励
     * @param etDescribe  任务描述
     * @param etBeginTime 任务开始时间
     * @param etEndTime   任务结束时间
     * @param etTaskTitle 任务标题
     * @param subscriber
     */
    public void commitPublishTask(String etEndTime, String etBeginTime, String etDescribe, String etTaskPrice, String etTaskTitle,
                                  Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.commitPublishTask(etTaskPrice, etDescribe, etBeginTime, etEndTime, etTaskTitle, SPUtil.getToken())
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 提交用户信息
     *
     * @param ivUsername 用户名
     * @param ivEmail    邮箱
     * @param ivSchool   用户学校
     * @param ivPassword 用户密码
     * @param subscriber
     */
    public void commitUserInfo(String ivUsername, String ivEmail, String ivSchool, String ivPassword,
                               Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.commitUserInfo(ivUsername, ivEmail, ivSchool, ivPassword, SPUtil.getToken())
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取主页任务页面的列表信息
     * @param subscriber
     */
    public void getMainTaskInfo(Subscriber<HttpWrapper<List<MainTaskListInfo>>> subscriber) {
        mApiService.getMainTaskInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }
}
