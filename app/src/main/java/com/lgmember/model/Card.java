package com.lgmember.model;

import java.io.Serializable;

/**
 * Created by Yanan_Wu on 2017/3/19.
 */

/*
*  /**
    * 卡券
    *
    * @param id          卡券id
    * @param name        名称
    * @param description 简介
    * @param start_time  开始时间
    * @param end_time    结束时间
    * @param type_id     类型id
    * @param type_name   类型名称
    * @param num         数量
    * @param level       等级
    * @param no          编号
    * @param code        兑换码
    */

public class Card implements Serializable {
    private int id;
    private String name;
    private String description;
    private String start_time;
    private String end_time;
    private int type_id;
    private String type_name;
    private int num;
    private int level;
    private String no;
    private String code;

    public Card(int id, String name, String description,
                String start_time, String end_time, int type_id,
                String type_name, int num, int level, String no,
                String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.type_id = type_id;
        this.type_name = type_name;
        this.num = num;
        this.level = level;
        this.no = no;
        this.code = code;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getStart_time() {
        return start_time;
    }

    public final void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public final String getEnd_time() {
        return end_time;
    }

    public final void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public final int getType_id() {
        return type_id;
    }

    public final void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public final String getType_name() {
        return type_name;
    }

    public final void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public final int getNum() {
        return num;
    }

    public final void setNum(int num) {
        this.num = num;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(int level) {
        this.level = level;
    }

    public final String getNo() {
        return no;
    }

    public final void setNo(String no) {
        this.no = no;
    }

    public final String getCode() {
        return code;
    }

    public final void setCode(String code) {
        this.code = code;
    }
}
