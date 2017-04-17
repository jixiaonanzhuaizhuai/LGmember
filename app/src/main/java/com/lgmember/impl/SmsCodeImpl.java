package com.lgmember.impl;

import android.content.Context;
import android.util.Log;

import com.lgmember.api.HttpApi;
import com.lgmember.business.SmsCodeBusiness;
import com.lgmember.util.Common;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

public class SmsCodeImpl extends HttpApi {

    private String TAG = "---SmsCodeImpl---";

    public void getCode(String phoneTxt, final SmsCodeBusiness.GetCodeResultHandler handler, Context context){
        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.get()
                .url(Common.URL_REQUEST_CODE)
                .addParam("mobile",phoneTxt)
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        Log.i(TAG,response.toString());
                        handler.onRequestCodeSuccess(response);

                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        handler.onFailed(statusCode,error_msg);
                    }
                });
    }
}
