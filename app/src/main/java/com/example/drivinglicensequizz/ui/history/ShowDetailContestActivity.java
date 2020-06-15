package com.example.drivinglicensequizz.ui.history;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.ui.do_contest.QuestionContestAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowDetailContestActivity extends AppCompatActivity {

    private Button nextQuestion, previousQuestion;
    private ImageButton backButton;
    private RecyclerView recyclerViewContest;
    private TextView numberOfQuestion, titleContest;

    ArrayList<Question> questions;
    Question dataOnRyclerView;
    QuestionContestAdapter questionContestAdapter;
    int numberQuestion = 1;
    int sttContest;
    int maxQuestion;
    List<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail_contest);
        setUpLayout();
        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("listQuestionContest");
        sttContest = intent.getIntExtra("sttContest", 1);
        maxQuestion = questions.size();
        setupData();
        setUpAction();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void setUpLayout() {
        backButton = findViewById(R.id.back_button_contest);
        nextQuestion = findViewById(R.id.next_button);
        previousQuestion = findViewById(R.id.previous_button);
        recyclerViewContest = findViewById(R.id.contest_recycleView);
        numberOfQuestion = findViewById(R.id.numberOfQuestion);
        titleContest = findViewById(R.id.titleContest);
    }

    private void setupData() {
        titleContest.setText("Đề " + String.valueOf(sttContest));
        addImageToArray();
        dataOnRyclerView = questions.get(0);
        numberOfQuestion.setText("Câu " + String.valueOf(numberQuestion) + "/" + String.valueOf(maxQuestion));
        questionContestAdapter = new QuestionContestAdapter(this, dataOnRyclerView, 1, questions.size() == 20 ? 0 : 1, true);
        for (Question question: questions) {
            answers.add(question.getAnwser());
        }
        questionContestAdapter.setAnswerHistory(answers);
        recyclerViewContest.setAdapter(questionContestAdapter);
        recyclerViewContest.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpAction() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
