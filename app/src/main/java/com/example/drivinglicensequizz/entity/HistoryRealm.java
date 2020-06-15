package com.example.drivinglicensequizz.entity;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistoryRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private boolean isA1A2;
    private RealmList<QuestionRealm> listQuestions;

    public HistoryRealm(int id, boolean isA1A2, RealmList<QuestionRealm> listQuestions) {
        this.id = id;
        this.isA1A2 = isA1A2;
        this.listQuestions = listQuestions;
    }

    public HistoryRealm() {};


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

    public RealmList<QuestionRealm> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(RealmList<QuestionRealm> listQuestions) {
        this.listQuestions = listQuestions;
    }
}
