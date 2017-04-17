package com.lgmember.impl;

import android.content.Context;
import android.util.Log;

import com.lgmember.api.HttpApi;
import com.lgmember.bean.MessageBean;
import com.lgmember.business.message.DeleteMsgBusiness;
import com.lgmember.business.message.MessageBusiness;
import com.lgmember.business.message.MessageDetailBusiness;
import com.lgmember.model.Message;
import com.lgmember.util.Common;
import com.lgmember.util.JsonUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageImpl extends HttpApi {

    private String TAG = "----MessageImpl----";

    public void getSysMessage(int pageNo, int pageSize, final MessageBusiness.MessageResultHandler handler, Context context){
        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        final JSONObject jsonObject = new JSONObject();

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.get()
                .url(Common.URL_MESSAGE_LIST)
                .addParam("pageNo",String.valueOf(pageNo))
                .addParam("pageSize",String.valueOf(pageSize))
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {


                        MessageBean messageBean = JsonUtil.parseMessageResult(response);
                        if (messageBean.getCode() == 0){
                            handler.onSuccess(messageBean);
                        }else {
                            handler.onError(messageBean.getCode());
                        }

                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        handler.onFailed(statusCode,error_msg);
                    }
                });
    }

    public void deleteMessage(int message_id, int state, final DeleteMsgBusiness.DeleteMsgResultHandler handler, Context context){
        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        //http post的json数据格式：  {"name": "****","pwd": "******"}
        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("message_id",message_id);
            jsonObject.put("state", state);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.post()
                .url(Common.URL_DELETE_MESSAGE)
                .jsonParams(jsonObject.toString())
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        Log.i(TAG,response.toString());

                        try {
                            int code = response.getInt("code");
                            if (code == 0){
                                handler.onDeleteMsgSuccess();
                            }else {
                                handler.onError(code);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.i("----000111-----",statusCode+error_msg);
                        //handler.onFailed(statusCode,error_msg);
                    }
                });



    }

    public void getMessageDetail(int message_id, final MessageDetailBusiness.MessageDetailResultHandler handler, Context context){
        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }


        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.get()
                .url(Common.URL_MESSAGE_DETAIL)
                .addParam("message_id",String.valueOf(message_id))
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try {
                            int code = response.getInt("code");

                            if (code == 0){
                                JSONObject jsonObject = response.getJSONObject("message");
                                Message message = new Message(jsonObject.getInt("message_id"),
                                        jsonObject.getString("title"),jsonObject.getString("content"),
                                        jsonObject.getString("hyperlink"),jsonObject.getString("create_time"),
                                        jsonObject.getString("author"),jsonObject.getInt("state"));
                                handler.onMessageDetailSuccess(message);
                            }else {
                                handler.onError(code);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.i("----000111-----",statusCode+error_msg);
                        handler.onFailed(statusCode,error_msg);
                    }
                });
    }
}
