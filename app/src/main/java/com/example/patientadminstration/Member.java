package com.example.patientadminstration;

public class Member {

    private String name,dob,address, mobile, heartrate, bp,blood,resprate, temp, IVname, IVrate, IVreading, medicalcond,id,BedNo;

    public Member() {
    }

    public Member(String name, String dob, String address, String mobile, String heartrate, String bp, String blood, String resprate, String temp, String IVname, String IVrate, String IVreading, String medicalcond, String id, String BedNo) {
        this.BedNo = BedNo;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.mobile = mobile;
        this.heartrate = heartrate;
        this.bp = bp;
        this.blood = blood;
        this.resprate = resprate;
        this.temp = temp;
        this.IVname = IVname;
        this.IVrate = IVrate;
        this.IVreading = IVreading;
        this.medicalcond = medicalcond;
        this.id = id;
    }

    public String getBedNo() {
        return BedNo;
    }

    public void setBedNo(String bedNo) {
        BedNo = bedNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getResprate() {
        return resprate;
    }

    public void setResprate(String resprate) {
        this.resprate = resprate;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getIVname() {
        return IVname;
    }

    public void setIVname(String IVname) {
        this.IVname = IVname;
    }

    public String getIVrate() {
        return IVrate;
    }

    public void setIVrate(String IVrate) {
        this.IVrate = IVrate;
    }

    public String getIVreading() {
        return IVreading;
    }

    public void setIVreading(String IVreading) {
        this.IVreading = IVreading;
    }

    public String getMedicalcond() {
        return medicalcond;
    }

    public void setMedicalcond(String medicalcond) {
        this.medicalcond = medicalcond;
    }

    public String toString(){
        return IVreading;
    }
}
