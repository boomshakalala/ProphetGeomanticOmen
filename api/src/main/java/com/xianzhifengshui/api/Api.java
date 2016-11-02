package com.xianzhifengshui.api;

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

}
