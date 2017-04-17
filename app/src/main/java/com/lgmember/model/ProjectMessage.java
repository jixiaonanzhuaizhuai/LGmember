package com.lgmember.model;

import java.io.Serializable;

/**
 * Created by Yanan_Wu on 2017/3/14.
 */


/*
* object ReviewState {
  //草稿
  final val DRAFT = 0
  //等待审核
  final val WAIT_FOR_REVIEW = 1
  //频率频道审核失败
  final val FIRST_REVIEW_FAILED = 2
  //频率频道审核通过
  final val FIRST_REVIEW_PASS = 3
  //台级审核失败
  final val SECOND_REVIEW_FAILED = 4
  //台级审核通过
  final val SECOND_REVIEW_PASS = 5
}*/
public class ProjectMessage implements Serializable{
    private int id; // 招募信息id
    private String title; // 标题
    private String picture;//图片, base64
    private String content;// 活动宣传内容
    private String hyperlink; // 超链接
    private Boolean saved; // 是否收藏
    private int state;  //会员报名状态   -1未报名;0:报名未签到;1:报名且签到;2:未报名但签到;3:候补报名
    private int count; // 报名总数
    private String start_time;
    private String end_time;


    public ProjectMessage(){
        super();
    }

    public ProjectMessage(int id, String title, String picture, String content, String hyperlink, Boolean saved, int state, int count, String start_time, String end_time) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.content = content;
        this.hyperlink = hyperlink;
        this.saved = saved;
        this.state = state;
        this.count = count;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public final String getEnd_time() {
        return end_time;
    }

    public final void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getPicture() {
        return picture;
    }

    public final void setPicture(String picture) {
        this.picture = picture;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final String getHyperlink() {
        return hyperlink;
    }

    public final void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public final Boolean getSaved() {
        return saved;
    }

    public final void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public final int getState() {
        return state;
    }

    public final void setState(int state) {
        this.state = state;
    }

    public final int getCount() {
        return count;
    }

    public final void setCount(int count) {
        this.count = count;
    }

    public final String getStart_time() {
        return start_time;
    }

    public final void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }
}
