package com.xianzhifengshui.api.net;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.method.KeyListener;
import android.util.Log;

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
import java.util.concurrent.TimeUnit;

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
    private UIThreadHandler uiThreadHandler;

    private HttpEngine(){
        uiThreadHandler = new UIThreadHandler();
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
        String sign = Md5Utils.md5s(DESUtils.decrypt(ciphertext));
        String url = getUrl(HOST + method, ciphertext, sign);
        Log.d(TAG, "get url = "+url);
        Log.d(TAG, "get sign = "+sign);
        Log.d(TAG, "get json = " + ciphertext);
        if (uiThreadHandler!=null){
            uiThreadHandler.setCallback(callback);
            uiThreadHandler.setType(typeOfClass);
        }
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent","android")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = NETWORK_FAILURE_ERROR;
                msg.obj = NETWORK_FAILURE_INFO;
                uiThreadHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = NETWORK_SUCCESS;
                msg.obj = response.body().string();
                uiThreadHandler.sendMessage(msg);
            }
        });
    }

    public void wxGet(String method,LinkedHashMap<String,String> params, final ActionCallbackListener<WXApiResponse> callback){
        String url = getUrl(WX_HOST + method, params);
        Log.d(TAG, "get url = " + url);
        if (uiThreadHandler!=null){
            uiThreadHandler.setCallback(callback);
            uiThreadHandler.setType(WXApiResponse.class);
        }
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent","android")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = NETWORK_FAILURE_ERROR;
                msg.obj = NETWORK_FAILURE_INFO;
                uiThreadHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = WX_NETWORK_SUCCESS;
                msg.obj = response.body().string();
                uiThreadHandler.sendMessage(msg);
            }
        });
    }

    private <T> ApiResponse<T> json2Obj(String json,Type typeOfT) throws JsonSyntaxException {
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
        return url + "?json="+json+"&sign="+sign;
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

    class UIThreadHandler extends Handler{
        private ActionCallbackListener callback;
        private Type type;

        public void setType(Type type) {
            this.type = type;
        }

        public <T> void setCallback(ActionCallbackListener<T> callback){
            this.callback = callback;
        }
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NETWORK_FAILURE_ERROR:
                    if (callback!=null){
                        String info = (String) msg.obj;
                        callback.onFailure(NETWORK_FAILURE_ERROR,info);
                    }
                    break;
                case NETWORK_SUCCESS:
                    if (callback!=null){
                        String json = (String) msg.obj;
                        Log.d(TAG, "handleMessage json="+json);
                        ApiResponse response = json2Obj(json,type);
                        if (response.isSuccess()){
                            callback.onSuccess(response.getData());
                        }else if (response.getStatus().equals("success")){
                            callback.onFailure(response.getStatusCode(),response.getMessage());
                        }else {
                            callback.onFailure(response.getStatusCode(),response.getMessage());
                            Log.e(TAG, "network error:"+response.getMessage());
                        }
                    }
                    break;
                case WX_NETWORK_SUCCESS:
                    if (callback !=null){
                        String json = (String) msg.obj;
                        WXApiResponse response = json2Obj(json);
                        if (response.getErrcode() == 0) {
                            callback.onSuccess(response);
                        } else {
                            Log.d(TAG, "onSuccess errorCode=" + response.getErrcode());
                            Log.d(TAG, "onSuccess errorMsg=" + response.getErrmsg());
                            callback.onFailure(response.getErrcode(), response.getErrmsg());
                        }
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

}
