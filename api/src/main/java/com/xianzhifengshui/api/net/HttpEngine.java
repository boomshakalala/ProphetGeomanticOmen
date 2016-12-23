package com.xianzhifengshui.api.net;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.method.KeyListener;
import com.xianzhifengshui.api.utils.KLog;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.xianzhifengshui.api.ApiResponse;
import com.xianzhifengshui.api.des.DESUtils;
import com.xianzhifengshui.api.des.Md5Utils;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.api.model.WXApiResponse;
import com.xianzhifengshui.api.utils.JsonFormatTool;


import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.PortUnreachableException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 作者: 陈冠希
 * 日期: 2016/9/12.
 * 描述: 引擎处理类
 */
public class HttpEngine {
    private final String TAG = getClass().getSimpleName();
    public final String HOST = "http://api.xianzhifengshui.com/";      //服务器主地址
    public final String WX_HOST = "https://api.weixin.qq.com/sns/";
    private static final int JSON_SYNTAX_ERROR = -1;
    private static final String JSON_SYNTAX_INFO = "返回数据格式错误";
    private static final String NETWORK_FAILURE_INFO = "网络不给力哦";
    private static final int NETWORK_FAILURE_ERROR = -100;
    private static final int NETWORK_SUCCESS = 200;
    private static final int WX_NETWORK_SUCCESS = 201;
    private static OkHttpClient client;
    public static HttpEngine instance;

    private HttpEngine(){
        EventBus.getDefault().register(this);
    }

    public static HttpEngine getInstance() {
        if (instance == null) {
            instance = new HttpEngine();
        }
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .build();
        }
        return instance;
    }


    public  <T>  void  get(String method, String ciphertext, final Type typeOfClass ,final ActionCallbackListener<T> callback){
//        String sign = Md5Utils.md5s(DESUtils.decrypt(ciphertext));
        String url = getUrl(HOST + method, ciphertext, "");
        KLog.d(TAG, "get url = "+url);
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent","android")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                com.xianzhifengshui.api.net.Response<T> resp = new com.xianzhifengshui.api.net.Response<>();
                resp.code = NETWORK_FAILURE_ERROR;
                resp.json = NETWORK_FAILURE_INFO;
                resp.callback = callback;
                EventBus.getDefault().post(resp);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                com.xianzhifengshui.api.net.Response<T> resp = new com.xianzhifengshui.api.net.Response<>();
                resp.code = NETWORK_SUCCESS;
                resp.json = response.body().string();
                resp.callback = callback;
                resp.typeOfClass = typeOfClass;
                EventBus.getDefault().post(resp);
            }
        });
    }

    public void wxGet(String method,LinkedHashMap<String,String> params, final ActionCallbackListener<WXApiResponse> callback){
        String url = getUrl(WX_HOST + method, params);
        KLog.d(TAG, "get url = " + url);
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent","android")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                com.xianzhifengshui.api.net.Response<WXApiResponse> resp = new com.xianzhifengshui.api.net.Response<>();
                resp.code = NETWORK_FAILURE_ERROR;
                resp.json = NETWORK_FAILURE_INFO;
                resp.callback = callback;
                EventBus.getDefault().post(resp);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                com.xianzhifengshui.api.net.Response<WXApiResponse> resp = new com.xianzhifengshui.api.net.Response<>();
                resp.code = NETWORK_SUCCESS;
                resp.json = response.body().string();
                resp.callback = callback;
                EventBus.getDefault().post(resp);
            }
        });
    }

    private <T> ApiResponse<T> json2Obj(String json,Type typeOfT) throws JsonSyntaxException {
        KLog.json(TAG, json);
        Gson gson = new Gson();
        ApiResponse<T> response = new ApiResponse<>();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        if (jsonObject.has("statusCode")){
            response.setStatusCode(jsonObject.get("statusCode").getAsInt());
        }
        if (jsonObject.has("status")){
            response.setStatus(jsonObject.get("status").getAsString());
        }
        if (jsonObject.has("message")){
            String message = jsonObject.get("message").getAsString();
            response.setMessage(message.isEmpty()?"未知错误":message);
        }
        if (jsonObject.has("data")&&!(typeOfT==Void.class)){
            String jsonDataStr = jsonObject.get("data").toString();
            T data = gson.fromJson(jsonDataStr,typeOfT);
            JsonObject jsonData = parser.parse(jsonDataStr).getAsJsonObject();
            response.setData(data);
        }
        return response;
    }

    private WXApiResponse json2Obj(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,WXApiResponse.class);
    }


    private String getUrl(String url ,String json,String sign){
//        return url + "?json="+json+"&sign="+sign;
        return url + "?json="+json;
    }

    private String getUrl(String url,Map<String,String> params){
        String strParams = "?";
        if(params != null){
            Iterator<String> iterator = params.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next();
                strParams += key + "=" + params.get(key)+"&";
            }
        }
        String result = url+strParams;
        return result.substring(0, result.lastIndexOf('&'));
    }

    void onEventMainThread(com.xianzhifengshui.api.net.Response resp){
        switch (resp.code){
            case NETWORK_FAILURE_ERROR:
                if (resp.callback!=null){
                    String info = resp.json;
                    resp.callback.onFailure(NETWORK_FAILURE_ERROR,info);
                }
                break;
            case NETWORK_SUCCESS:
                if (resp.callback!=null){
                    String json = resp.json;
                    KLog.json(TAG, json);
                    ApiResponse response = json2Obj(json,resp.typeOfClass);
                    if (response.isSuccess()){
                        resp.callback.onSuccess(response.getData());
                    }else if (response.getStatus().equals("success")){
                        resp.callback.onFailure(response.getStatusCode(),response.getMessage());
                    }else {
                        resp.callback.onFailure(response.getStatusCode(),response.getMessage());
                        KLog.e(TAG, "network error:"+response.getMessage());
                    }
                }
                break;
            case WX_NETWORK_SUCCESS:
                if (resp.callback!=null){
                    String json = resp.json;
                    WXApiResponse response = json2Obj(json);
                    if (response.getErrcode() == 0) {
                        resp.callback.onSuccess(response);
                    } else {
                        KLog.d(TAG, "onSuccess errorCode=" + response.getErrcode());
                        KLog.d(TAG, "onSuccess errorMsg=" + response.getErrmsg());
                        resp.callback.onFailure(response.getErrcode(), response.getErrmsg());
                    }
                }
                break;
        }
    }



}
