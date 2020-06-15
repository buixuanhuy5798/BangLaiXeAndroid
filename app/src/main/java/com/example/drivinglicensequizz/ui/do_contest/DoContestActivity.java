package com.example.drivinglicensequizz.ui.do_contest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.MapperService;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.entity.ContestStateRealm;
import com.example.drivinglicensequizz.entity.HistoryRealm;
import com.example.drivinglicensequizz.entity.QuestionRealm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class DoContestActivity extends AppCompatActivity {

    private Button nextQuestion, previousQuestion, submitButton;
    private ImageButton backButton;
    private RecyclerView recyclerViewContest;
    private TextView numberOfQuestion, titleContest, timeLeft;

    ArrayList<Question> questions;
    Question dataOnRyclerView;
    QuestionContestAdapter questionContestAdapter;
    int typeOfContest;
    int sttContest;
    int numberQuestion = 1;
    int maxQuestion;
    int mark = 0;
    int timeInMilis;
    CountDownTimer threadTime;
    List<String> answers = new ArrayList<>();
    Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_contest);
        setUpLayout();
        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("listQuestionContest");
        typeOfContest = intent.getIntExtra("typeOfContest",1);
        maxQuestion = typeOfContest == 0 ? 20 : 30;
        timeInMilis = typeOfContest == 0 ? 900001 : 1200001;
        sttContest = intent.getIntExtra("numberContest", 1);
        mRealm = Realm.getDefaultInstance();
        setUpAction();
        setupData();

        threadTime = new CountDownTimer(timeInMilis, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
                timeLeft.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                timeLeft.setText("00:00:00");
                questionContestAdapter.setShowAnswer();
                submitButton.setEnabled(false);
                getMarkOfContest();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (!questionContestAdapter.isSubmitted) {
            showAlert();
        } else {
            super.onBackPressed();
        }
    }

    private void setUpLayout() {
        backButton = findViewById(R.id.back_button_contest);
        nextQuestion = findViewById(R.id.next_button);
        previousQuestion = findViewById(R.id.previous_button);
        recyclerViewContest = findViewById(R.id.contest_recycleView);
        numberOfQuestion = findViewById(R.id.numberOfQuestion);
        titleContest = findViewById(R.id.titleContest);
        submitButton = findViewById(R.id.submitButton);
        timeLeft = findViewById(R.id.timeLeft);
    }

    private void setUpAction() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questionContestAdapter.isSubmitted) {
                    showAlert();
                } else {
                    finish();
                }
            }
        });
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextQuestion(true);
            }
        });
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextQuestion(false);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionContestAdapter.setShowAnswer();
                submitButton.setEnabled(false);
                getMarkOfContest();
                threadTime.cancel();
            }
        });
    }

    private void setupData() {
        titleContest.setText("Đề " + String.valueOf(sttContest));
        addImageToArray();
        dataOnRyclerView = questions.get(0);
        numberOfQuestion.setText("Câu " + String.valueOf(numberQuestion) + "/" + String.valueOf(maxQuestion));
        questionContestAdapter = new QuestionContestAdapter(this, dataOnRyclerView, 1, typeOfContest, false);
        recyclerViewContest.setAdapter(questionContestAdapter);
        recyclerViewContest.setLayoutManager(new LinearLayoutManager(this));
    }

    private void toNextQuestion(boolean isNext) {
        if (isNext) {
            if (numberQuestion < maxQuestion) {
                numberQuestion += 1;
                dataOnRyclerView = questions.get(numberQuestion - 1);
                questionContestAdapter.reloadData(dataOnRyclerView, numberQuestion);
            }
        } else {
            if (numberQuestion > 1) {
                numberQuestion -= 1;
                dataOnRyclerView = questions.get(numberQuestion - 1);
                questionContestAdapter.reloadData(dataOnRyclerView, numberQuestion);
            }
        }
        numberOfQuestion.setText("Câu " + String.valueOf(numberQuestion) + "/" + String.valueOf(maxQuestion));
    }

    private void addImageToArray() {
        for(int i = 0; i < questions.size(); i++) {
            try {
                Bitmap bitmap =  BitmapFactory.decodeStream(getAssets().open(String.valueOf(questions.get(i).getId()) + ".png"));
                questions.get(i).setBienbao(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You are still in the contest time. Are your sure?")
                          .setTitle("Warning")
                          .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                              }
                          })
                          .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  finish();
                              }
                          }).show();
    }

    private void getMarkOfContest() {
        for (int i = 0; i < maxQuestion; i++ ) {
            String res = "";
            for (int j = 0; j < 4; j++) {
                if (questionContestAdapter.yourAnswer[i][j]) {
                    res += String.valueOf(j+1) + "-";
                }
            }
            if (!res.isEmpty()) { res = res.substring(0, res.length() - 1); }
            answers.add(res);
            if (questions.get(i).getDapan().compareTo(res) == 0) { mark++; }
        }
        saveContest(mark);
        Toast.makeText(this, "You got " + String.valueOf(mark) + "/" + String.valueOf(maxQuestion), Toast.LENGTH_LONG).show();
    }

    private void saveContest(int mark) {
        int id = sttContest;
        long timeStamp = new Date().getTime();
        boolean isA1A2 = typeOfContest == 0;
        RealmList<QuestionRealm> listQuestion = new RealmList<>();

        for (int i = 0; i < questions.size(); i++) {
            listQuestion.add(MapperService.getInstance().mapQuestionToQuestionRealm(questions.get(i), answers.get(i)));
        }

        mRealm.beginTransaction();

        HistoryRealm historyRealm = new HistoryRealm();
        historyRealm.setId(id);
        historyRealm.setA1A2(isA1A2);
        historyRealm.setListQuestions(listQuestion);
        mRealm.insertOrUpdate(historyRealm);

        ContestStateRealm contestStateRealm = new ContestStateRealm();
        contestStateRealm.setTimeStamp(timeStamp);
        contestStateRealm.setId(id);
        contestStateRealm.setA1A2(isA1A2);
        contestStateRealm.setPassed(isA1A2 ? mark >= 16 : mark >= 26);
        mRealm.insertOrUpdate(contestStateRealm);

        mRealm.commitTransaction();
        mRealm.close();
    }
}
