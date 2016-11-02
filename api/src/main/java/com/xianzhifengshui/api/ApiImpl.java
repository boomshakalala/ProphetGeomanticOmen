package com.xianzhifengshui.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianzhifengshui.api.des.DESUtils;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.model.User;
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
    private final String TAG = "ApiImpl";
    public final int PARAM_NULL = -2;
    private LinkedHashMap<String,String> paramsMap;

    public ApiImpl(){
        paramsMap = new LinkedHashMap<>();
    }

    /**
     * 参数加密
     * @param map 要传的参数
     * @return 加密后的字符串
     */
    private String map2Ciphertext(LinkedHashMap<String,String> map){
        Gson gson = new Gson();
        LinkedHashMap<String,String> resultMap = new LinkedHashMap();
        //城市代码:北京（110000）;上海（200000）；其他待定
        resultMap.put("cityCode", "110000");
        //设备类型:安卓传android；苹果传ios；pc端传pc，微信端传weixin，M站传mobile
        resultMap.put("deviceType", "android");
        resultMap.putAll(map);
        String json = gson.toJson(resultMap);
        Log.d(TAG, "map2Ciphertext json = "+ JsonFormatTool.formatJson(json));
        return DESUtils.encrypt(json);

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
        paramsMap.put("pageNum", String.valueOf(pageNum));
        paramsMap.put("pageSize",String.valueOf(pageSize));
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


}
