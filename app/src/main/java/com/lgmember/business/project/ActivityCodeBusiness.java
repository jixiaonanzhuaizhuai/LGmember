package com.lgmember.business.project;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lgmember.api.HttpHandler;
import com.lgmember.impl.SignImpl;
import com.lgmember.impl.SmsCodeImpl;
import com.lgmember.util.StringUtil;

import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2017/2/27.
 */

public class ActivityCodeBusiness {
    private String activityCode;
    private Context context;
    private SignImpl signImpl;

    public ActivityCodeBusiness(Context context, String activityCode) {
        super();
        this.context = context;
        this.activityCode = activityCode;
    }

    // 先验证参数的可发性，再登陆
    public void getActivityCode() {
        // 验证参数是否为空
        if (TextUtils.isEmpty(activityCode)) {
            if (handler != null) {
                handler.onArgumentEmpty("活动码不能为空");
            }
            return;
        }
        //判断参数是否全是数字
        if (!StringUtil.isAllNumber(activityCode)){
            if (handler != null){
                handler.onArgumentFormatError("请输入全部是数字的活动码");
            }
            return;
        }

        // 判断活动码是否有效
        signImpl = new SignImpl();
        signImpl.getActivityCode(activityCode,handler,context);
    }

    private ActivityCodeResulHandler handler;

    public interface ActivityCodeResulHandler extends HttpHandler {
        //当参数为空
        public void onArgumentEmpty(String s);

        //当参数不合法时
        public void onArgumentFormatError(String s);

        public void onSuccess(String string);

    }
    public void setHandler(ActivityCodeResulHandler handler){
        this.handler = handler;
    }

    }