package com.example.managelib;

public class AllAccessModel {
    String name,dateJoined,uid,aadhar,phone,slot;

    public AllAccessModel(String name,String uid,String dateJoined,String aadhar,String phone,String slot) {
        this.name = name;
        this.dateJoined=dateJoined;
        this.uid=uid;
        this.aadhar=aadhar;
        this.phone=phone;
        this.slot=slot;
    }

    public String getName() {
        return name;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
