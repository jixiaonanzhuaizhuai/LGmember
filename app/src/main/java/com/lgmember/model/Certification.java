package com.lgmember.model;

/**
 * Created by Yanan_Wu on 2017/3/22.
 */

public class Certification {

    private String name;
    private String idno;
    private Boolean gender;
    private int nation;
    private String upload_session_id;

    public Certification(){
        super();
    }

    public Certification(String name, String idno,
                         Boolean gender, int nation,
                         String upload_session_id) {
        this.name = name;
        this.idno = idno;
        this.gender = gender;
        this.nation = nation;
        this.upload_session_id = upload_session_id;
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public String getUpload_session_id() {
        return upload_session_id;
    }

    public void setUpload_session_id(String upload_session_id) {
        this.upload_session_id = upload_session_id;
    }
}
