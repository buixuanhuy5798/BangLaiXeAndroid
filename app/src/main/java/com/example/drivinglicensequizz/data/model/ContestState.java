package com.example.drivinglicensequizz.data.model;

import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.annotations.PrimaryKey;

public class ContestState {

    @PrimaryKey
    private String id_type;
    private int id;
    private boolean isA1A2;
    private boolean isPassed;

    public ContestState(String id_type, int id, boolean isA1A2, boolean isPassed) {
        this.id_type = id_type;
        this.id = id;
        this.isA1A2 = isA1A2;
        this.isPassed = isPassed;
    }

    public ContestState() {
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isA1A2() {
        return isA1A2;
    }

    public void setA1A2(boolean a1A2) {
        isA1A2 = a1A2;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    @Override
    public String toString() {
        return "ContestState{" +
                "id=" + id +
                ", isA1A2=" + isA1A2 +
                ", isPassed=" + isPassed +
                '}';
    }
}
