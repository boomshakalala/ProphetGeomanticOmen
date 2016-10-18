package com.xianzhifengshui.api.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.xianzhifengshui.api.ApiResponse;
import com.xianzhifengshui.api.des.DESUtils;
import com.xianzhifengshui.api.des.Md5Utils;


import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;


/**
 * 作者: 陈冠希
 * 日期: 2016/9/12.
 * 描述: 引擎处理类
 */
public class HttpEngine {
    private final String TAG = "HttpEngine";
    public final String HOST = "http://www.xianzhifengshui/api/";      //服务器主地址
    private static final int JSON_SYNTAX_ERROR = -1;
    private static final String JSON_SYNTAX_INFO = "返回数据格式错误";
    private static AsyncHttpClient client;
    public static HttpEngine instance;

    private HttpEngine(){

    }

    public static HttpEngine getInstance(){
        if (instance == null) {
            instance = new HttpEngine();
        }
        if (client == null) {
            client = new AsyncHttpClient();
            client.setTimeout(1000 * 10);
            client.addHeader("client", "Android");
            client.addHeader("Accept-Encoding", "gzip");
        }
        return instance;
    }


    public  <T>  void  get(String method, String ciphertext, final Type typeOfClass ,final ActionCallbackListener<T> callback){
        String url = HOST + method;
        String sign = Md5Utils.md5s(DESUtils.decrypt(ciphertext));
        RequestParams params = new RequestParams();
        params.put("json", ciphertext);
        params.put("sign",sign);
        client.get(url, params, new TextHttpResponseHandler()  {


            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                callback.onProgress(bytesWritten, totalSize);
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                Log.e(TAG, "network error:"+throwable.getMessage());
                callback.onFailure(i,"网络不给力哦");
            }

            @Override
            public void onSuccess(int i, Header[] headers, String ciphertext) {
                String json = DESUtils.decrypt(ciphertext);
                ApiResponse<T> response = json2Obj(json, typeOfClass);
                if (response.isSuccess()){
                    callback.onSuccess(response.getData());
                }else if (response.getStatus().equals("success")){
                    callback.onFailure(response.getStatusCode(),response.getMessage());
                }else {
                    Log.e(TAG, "network error:"+response.getMessage());
                }
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
        if (jsonObject.has("data")){
            String jsonDataStr = jsonObject.get("data").toString();
            T data = gson.fromJson(jsonDataStr,typeOfT);
            JsonObject jsonData = parser.parse(jsonDataStr).getAsJsonObject();
            response.setData(data);
        }
        return response;
    }





}
