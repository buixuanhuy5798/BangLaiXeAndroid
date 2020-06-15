package com.example.drivinglicensequizz.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuestionRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private int anh;
    private String cauhoi;
    private String a;
    private String b;
    private String c;
    private String d;
    private String dapan;
    private String loaibang;
    private String loaibang2;
    private byte []bienbaoByte;
    private String answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public String getLoaibang() {
        return loaibang;
    }

    public void setLoaibang(String loaibang) {
        this.loaibang = loaibang;
    }

    public String getLoaibang2() {
        return loaibang2;
    }

    public void setLoaibang2(String loaibang2) {
        this.loaibang2 = loaibang2;
    }

    public byte[] getBienbaoByte() {
        return bienbaoByte;
    }

    public void setBienbaoByte(byte[] bienbaoByte) {
        this.bienbaoByte = bienbaoByte;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
