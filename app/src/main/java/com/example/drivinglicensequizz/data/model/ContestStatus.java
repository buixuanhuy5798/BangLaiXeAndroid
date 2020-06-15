package com.example.drivinglicensequizz.data.model;

public class ContestStatus {

    private String id_typeOfContest;
    private int id;
    private int typeOfContest;
    private String status;

    public ContestStatus(String id_typeOfContest, int id, int typeOfContest, String status) {
        this.id_typeOfContest = id_typeOfContest;
        this.id = id;
        this.typeOfContest = typeOfContest;
        this.status = status;
    }

    public ContestStatus() {
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
