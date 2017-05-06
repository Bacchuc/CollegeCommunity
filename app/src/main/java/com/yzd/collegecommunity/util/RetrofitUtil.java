package com.yzd.collegecommunity.util;

import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.GoodsWrapper;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.RankingWrapper;
import com.yzd.collegecommunity.modeal.TaskWrapper;
import com.yzd.collegecommunity.retrofit.ApiService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
//        builder.addInterceptor(new LogInterceptor());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder1 = request.newBuilder();
                Request build = builder1.addHeader(Constants.TOKEN, SPUtil.getToken()).build();
                return chain.proceed(build);
            }
        });
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
        mApiService.register(username, password, email, code)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 上传任务的单张图片
     *
     * @param file      图片byte字节流
     * @param subscriber
     */
    public void uploadTaskSingleFile(File file, Subscriber<HttpWrapper<String>> subscriber) {

        Map<String,RequestBody> bodyMap = new HashMap<>();

        bodyMap.put("file"+"\"; filename=\""+file.getName(),
                RequestBody.create(MediaType.parse("image/png"),file));

        mApiService.uploadTaskSingleFile(bodyMap)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 上传商品的单张图片
     *
     * @param file      图片byte字节流
     * @param subscriber
     */
    public void uploadGoodsSingleFile(File file, Subscriber<HttpWrapper<String>> subscriber) {

        Map<String,RequestBody> bodyMap = new HashMap<>();

        bodyMap.put("file"+"\"; filename=\""+file.getName(),
                RequestBody.create(MediaType.parse("image/png"),file));

        mApiService.uploadGoodsSingleFile(bodyMap)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 上传用户头像的单张图片
     *
     * @param file      图片byte字节流
     * @param subscriber
     */
    public void uploadUserSingleFile(File file, Subscriber<HttpWrapper<String>> subscriber) {

        Map<String,RequestBody> bodyMap = new HashMap<>();

        bodyMap.put("file"+"\"; filename=\""+file.getName(),
                RequestBody.create(MediaType.parse("image/png"),file));

        mApiService.uploadUserSingleFile(bodyMap)
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
        mApiService.commitPublishGoods(etNumber, etDescribe, etPrice, etTitle)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 提交任务发布的任务信息
     *
     * @param etTaskPrice 任务奖励
     * @param etDescribe  任务描述
     * @param etEndTime   任务结束时间
     * @param subscriber
     */
    public void commitPublishTask(String etEndTime, String etDescribe, Double etTaskPrice,
                                  Subscriber<HttpWrapper<String>> subscriber) {
        mApiService.commitPublishTask(etTaskPrice, etDescribe, etEndTime)
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
        mApiService.commitUserInfo(ivUsername, ivEmail, ivSchool, ivPassword)
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取主页任务页面的列表信息
     * @param subscriber
     */
    public void getMainTaskInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMainTaskInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取主页商品页面的列表信息
     * @param subscriber
     */
    public void getMainGoodsInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMainGoodsInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取主页排行榜页面的列表信息
     * @param subscriber
     */
    public void getMainRankingInfo(Subscriber<HttpWrapper<RankingWrapper>> subscriber) {
        mApiService.getMainRankingInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品已经发布的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsMyPublicSellInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsMyPublicSellInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品正在销售的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsMyPublicSellingInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsMyPublicSellingInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品已经销售完的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsMyPublicSoldInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsMyPublicSoldInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品已经完成购买的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsOtherPublicFinishInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsOtherPublicFinishInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品已经购买的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsMyPublicPurchaseInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsMyPublicPurchaseInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人商品正在路上的页面的列表信息
     * @param subscriber
     */
    public void getMeGoodsMyPublicUnderWayInfo(Subscriber<HttpWrapper<GoodsWrapper>> subscriber) {
        mApiService.getMeGoodsMyPublicUnderWayInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人任务已经完成的页面的列表信息
     * @param subscriber
     */
    public void getMeTaskMyPublicFinishInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskMyPublicFinishInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人任务正在发布的页面的列表信息
     * @param subscriber
     */
    public void getMeTaskMyPublicReleasedInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskMyPublicReleasedInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人任务正在完成的页面的列表信息
     * @param subscriber
     */
    public void getMeTaskMyPublicUnderWayInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskMyPublicUnderWayInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人接受过的任务的页面的列表信息
     * @param subscriber
     */
    public void getMeTaskOtherPublicAcceptInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskOtherPublicAcceptInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人正在完成的任务页面的列表信息
     * @param subscriber
     */
    public void getMeTaskOtherPublicUnderWayInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskOtherPublicUnderWayInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }

    /**
     * 获取个人已经完成的任务页面的列表信息
     * @param subscriber
     */
    public void getMeTaskOtherPublicFinishInfo(Subscriber<HttpWrapper<TaskWrapper>> subscriber) {
        mApiService.getMeTaskOtherPublicFinishInfo()
                .compose(RxSchedulers.switchThread())
                .subscribe(subscriber);
    }



























}
