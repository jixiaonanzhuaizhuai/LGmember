package com.lgmember.business;

import android.content.Context;
import android.text.TextUtils;

import com.lgmember.api.HttpApi;
import com.lgmember.api.HttpHandler;
import com.lgmember.impl.LoginImpl;
import com.lgmember.util.StringUtil;

import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2017/2/27.
 */

public class LoginBusiness {
    private String loginName;
    private String loginPass;
    private Context context;
    private LoginImpl loginImpl;

    public LoginBusiness(Context context, String loginName, String loginPass) {
        super();
        this.context = context;
        this.loginName = loginName;
        this.loginPass = loginPass;
    }

    // 先验证参数的可发性，再登陆
    public void Login() {
        // 验证参数是否为空
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPass)) {
            if (handler != null) {
                handler.onArgumentEmpty("参数不能为空");
            }
            return;
        }
        // 如果是手机号
        if (!StringUtil.isPhone(loginName)) {
            if (handler != null) {
                handler.onArgumentFormatError("手机号格式不正确");
            }
            return;
        }
        // TODO 可能还要验证密码
        // 登陆
        loginImpl = new LoginImpl();
        loginImpl.login(loginName,loginPass,handler,context);
    }

    private LoginResultHandler handler;

    public interface LoginResultHandler extends HttpHandler {
        //当参数为空
        public void onArgumentEmpty(String s);

        //当参数不合法时
        public void onArgumentFormatError(String s);
        public void onSuccess();
    }
    public void setHandler(LoginResultHandler handler){
        this.handler = handler;
    }

    }