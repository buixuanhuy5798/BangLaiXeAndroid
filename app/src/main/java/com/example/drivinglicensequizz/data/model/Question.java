package com.example.drivinglicensequizz.data.model;

import android.graphics.Bitmap;

import io.realm.RealmObject;

public class Question  {
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
    private Bitmap bienbao;

    public Question(int id, int anh, String cauhoi, String a, String b, String c, String d, String dapan, String loaibang, String loaibang2) {
        this.id = id;
        this.anh = anh;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
        this.loaibang = loaibang;
        this.loaibang2 = loaibang2;
    }

    public Question() { }

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

    public Bitmap getBienbao() {
        return bienbao;
    }

    public void setBienbao(Bitmap bienbao) {
        this.bienbao = bienbao;
    }
}
