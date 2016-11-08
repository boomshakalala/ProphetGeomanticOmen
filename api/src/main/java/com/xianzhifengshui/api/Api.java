package com.xianzhifengshui.api;

import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.net.ActionCallbackListener;

import java.util.ArrayList;

/**
 * 作者: 陈冠希
 * 日期: 2016/9/12.
 * 描述: Api接口
 */
public interface Api {
    String USER_LOGIN = "user/login"; //用户登录接口
    String MASTER_LIST = "master/list"; //获取大师列表接口
    String MASTER_DETAIL = "master/detail"; //获取大师详情接口
    String INDEX_GET_DATA_LIST = "index/getDataList";//获取首页数据（城市列表、轮播图列表、大师类型列表）
    String USER_SAVE_USER_INFO = "user/saveUserInfo";//用户注册接口（保存用户信息）
    String MASTER_COLLECTION_CONFIRM = "master/collection/confirm";// 收藏（包括取消）大师接口


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

    void masterEvaluateConfirm(String masterCode,String userCode,String content,ActionCallbackListener<Void> callback);

}
