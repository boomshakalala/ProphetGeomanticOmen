package com.xianzhifengshui.api;

import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.model.WXApiResponse;
import com.xianzhifengshui.api.net.ActionCallbackListener;

import java.util.ArrayList;

/**
 * 作者: 陈冠希
 * 日期: 2016/9/12.
 * 描述: Api接口
 */
public interface Api {
    /*======= 微信接口 =======*/
    String WX_ACCESS_TOKEN = "access_token";//通过code获取access_token
    String WX_USER_INFO = "userinfo";//获取用户个人信息（UnionID机制）
    /*=======================*/

    String USER_LOGIN = "user/login"; //用户登录接口
    String MASTER_LIST = "master/list"; //获取大师列表接口
    String MASTER_DETAIL = "master/detail"; //获取大师详情接口
    String INDEX_GET_DATA_LIST = "index/getDataList";//获取首页数据（城市列表、轮播图列表、大师类型列表）
    String INDEX_GET_CITY_LIST = "index/getCityList";//获取城市列表接口
    String USER_SAVE_USER_INFO = "user/saveUserInfo";//用户注册接口（保存用户信息）
    String MASTER_COLLECTION_CONFIRM = "master/collection/confirm";// 收藏（包括取消）大师接口
    String MASTER_EVALUATE_CONFIRM = "master/collection/confirm";// 评价大师接口
    String USER_SEND_SMS = "user/sendSMS";// 发送手机验证码接口
    String USER_RESET_PASSWORD = "user/resetPassword";// 找回（重置）密码接口
    String USER_UPDATE_PASSWORD = "user/updatePassword";// 修改密码接口
    String FEEDBACK_FEEDBACK = "feedback/feedback";// 用户反馈

    /**
     * 通过code获取access_token的接口。
     * @param appId 应用唯一标识，在微信开放平台提交应用审核通过后获得
     * @param secret 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     * @param code 填写第一步获取的code参数
     */
    void getAccessToken(String appId,String secret,String code,ActionCallbackListener<WXApiResponse> callback);

    /**
     * 此接口用于获取用户个人信息。
     * @param accessToken 调用凭证
     * @param openId 普通用户的标识，对当前开发者帐号唯一
     */
    void getUserInfo(String accessToken,String openId,ActionCallbackListener<WXApiResponse> callback);


    /**
     * 调用本接口验证用户登录操作
     * @param userName 用户名
     * @param passWord 登录密码
     * @param callback 回调
     */
    void userLogin(String userName,String passWord,ActionCallbackListener<User> callback);

    /**
     * 调用本接口获取大师列表数据
     * @param pageNum 当前第几页
     * @param pageSize 每页最多显示多少条
     * @param callback 回调
     */
    void masterList(int pageNum,int pageSize,ActionCallbackListener<BaseListModel<ArrayList<Master>>> callback);

    /**
     * 调用本接口获取大师详情
     * @param masterCode 大师编号
     * @param userCode 用户编号
     * @param callback 回调
     */
    void masterDetail(String masterCode,String userCode,ActionCallbackListener<MasterDetailModel> callback);


    /**
     * 调用本接口获取大师列表数据
     * @param callback 回调
     */
    void indexGetDataList(ActionCallbackListener<HomeItemModle> callback);

    /**
     * 调用本接口保存注册用户信息
     * @param mobilePhone 注册手机号码
     * @param password 登录密码
     * @param nickname 用户昵称
     * @param callback 回调
     */
    void userSaveUserInfo(String mobilePhone,String password,String nickname,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口收藏（或取消）大师
     * @param masterCode 大师编号
     * @param userCode 用户编号
     * @param type 类型 1表示收藏；2表示取消收藏
     * @param callback 回调
     */
    void masterCollectionConfirm(String masterCode,String userCode,String type,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口保存用户评价大师信息
     * @param masterCode 大师编号
     * @param userCode 用户编号
     * @param content 评价内容
     * @param callback 回调
     */
    void masterEvaluateConfirm(String masterCode,String userCode,String content,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口获取城市列表数据
     * @param callback 回调
     */
    void indexGetCityList(ActionCallbackListener<Void> callback);

    /**
     * 调用本接口可以发送手机短信验证码
     * @param mobilePhone 手机号码
     * @param type 类型 1表示注册验证码；2表示重置密码验证码；3表示登录操作验证码
     * @param callback 回调
     */
    void userSendSms(String mobilePhone,String type,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口获取大师列表数据
     * @param mobilePhone 手机号码
     * @param password 新密码
     * @param callback 回调
     */
    void userResetPassword(String mobilePhone,String password,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口修改用户登录密码
     * @param mobilePhone 手机号码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param callback 回调
     */
    void userUpdatePassword(String mobilePhone,String oldPassword,String newPassword,ActionCallbackListener<Void> callback);

    /**
     * 调用本接口提交反馈
     * @param uid 用户ID
     * @param content 反馈内容
     * @param callback 回调
     */
    void feedBack(String uid,String content,ActionCallbackListener<Void> callback);
}
