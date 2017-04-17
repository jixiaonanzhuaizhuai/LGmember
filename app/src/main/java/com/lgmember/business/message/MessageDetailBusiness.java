package com.lgmember.business.message;

import android.content.Context;

import com.lgmember.api.HttpHandler;
import com.lgmember.bean.MessageBean;
import com.lgmember.impl.MessageImpl;
import com.lgmember.model.Message;

/**
 * Created by Yanan_Wu on 2017/3/6.
 */

public class MessageDetailBusiness {

    private int message_id;
    private Context context;
    private MessageImpl messageImpl;

    public MessageDetailBusiness(Context context, int message_id) {
        super();
        this.context = context;
        this.message_id = message_id;
    }

    // 先验证参数的可发性，再登陆
    public void getMessageDetail() {
        // TODO 可能还要验证密码
        // 登陆
        messageImpl = new MessageImpl();
        messageImpl.getMessageDetail(message_id,handler,context);
    }

    private MessageDetailResultHandler handler;

    public interface MessageDetailResultHandler extends HttpHandler {
        //当参数为空
        public void onMessageDetailSuccess(Message message);

    }
    public void setHandler(MessageDetailResultHandler handler){
        this.handler = handler;
    }
}
