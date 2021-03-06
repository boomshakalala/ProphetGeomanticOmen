package com.xianzhifengshui.api;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.api.model.Bill;
import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.model.PayOrder;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.model.Verify;
import com.xianzhifengshui.api.model.WXApiResponse;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.api.net.HttpEngine;
import com.xianzhifengshui.api.utils.KLog;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        KLog.json(TAG,json);
        return json;

    }

    @Override
    public void payOrderList(String userCode, int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Bill>>> callback) {
        paramsMap.clear();
        paramsMap.put("usercode",userCode);
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Bill>>>(){}.getType();
        HttpEngine.getInstance().post(PAY_ORDER_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void topicIssueConfirm(String userCode, String tile, String content, String typeCode, List<String> picList, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("userCode", userCode);
        paramsMap.put("tile",tile);
        paramsMap.put("content",content);
        paramsMap.put("typeCode",typeCode);
        paramsMap.put("picList",picList);
        HttpEngine.getInstance().post(TOPIC_ISSUE_CONFIRM, map2Ciphertext(paramsMap) ,Void.class,callback);
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
        paramsMap.put("mobilePhone", userName);
        paramsMap.put("password",passWord);
        HttpEngine.getInstance().post(USER_LOGIN, map2Ciphertext(paramsMap) ,User.class,callback);
    }

    @Override
    public void masterList(int pageNum, int pageSize, int searchType, String userCode, String keyword, ActionCallbackListener<BaseListModel<ArrayList<Master>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("searchType",searchType);
        paramsMap.put("userCode",userCode);
        paramsMap.put("keyword",keyword);
        Type type = new TypeToken<BaseListModel<ArrayList<Master>>>(){}.getType();
        HttpEngine.getInstance().post(MASTER_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void masterDetail(String masterCode, String userCode, ActionCallbackListener<MasterDetailModel> callback) {
        paramsMap.clear();
        paramsMap.put("masterCode", masterCode);
        paramsMap.put("userCode", userCode);
        Type type = MasterDetailModel.class;
        HttpEngine.getInstance().post(MASTER_DETAIL,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void indexGetDataList(ActionCallbackListener<HomeItemModle> callback) {
        paramsMap.clear();
        Type type = HomeItemModle.class;
        HttpEngine.getInstance().post(INDEX_GET_DATA_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void userSaveUserInfo(String mobilePhone, String password, String nickname, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type type = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("password",password);
        paramsMap.put("nickname",nickname);
        HttpEngine.getInstance().post(USER_SAVE_USER_INFO,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void masterEvaluateConfirm(String masterCode, String userCode, String content, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("masterCode",masterCode);
        paramsMap.put("userCode",userCode);
        paramsMap.put("content",content);
        HttpEngine.getInstance().post(MASTER_COLLECTION_CONFIRM,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void indexGetCityList(ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        HttpEngine.getInstance().post(INDEX_GET_CITY_LIST,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userSendSms(String mobilePhone, String type, ActionCallbackListener<Verify> callback) {
        paramsMap.clear();
        Type typeOfT = Verify.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("type",type);
        HttpEngine.getInstance().post(USER_SEND_SMS,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userResetPassword(String mobilePhone, String password, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("password",password);
        HttpEngine.getInstance().post(USER_RESET_PASSWORD,map2Ciphertext(paramsMap),typeOfT,callback);
    }

    @Override
    public void userUpdatePassword(String mobilePhone, String oldPassword, String newPassword, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type typeOfT = Void.class;
        paramsMap.put("mobilePhone",mobilePhone);
        paramsMap.put("oldPassword",oldPassword);
        paramsMap.put("newPassword",newPassword);
        HttpEngine.getInstance().post(USER_UPDATE_PASSWORD, map2Ciphertext(paramsMap), typeOfT, callback);
    }

    @Override
    public void feedBack(String userCode, String email, String content, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        Type tpyeOfT = Void.class;
        paramsMap.put("usercode",userCode);
        paramsMap.put("content",content);
        paramsMap.put("email",email);
        Type type = Void.class;
        HttpEngine.getInstance().post(MINE_FEEDBACK,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void lecturesList(int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Lecture>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Lecture>>>(){}.getType();
        HttpEngine.getInstance().post(LECTURES_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void lecturesDetail(String lectureCode, String userCode, ActionCallbackListener<Lecture> callback) {
        paramsMap.clear();
        paramsMap.put("lecturesCode", lectureCode);
        paramsMap.put("userCode", userCode);
        Type type = Lecture.class;
        HttpEngine.getInstance().post(LECTURES_DETAIL,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void topicList(int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Topic>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Topic>>>(){}.getType();
        HttpEngine.getInstance().post(TOPIC_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void topicTypeList(ActionCallbackListener<BaseListModel<ArrayList<TopicType>>> callback) {
        paramsMap.clear();
        Type type = new TypeToken<BaseListModel<ArrayList<TopicType>>>(){}.getType();
        HttpEngine.getInstance().post(TOPIC_TYPE_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void userThirdLogin(String token, String phone, String tokenType, ActionCallbackListener<User> callback) {
        paramsMap.clear();
        paramsMap.put("token",token);
        paramsMap.put("phone",phone);
        paramsMap.put("tokenType",tokenType);
        HttpEngine.getInstance().post(USER_THIRD_LOGIN,map2Ciphertext(paramsMap),User.class,callback);
    }

    @Override
    public void masterPointOfPraise(String masterCode, String userCode,ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("masterCode", masterCode);
        paramsMap.put("userCode",userCode);
        HttpEngine.getInstance().post(MASTER_POINT_OF_PRAISE,map2Ciphertext(paramsMap),Void.class,callback);
    }

    @Override
    public void masterArticleList(String masterCode, int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Article>>> callback) {
        paramsMap.clear();
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("masterCode",masterCode);
        Type type = new TypeToken<BaseListModel<ArrayList<Article>>>(){}.getType();
        HttpEngine.getInstance().post(MASTER_ARTICLE_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void masterArticleDetail(String articleCode, ActionCallbackListener<Article> callback) {
        paramsMap.clear();
        paramsMap.put("articleCode", articleCode);
        HttpEngine.getInstance().post(MASTER_ARTICLE_DETAIL,map2Ciphertext(paramsMap),Article.class,callback);
    }

    @Override
    public void masterCollectionConfirm(String masterCode, String userCode, int type, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("masterCode", masterCode);
        paramsMap.put("userCode", userCode);
        paramsMap.put("type", type);
        HttpEngine.getInstance().post(MASTER_COLLECTION_CONFIRM,map2Ciphertext(paramsMap),Void.class,callback);
    }

    @Override
    public void masterEvaluateConfirm(String masterCode, String userCode, String masterOrderCode, int serviceAttitude, int professionalLevel, String content, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("masterCode", masterCode);
        paramsMap.put("userCode", userCode);
        paramsMap.put("masterOrderCode", masterOrderCode);
        paramsMap.put("serviceAttitude", serviceAttitude);
        paramsMap.put("professionalLevel", professionalLevel);
        paramsMap.put("content", content);
        HttpEngine.getInstance().post(MASTER_EVALUATE_CONFIRM,map2Ciphertext(paramsMap),Void.class,callback);
    }

    @Override
    public void lectureCollectionCollect(String userCode, String lectCode, int type, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("userCode", userCode);
        paramsMap.put("lectCode", lectCode);
        paramsMap.put("type", type);
        HttpEngine.getInstance().post(LECTURE_COLLECTION_COLLECT,map2Ciphertext(paramsMap),Void.class,callback);
    }

    @Override
    public void lectureCollectionList(String userCode, int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Lecture>>> callback) {
        paramsMap.clear();
        paramsMap.put("userCode", userCode);
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize", pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Lecture>>>(){}.getType();
        HttpEngine.getInstance().post(LECTURE_COLLECTION_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void lectureSignUpSign(String userCode, String lectCode, int type, ActionCallbackListener<Void> callback) {
        paramsMap.clear();
        paramsMap.put("userCode", userCode);
        paramsMap.put("lectCode", lectCode);
        paramsMap.put("type", type);
        HttpEngine.getInstance().post(LECTURE_SIGN_UP_SIGN,map2Ciphertext(paramsMap),Void.class,callback);
    }

    @Override
    public void lectureSignPuList(String userCode, int pageNum, int pageSize, ActionCallbackListener<BaseListModel<ArrayList<Lecture>>> callback) {
        paramsMap.clear();
        paramsMap.put("userCode", userCode);
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize", pageSize);
        Type type = new TypeToken<BaseListModel<ArrayList<Lecture>>>(){}.getType();
        HttpEngine.getInstance().post(LECTURE_SIGN_PU_LIST,map2Ciphertext(paramsMap),type,callback);
    }

    @Override
    public void lectureOrderPay(String userCode, String ip, int totalFee, String body, String lectCode, String payType, ActionCallbackListener<PayOrder> callback) {
        paramsMap.clear();
        paramsMap.put("usercode", userCode);
        paramsMap.put("ip", ip);
        paramsMap.put("totalFee", totalFee);
        paramsMap.put("body", body);
        paramsMap.put("lectCode", lectCode);
        paramsMap.put("payType", payType);
        HttpEngine.getInstance().post(LECTURE_ORDER_PAY,map2Ciphertext(paramsMap),PayOrder.class,callback);
    }

    @Override
    public void fileUpload(File file) {
        HttpEngine.getInstance().imageUpload(file);
    }

    @Override
    public void fileUploadBatch(List<File> files) {
        HttpEngine.getInstance().imageUploadBatch(files);
    }


}
