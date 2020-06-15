package com.example.drivinglicensequizz.data.model;

import android.os.Parcelable;

import java.util.ArrayList;

public class ContestState {
    private int id;
    private boolean isA1A2;
    private boolean isPassed;

    public ContestState(int id, boolean isA1A2, boolean isPassed) {
        this.id = id;
        this.isA1A2 = isA1A2;
        this.isPassed = isPassed;
    }

    public ContestState() {
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
