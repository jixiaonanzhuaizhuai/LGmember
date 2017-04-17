package com.lgmember.impl;

import android.content.Context;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.lgmember.api.HttpApi;
import com.lgmember.api.HttpHandler;
import com.lgmember.bean.HttpResultBean;
import com.lgmember.business.LoginBusiness;
import com.lgmember.business.person.ForgetPwdBusiness;
import com.lgmember.util.Common;
import com.lgmember.util.JsonUtil;
import com.lgmember.util.StringUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class LoginImpl extends HttpApi {

    private String TAG = "---LoginImpl----";

    public void login(final String loginName, String password, final LoginBusiness.LoginResultHandler handler, Context context) {

        final ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        //http post的json数据格式：  {"name": "****","pwd": "******"}
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", loginName);
            jsonObject.put("pwd", password);
            jsonObject.put("cpt","null");
            jsonObject.put("need_capt","false");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient);
        mMyOkhttp.post()
                .url(Common.URL_LOGIN)
                .jsonParams(jsonObject.toString())          //与 params 不共存 以 jsonParams 优先
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final JSONObject response) {

                        Log.i(TAG,"-----"+cookieJar.toString());

                        try {
                            int code = response.getInt("code");

                            if (code == 0){
                                handler.onSuccess();
                            }else {
                                handler.onError(code);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        handler.onFailed(statusCode, error_msg);

                    }
                });
    }

    //忘记密码

    public void forgetPwd(String mobile, String capt, String sms_capt_token, String password, final ForgetPwdBusiness.ForgetPwdResulHandler handler, Context context) {


        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        //http post的json数据格式：  {"name": "****","pwd": "******"}
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("mobile", mobile);
            jsonObject.put("capt", capt);
            jsonObject.put("sms_capt_token",sms_capt_token);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.post()
                .url(Common.URL_FORGET_PASSWORD)
                .jsonParams(jsonObject.toString())          //与 params 不共存 以 jsonParams 优先
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final JSONObject response) {

                        HttpResultBean httpResultBean = JsonUtil.parseJsonWithGson(response.toString(),HttpResultBean.class);
                        if (httpResultBean.getCode() == 0){
                            handler.onSuccess(httpResultBean.getCode());
                        }else {
                            handler.onError(httpResultBean.getCode());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        handler.onFailed(statusCode, error_msg);

                    }
                });

    }
}
