package com.lgmember.model;

/**
 * Created by Yanan_Wu on 2017/3/17.
 */

public class Member {

    private String name;
    private String idno;
    private String mobile;
    private Boolean gender;
    private String addr;
    private String company;
    private String job_title;
    private int nation;
    private int source;
    private String create_time;
    private int education;
    private int month_income;
    private int month_outcome;
    private int state;
    private int level;
    private String avatar;
    private Boolean authorized;
    private int point;
    private String card_no;
    private int point_used;
    private Boolean black;
    private int num_regist;
    private int num_sign;

    public Member(){
        super();
    }

    public Member(String name, String idno, String mobile, Boolean gender,
                  String addr, String company, String job_title, int nation,
                  int source, String create_time, int education,
                  int month_income, int month_outcome, int state, int level,
                  String avatar, Boolean authorized, int point, String card_no,
                  int point_used, Boolean black, int num_regist, int num_sign) {
        this.name = name;
        this.idno = idno;
        this.mobile = mobile;
        this.gender = gender;
        this.addr = addr;
        this.company = company;
        this.job_title = job_title;
        this.nation = nation;
        this.source = source;
        this.create_time = create_time;
        this.education = education;
        this.month_income = month_income;
        this.month_outcome = month_outcome;
        this.state = state;
        this.level = level;
        this.avatar = avatar;
        this.authorized = authorized;
        this.point = point;
        this.card_no = card_no;
        this.point_used = point_used;
        this.black = black;
        this.num_regist = num_regist;
        this.num_sign = num_sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getMonth_income() {
        return month_income;
    }

    public void setMonth_income(int month_income) {
        this.month_income = month_income;
    }

    public int getMonth_outcome() {
        return month_outcome;
    }

    public void setMonth_outcome(int month_outcome) {
        this.month_outcome = month_outcome;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public int getPoint_used() {
        return point_used;
    }

    public void setPoint_used(int point_used) {
        this.point_used = point_used;
    }

    public Boolean getBlack() {
        return black;
    }

    public void setBlack(Boolean black) {
        this.black = black;
    }

    public int getNum_regist() {
        return num_regist;
    }

    public void setNum_regist(int num_regist) {
        this.num_regist = num_regist;
    }

    public int getNum_sign() {
        return num_sign;
    }

    public void setNum_sign(int num_sign) {
        this.num_sign = num_sign;
    }
}
