package com.example.managelib;

public class ExpireAccessModel {
    String name,uid,expireDate;

    public ExpireAccessModel(String name,String uid,String expireDate) {
        this.name = name;
        this.expireDate=expireDate;
        this.uid=uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
