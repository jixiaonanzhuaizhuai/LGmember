package com.lgmember.impl;

import android.content.Context;

import android.util.Log;

import com.lgmember.api.HttpApi;
import com.lgmember.business.ShowNetworkImgBusiness;
import com.lgmember.business.UpdatePhotoBusiness;
import com.lgmember.business.UploadImgBusiness;
import com.lgmember.util.Common;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ImgImpl extends HttpApi {

    private String TAG = "---ImgImpl--";


    public void uploadImg(String session_id, File file, final UploadImgBusiness.UploadImgResulHandler handler, Context context) {

        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.upload()
                .url(Common.URL_UPLOAD_IMG + "?id=" + session_id)
                .addFile("photo", file)
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final JSONObject response) {
                        try {
                            int code = response.getInt("code");
                            if (code == 0) {
                                handler.onUploadImgSuccess();
                            } else {
                                handler.onError(code);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        handler.onFailed(statusCode, error_msg);
                        Log.i(TAG, 1111 + statusCode + error_msg);
                    }
                });

    }

    //加载网络图片
    public void showNetworkImg(String photoName, final ShowNetworkImgBusiness.ShowNetworkImgResulHandler handler, Context context) {

        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }

        final OkHttpClient okHttpClient = new OkHttpClient();

        String photo_url = Common.URL_BASE + "avatar/" + photoName;

        final Request request = new Request.Builder().url(photo_url).build();

        okhttp3.Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                handler.onShowImgFailed("加载网络图片失败");
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                handler.onSuccess(bytes);
            }
        });
    }


    public void updatePhoto(File file, final UpdatePhotoBusiness.UpdatePhotoResulHandler handler, Context context) {

        //判断没有网络应该如何处理
        if (!app.isNetWorkEnable(context)) {
            handler.onNetworkDisconnect();
        }
        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient());
        mMyOkhttp.upload()
                .url(Common.URL_UPDATE_PHOTO)
                .addFile("photo",file)
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final JSONObject response) {
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
                        Log.i(TAG,55552222+statusCode+error_msg);

                    }
                });

    }


     /* OkHttpClient mOkHttpClient = new OkHttpClient();


        //创建RequestBody
        RequestBody body = RequestBody.create(MediaType.parse("image*//*"), file);
        //创建Request
        final Request request = new Request.Builder().url(Common.URL_UPDATE_PHOTO).post(body).build();
        final Call call = mOkHttpClient.newBuilder().
                addNetworkInterceptor(new StethoInterceptor())
                .writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    Log.e(TAG, "response ----->" + string);
                }
            }


        });*/
}
