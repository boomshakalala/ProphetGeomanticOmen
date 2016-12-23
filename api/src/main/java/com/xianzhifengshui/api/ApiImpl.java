package com.xianzhifengshui.api;

import android.app.SearchManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianzhifengshui.api.des.DESUtils;
import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.model.Verify;
import com.xianzhifengshui.api.model.WXApiResponse;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.api.net.HttpEngine;
import com.xianzhifengshui.api.utils.JsonFormatTool;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 作者: 陈冠希
 * 日期: 2016/9/12.
 * 描述: Api实现类
 */
public class ApiImpl implements Api {
    private final String TAG = getClass().getSimpleName();
    public final int PARAM_NULL = -2;
    private LinkedHashMap<String,Object> paramsMap;
    private static ApiImpl instance;

    public static ApiImpl getInstance(){
        if (instance != null) {
            return instance;
        }else {
            instance = new ApiImpl();
            return instance;
        }
    }

    private ApiImpl(){
        paramsMap = new LinkedHashMap<>();
    }

    /**
     * 参数加密
     * @param map 要传的参数
     * @return 加密后的字符串
     */
    private String map2Ciphertext(LinkedHashMap<String,Object> map){
        Gson gson = new Gson();
        LinkedHashMap<String,Object> resultMap = new LinkedHashMap();
        //城市代码:北京（110000）;上海（200000）；其他待定
        resultMap.put("cityCode", "110000");
        //设备类型:安卓传android；苹果传ios；pc端传pc，微信端传weixin，M站传mobile
        resultMap.put("deviceType", "android");
        resultMap.putAll(map);
        String json = gson.toJson(resultMap);
        Log.d(TAG, "map2Ciphertext json = "+ JsonFormatTool.formatJson(json));
        return json;

    }

    @Override
    public void wxGetAccessToken(String appId, String secret, String code,ActionCallbackListener<WXApiResponse> callback) {
        LinkedHashMap<String,String> params = new LinkedHashMap<>();
        params.put("appid",appId);
        params.put("secret",secret);
        params.put("code",code);
        params.put("grant_type","authorization_code");
        HttpEngine.getInstance().wxGet(WX_ACCESS_TOKEN, params, callback);
    }

    @Override
    public void wxGetUserInfo(String accessToken, String openId,ActionCallbackListener<WXApiResponse> callback) {
        LinkedHashMap<String,String> params = new LinkedHashMap<>();
        params.put("access_token",accessToken);
        params.put("openid",openId);
        HttpEngine.getInstance().wxGet(WX_USER_INFO, params, callback);
    }

    @Override
    public void userLogin(String userName, String passWord,ActionCallbackListener<User> callback) {
        paramsMap.clear();
        paramsMap.put("username", userName);
        paramsMap.put("password",passWord);
        HttpEngine.getInstance().get(USER_LOGIN, map2Ciphertext(paramsMap) ,User.class,callback);
    }

    @Override
    public void masterList(int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Master>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Master>>>(){}.getType();
        HttpEngine.getInstance().get(MASTER_LIST,map2Ciphertext(paramsMap),type,callback);

    }

    @Override
    public void masterDetail(String masterCode, String userCode, ActionCallbackListener<MasterDetailModel> callback) {
        paramsMap.clear();
        paramsMap.put("masterCode", masterCode);
        paramsMap.put("userCode", userCode);
        Type type = MasterDetailModel.class;
        HttpEngine.getInstance().get(MASTER_DETAIL,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void indexGetDataList(ActionCallbackListener<HomeItemModle> callback) {
        paramsMap.clear();
        Type type = HomeItemModle.class;
        HttpEngine.getInstance().get(INDEX_GET_DATA_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void userSaveUserInfo(String mobilePhone, String password, String nickname, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type type = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("password",password);
        paramsMap.put("nickname",nickname);
        HttpEngine.getInstance().get(USER_SAVE_USER_INFO,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void masterCollectionConfirm(String masterCode, String userCode, String type, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("masterCode",masterCode);
        paramsMap.put("userCode",userCode);
        paramsMap.put("type",type);
        HttpEngine.getInstance().get(MASTER_COLLECTION_CONFIRM,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void masterEvaluateConfirm(String masterCode, String userCode, String content, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("masterCode",masterCode);
        paramsMap.put("userCode",userCode);
        paramsMap.put("content",content);
        HttpEngine.getInstance().get(MASTER_COLLECTION_CONFIRM,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void indexGetCityList(ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        HttpEngine.getInstance().get(INDEX_GET_CITY_LIST,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userSendSms(String mobilePhone, String type, ActionCallbackListener<Verify> callback) {
        paramsMap.clear();
        Type typeOfT = Verify.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("type",type);
        HttpEngine.getInstance().get(USER_SEND_SMS,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userResetPassword(int mobilePhone, int password, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("password",password);
        HttpEngine.getInstance().get(USER_RESET_PASSWORD,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userUpdatePassword(String mobilePhone, String oldPassword, String newPassword, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("oldPassword",oldPassword);
        paramsMap.put("newPassword",newPassword);
        HttpEngine.getInstance().get(USER_UPDATE_PASSWORD, map2Ciphertext(paramsMap), typeOfT, callback);
    }

    @Override
    public void feedBack(String uid, String content, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type tpyeOfT = Void.class;
        paramsMap.put("uid",uid);
        paramsMap.put("content",content);
    }

    @Override
    public void lecturesList(int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Lecture>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Lecture>>>(){}.getType();
        HttpEngine.getInstance().get(LECTURES_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void lecturesDetail(String lectureCode, String userCode, ActionCallbackListener<Lecture> callback) {
        paramsMap.clear();
        paramsMap.put("lecturesCode", lectureCode);
        paramsMap.put("userCode", userCode);
        Type type = Lecture.class;
        HttpEngine.getInstance().get(LECTURES_DETAIL,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void topicList(int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Topic>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Topic>>>(){}.getType();
        HttpEngine.getInstance().get(TOPIC_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void topicTypeList(ActionCallbackListener<BaseListModel<ArrayList<TopicType>>> callback) {
        paramsMap.clear();
        Type type = new TypeToken<BaseListModel<ArrayList<TopicType>>>(){}.getType();
        HttpEngine.getInstance().get(TOPIC_TYPE_LIST,map2Ciphertext(paramsMap),type,callback);
    }


}
