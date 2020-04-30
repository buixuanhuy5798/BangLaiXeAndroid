package com.example.drivinglicensequizz;

import android.graphics.Bitmap;
import android.media.Image;

public class TrafficSign {
    private int anh;
    private String noidung;
    private int loaibien;
    private Bitmap bienbao;

    public TrafficSign(int anh, String noidung, int loaibien) {
        this.anh = anh;
        this.noidung = noidung;
        this.loaibien = loaibien;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public Bitmap getBienbao() {
        return bienbao;
    }

    public void setBienbao(Bitmap bienbao) {
        this.bienbao = bienbao;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getLoaibien() {
        return loaibien;
    }

    public void setLoaibien(int loaibien) {
        this.loaibien = loaibien;
    }
}
