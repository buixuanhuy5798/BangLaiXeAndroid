package com.example.drivinglicensequizz.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ContestStateRealm extends RealmObject {

    @PrimaryKey
    private String id_type;
    private int id;
    private boolean isA1A2;
    private boolean isPassed;

    public ContestStateRealm(int id, boolean isA1A2, boolean isPassed, String id_type) {
        this.id = id;
        this.isA1A2 = isA1A2;
        this.isPassed = isPassed;
        this.id_type = id_type;
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

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }
}
