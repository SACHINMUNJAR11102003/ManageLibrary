package com.example.managelib;

public class LiveAccessModel {
    String name,uid,std,edd;
    LiveAccessModel(String name,String uid,String std,String edd){
        this.name=name;
        this.uid=uid;
        this.edd=edd;
        this.std=std;
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

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getEdd() {
        return edd;
    }

    public void setEdd(String edd) {
        this.edd = edd;
    }
}
