package com.example.drivinglicensequizz.entity;

import com.example.drivinglicensequizz.data.model.Question;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ContestStateRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private boolean isA1A2;
    private boolean isPassed;
    private long saveAt;

    public ContestStateRealm(int id, boolean isA1A2, boolean isPassed, long saveAt) {
        this.id = id;
        this.isA1A2 = isA1A2;
        this.isPassed = isPassed;
        this.saveAt = saveAt;
    }

    public ContestStateRealm() {
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

    public long getSaveAt() {
        return saveAt;
    }

    public void setSaveAt(long saveAt) {
        this.saveAt = saveAt;
    }
}
