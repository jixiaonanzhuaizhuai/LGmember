package com.lgmember.model;

/**
 * Created by Yanan_Wu on 2017/3/9.
 */

public class Message {
    private int message_id;
    private String title;
    //private String picture;
    private String content;
    private String hyperlink;
    private String create_time;
    private String author;
    private int state;

    public Message(){
        super();
    }

    public Message(int message_id, String title,
                   String content, String hyperlink, String create_time,
                   String author, int state) {
        this.message_id = message_id;
        this.title = title;
        //this.picture = picture;
        this.content = content;
        this.hyperlink = hyperlink;
        this.create_time = create_time;
        this.author = author;
        this.state = state;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }*/

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
