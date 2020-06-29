package com.example.drivinglicensequizz.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ContestStatusRealm extends RealmObject {

    @PrimaryKey
    private String id_typeOfContest;
    private int id;
    private int typeOfContest;
    private String status;

    public ContestStatusRealm() {
        status = "UNTESTED";
    }

    public String getId_typeOfContest() {
        return id_typeOfContest;
    }

    public void setId_typeOfContest(String id_typeOfContest) {
        this.id_typeOfContest = id_typeOfContest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeOfContest() {
        return typeOfContest;
    }

    public void setTypeOfContest(int typeOfContest) {
        this.typeOfContest = typeOfContest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
