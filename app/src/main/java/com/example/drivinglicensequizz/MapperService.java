package com.example.drivinglicensequizz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.drivinglicensequizz.data.model.ContestState;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.entity.ContestStateRealm;
import com.example.drivinglicensequizz.entity.HistoryRealm;
import com.example.drivinglicensequizz.entity.QuestionRealm;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;

public class MapperService {

    private static  MapperService INSTANCE = null;

    private MapperService() {};

    public static MapperService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapperService();
        }
        return INSTANCE;
    }

    public ContestState mapContestStateRealmToContestState(ContestStateRealm contestStateRealm) {
        ContestState contestState = new ContestState();
        contestState.setId(contestStateRealm.getId());
        contestState.setA1A2(contestStateRealm.isA1A2());
        contestState.setPassed(contestStateRealm.isPassed());
        return contestState;
    }

    public ArrayList<Question> mapRealmListToArrayList(RealmList<QuestionRealm> realmListQuestions) {
        ArrayList<Question> questions = new ArrayList<>();
        for (QuestionRealm questionRealm: realmListQuestions) {
            questions.add(mapQuestionRealmToQuestion(questionRealm));
        }
        return questions;
    }

    public QuestionRealm mapQuestionToQuestionRealm(Question question, String yourAnswer) {
        QuestionRealm res = new QuestionRealm();
        res.setId(question.getId());
        res.setCauhoi(question.getCauhoi());
        res.setAnh(question.getAnh());
        Log.d("anh", question.getAnh() + "");
        res.setA(question.getA());
        res.setB(question.getB());
        res.setC(question.getC());
        res.setD(question.getD());
        res.setDapan(question.getDapan());
        res.setLoaibang(question.getLoaibang());
        res.setLoaibang2(question.getLoaibang2());
        if (question.getBienbao() != null) {
            res.setBienbaoByte(mapBitmapToByteArray(question.getBienbao()));
        }
        res.setAnswers(yourAnswer);
        return res;
    }

    public Question mapQuestionRealmToQuestion(QuestionRealm questionRealm) {
        Question question = new Question();
        question.setId(questionRealm.getId());
        question.setAnh(questionRealm.getAnh());
        question.setCauhoi(questionRealm.getCauhoi());
        question.setA(questionRealm.getA());
        question.setB(questionRealm.getB());
        question.setC(questionRealm.getC());
        question.setD(questionRealm.getD());
        question.setId(questionRealm.getId());
        question.setDapan(questionRealm.getDapan());
        question.setLoaibang(questionRealm.getLoaibang());
        question.setLoaibang2(questionRealm.getLoaibang2());
        question.setAnwser(questionRealm.getAnswers());
        return question;
    }

    private byte[] mapBitmapToByteArray(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        image.recycle();
        return byteArray;
    }

    public boolean[][] convertListStringToBooleanMatrix(List<String> answers) {
        boolean [][]answersConverted = new boolean [30][4];
        for (int i = 0; i < answers.size(); i++) {
            answersConverted[i][0] = false;
            answersConverted[i][1] = false;
            answersConverted[i][2] = false;
            answersConverted[i][3] = false;
            if (answers.get(i).isEmpty()) {

            } else {
                if (answers.get(i).contains("-")) {
                    String []multiChoose = answers.get(i).split("-");
                    for (int j = 0; j < multiChoose.length; j++) {
                        answersConverted[i][j] = true;
                    }
                } else {
                    answersConverted[i][Integer.valueOf(answers.get(i)) - 1] = true;
                }
            }
        }
        return answersConverted;
    }

}
