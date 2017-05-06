package com.yzd.collegecommunity.retrofit;

import com.yzd.collegecommunity.modeal.GoodsWrapper;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.RankingWrapper;
import com.yzd.collegecommunity.modeal.TaskWrapper;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 * <p>
 * 放所有的网络接口
 */

public interface ApiService {

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<HttpWrapper<String>> login(@Field("userName") String username, @Field("password") String password);

    /**
     * 用户通过邮箱请求发送验证码请求
     *
     * @param email 用户邮箱
     * @return
     */
    @FormUrlEncoded
    @POST("validate")
    Observable<HttpWrapper<String>> sendCode(@Field("mail") String email);

    /**
     * 用户注册 先通过验证码验证 再输入4个参数信息判断邮箱与用户名是否已注册，验证码是否正确，条件满足则注册成功，反之注册失败
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱地址
     * @param code     验证码
     * @return
     */
    @FormUrlEncoded
    @POST("register")
    Observable<HttpWrapper<String>> register(@Field("userName") String username,
                                             @Field("password") String password,
                                             @Field("mail") String email,
                                             @Field("validate") String code);

    /**
     * 发布任务上传单张图片
     * @param file
     * @return
     */
    @Multipart
    @POST("uploadTaskPic")
    Observable<HttpWrapper<String>> uploadTaskSingleFile(@PartMap Map<String,RequestBody> file);

    /**
     * 发布商品上传单张图片
     * @param file
     * @return
     */
    @Multipart
    @POST("uploadGoodsPic")
    Observable<HttpWrapper<String>> uploadGoodsSingleFile(@PartMap Map<String,RequestBody> file);

    /**
     * 上传用户单张图片
     * @param file
     * @return
     */
    @Multipart
    @POST("private/upload")
    Observable<HttpWrapper<String>> uploadUserSingleFile(@PartMap Map<String,RequestBody> file);

    /**
     * 提交商品发布的商品信息
     *
     * @param etNumber   商品数目
     * @param etDescribe 商品描述
     * @param etPrice    商品价格
     * @param etTitle    商品标题
     * @return 上传成功则code为200
     */
    @FormUrlEncoded
    @POST("private/toPublishGoods")
    Observable<HttpWrapper<String>> commitPublishGoods(@Field("goodsNum") String etNumber,
                                                       @Field("description") String etDescribe,
                                                       @Field("price") String etPrice,
                                                       @Field("title") String etTitle);

    /**
     * 提交任务发布的任务信息
     *
     * @param etTaskPrice 任务奖励
     * @param etDescribe  任务描述
     * @param etEndTime   任务结束时间
     * @return 上传成功则code为200
     */
    @FormUrlEncoded
    @POST("private/toPublishTask")
    Observable<HttpWrapper<String>> commitPublishTask(@Field("pay") Double etTaskPrice,
                                                      @Field("description") String etDescribe,
                                                      @Field("endTime") String etEndTime);

    /**
     * 提交用户信息
     *
     * @param ivUsername 用户名
     * @param ivEmail    邮箱
     * @param ivSchool   用户学校
     * @param ivPassword 用户密码
     * @return
     */
    @FormUrlEncoded
    @POST("private/modify")
    Observable<HttpWrapper<String>> commitUserInfo(@Field("userName") String ivUsername,
                                                   @Field("mail") String ivEmail,
                                                   @Field("school") String ivSchool,
                                                   @Field("password") String ivPassword);

    /**
     * 获取主页任务页面的列表信息
     * @return
     */
    @POST("allTasks")
    Observable<HttpWrapper<TaskWrapper>> getMainTaskInfo();

    /**
     * 获取商品任务页面的列表信息
     * @return
     */
    @POST("allGoods")
    Observable<HttpWrapper<GoodsWrapper>> getMainGoodsInfo();

    /**
     * 获取主页排行榜页面的列表信息
     * @return
     */
    @POST("allRanking")
    Observable<HttpWrapper<RankingWrapper>> getMainRankingInfo();

    /**
     * 获取个人商品已经发布的页面的列表信息
     * @return
     */
    @POST("private/sellGoods/selling")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsMyPublicSellInfo();

    /**
     * 获取个人商品正在销售的页面的列表信息
     * @return
     */
    @POST("private/sellGoods/translate")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsMyPublicSellingInfo();

    /**
     * 获取个人商品已经销售完的页面的列表信息
     * @return
     */
    @POST("private/sellGoods/confirm")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsMyPublicSoldInfo();

    /**
     * 获取个人商品已经完成购买的页面的列表信息
     * @return
     */
    @POST("private/buyGoods/selling")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsOtherPublicFinishInfo();

    /**
     * 获取个人商品已经购买的页面的列表信息
     * @return
     */
    @POST("private/buyGoods/selling")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsMyPublicPurchaseInfo();

    /**
     * 获取个人商品正在路上的页面的列表信息
     * @return
     */
    @POST("private/buyGoods/selling")
    Observable<HttpWrapper<GoodsWrapper>> getMeGoodsMyPublicUnderWayInfo();

    /**
     * 获取个人任务已经完成的页面的列表信息
     * @return
     */
    @POST("private/publishedTask/finished")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskMyPublicFinishInfo();

    /**
     * 获取个人任务正在发布的页面的列表信息
     * @return
     */
    @POST("private/publishedTask/unaccept")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskMyPublicReleasedInfo();

    /**
     * 获取个人任务正在完成的页面的列表信息
     * @return
     */
    @POST("private/publishedTask/doing")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskMyPublicUnderWayInfo();

    /**
     * 获取个人接受过的任务的页面的列表信息
     * @return
     */
    @POST("private/publishedTask/unaccept")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskOtherPublicAcceptInfo();

    /**
     * 获取个人正在完成的任务页面的列表信息
     * @return
     */
    @POST("private/publishedTask/doing")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskOtherPublicUnderWayInfo();

    /**
     * 获取个人已经完成的任务页面的列表信息
     * @return
     */
    @POST("private/acceptedTask/finished")
    Observable<HttpWrapper<TaskWrapper>> getMeTaskOtherPublicFinishInfo();













}
