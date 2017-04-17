package com.lgmember.bean;

import com.lgmember.model.HistoryScores;
import com.lgmember.model.Message;

import java.util.List;

/**
 * Created by Yanan_Wu on 2017/3/9.
 */

public class MessageBean extends HttpResultBean {
    private int total;
    private List<Message> messageList;

    public MessageBean(){
        super();
    }

    public MessageBean(int total, List<Message> messageList) {
        this.total = total;
        this.messageList = messageList;
    }

    public MessageBean(int code, int total, List<Message> messageList) {
        super(code);
        this.total = total;
        this.messageList = messageList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
