package com.example.drivinglicensequizz;

public class Tip {
    int id;
    int loai;
    String name;

    public Tip(int id, int loai, String name) {
        this.id = id;
        this.loai = loai;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
