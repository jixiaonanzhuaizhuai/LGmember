package com.lgmember.business;

import android.content.Context;
import android.text.TextUtils;

import com.lgmember.api.HttpHandler;
import com.lgmember.impl.SmsCodeImpl;
import com.lgmember.util.StringUtil;

import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2017/2/27.
 */

public class SmsCodeBusiness {
    private String phoneTxt;
    private Context context;
    private SmsCodeImpl smsCodeImpl;

    public SmsCodeBusiness(Context context, String phoneTxt) {
        super();
        this.context = context;
        this.phoneTxt = phoneTxt;
    }

    // 先验证参数的可发性，再登陆
    public void getCode() {
        // 验证参数是否为空
        if (TextUtils.isEmpty(phoneTxt) || TextUtils.isEmpty(phoneTxt)) {
            if (handler != null) {
                handler.onArgumentEmpty("手机号不能为空");
            }
            return;
        }
        // 如果是手机号
        if (!StringUtil.isPhone(phoneTxt)) {
            if (handler != null) {
                handler.onArgumentFormatError("手机号格式不正确");
            }
            return;
        }
        // TODO 可能还要验证密码
        // 登陆
        smsCodeImpl = new SmsCodeImpl();
        smsCodeImpl.getCode(phoneTxt,handler,context);
    }

    private GetCodeResultHandler handler;

    public interface GetCodeResultHandler extends HttpHandler {
        //当参数为空
        public void onArgumentEmpty(String string);

        //当参数不合法时
        public void onArgumentFormatError(String string);


        //当请求验证码返回成功
        public void onRequestCodeSuccess(JSONObject jsob);

        //当请求验证码返回失败时
        public void onRequestCodeFail(String msg);

    }
    public void setHandler(GetCodeResultHandler handler){
        this.handler = handler;
    }

    }