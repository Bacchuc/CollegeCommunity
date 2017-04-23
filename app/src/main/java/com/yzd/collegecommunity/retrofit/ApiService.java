package com.yzd.collegecommunity.retrofit;

import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.TaskWrapper;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

//    @POST("uploadTaskPic")
//    @Multipart
//    Observable<HttpWrapper<String>> uploadSingleFile(@PartMap Map<String, RequestBody> picStream);

    @POST("uploadTaskPic")
    @Multipart
    Observable<HttpWrapper<String>> uploadSingleFile(@Part("picStream") RequestBody picStream);

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
    @POST("goods/private/toPublish")
    Observable<HttpWrapper<String>> commitPublishGoods(@Field("etNumber") String etNumber,
                                                       @Field("etDescribe") String etDescribe,
                                                       @Field("etPrice") String etPrice,
                                                       @Field("etTitle") String etTitle);

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
    @POST("textPost")
    Observable<HttpWrapper<String>> commitUserInfo(@Field("ivUsername") String ivUsername,
                                                   @Field("ivEmail") String ivEmail,
                                                   @Field("ivSchool") String ivSchool,
                                                   @Field("ivPassword") String ivPassword);

    /**
     * 获取主页任务页面的列表信息
     * @return
     */
    @POST("all")
    Observable<HttpWrapper<TaskWrapper>> getMainTaskInfo();

    @FormUrlEncoded
    @POST("textPost")
    Observable<HttpWrapper<String>> getMainGoodsInfo();

    @FormUrlEncoded
    @POST("textPost")
    Observable<HttpWrapper<String>> getMainRankingInfo();
}
