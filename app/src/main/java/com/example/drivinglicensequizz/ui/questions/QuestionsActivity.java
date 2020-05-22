package com.example.drivinglicensequizz.ui.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.data.model.TypeOfContest;
import com.example.drivinglicensequizz.data.source.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    DatabaseHelper trafficSignsHelper = new DatabaseHelper(this);
    List<Question> questions;
    List<List<Question>> questionsPerPage = new ArrayList<>();
    List<Question> dataOnRyclerView = new ArrayList<>();
    QuestionAdapter questionAdapter;

    int typeOfContext = 1;
    int maxPage = 10;
    int pageNumber = 1;

    ImageButton backButton;
    TextView titleTextView;
    TextView countOfPagesTextView, pageTextView;
    Button peviousPageButton, nextPageButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setUpLayout();
        setUpData();
        setUpAction();
    }

    private void setUpAction() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        peviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePageNumber(false);
            }
        });
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePageNumber(true);
            }
        });
    }

    private void setUpLayout() {
        backButton = findViewById(R.id.back_button_questions);
        peviousPageButton = findViewById(R.id.previous_button);
        nextPageButton = findViewById(R.id.next_button);
        titleTextView = findViewById(R.id.title_textView);
        countOfPagesTextView = findViewById(R.id.count_of_pages_tv);
        pageTextView = findViewById(R.id.page_tv);
        recyclerView = findViewById(R.id.question_recycleView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private List<List<Question>> createQuestiosnPerPage(List<Question> questions) {
        List<List<Question>> quesPerPage = new ArrayList<>();
        if (typeOfContext == TypeOfContest.a1a2) {
            for (int i = 0; i <= 8; i++) {
                List<Question> ques = new ArrayList<>();
                for (int k = i*15; k < (i+1) * 15; k++) {
                    ques.add(questions.get(k));
                }
                quesPerPage.add(ques);
            }
            List<Question> ques = new ArrayList<>();
            for (int i = 135; i <= 150; i++) {
                ques.add(questions.get(i));
            }
            quesPerPage.add(ques);
        } else {
            for (int i = 0; i<=17; i++) {
                List<Question> ques = new ArrayList<>();
                for (int k = i*25; k < (i+1)*25; k++) {
                    ques.add(questions.get(k));
                }
                quesPerPage.add(ques);
            }
        }
        return quesPerPage;
    }

    private void updatePageNumber(Boolean nextPage) {
        if (nextPage) {
            if (pageNumber < maxPage) {
                pageNumber += 1;
                dataOnRyclerView = questionsPerPage.get(pageNumber-1);
                questionAdapter.reloadData(dataOnRyclerView, pageNumber - 1);
            }
        } else {
            if (pageNumber > 1) {
                pageNumber -= 1;
                dataOnRyclerView = questionsPerPage.get(pageNumber - 1);
                questionAdapter.reloadData(dataOnRyclerView,pageNumber-1);
            }
        }
        Log.d("COUNT", String.valueOf(questionsPerPage.get(pageNumber-1).size()));
        pageTextView.setText(String.valueOf(pageNumber));
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

    private void setUpData() {
        Intent intent = getIntent();
        typeOfContext = intent.getIntExtra("TypeOfContest", 1);
        if (typeOfContext == TypeOfContest.a1a2) {
            questions = trafficSignsHelper.getAllQuestionsA1A2(TypeOfContest.a1a2);
            addImageToArray();
            questionsPerPage = createQuestiosnPerPage(questions);
            titleTextView.setText("Lý thuyết A1,A2");
            maxPage = 10;
            countOfPagesTextView.setText("/" + String.valueOf(maxPage));
        } else {
            questions = trafficSignsHelper.getAllQuestionsA1A2(TypeOfContest.b1b2);
            addImageToArray();
            questionsPerPage = createQuestiosnPerPage(questions);
            titleTextView.setText("Lý thuyết B1,B2");
            maxPage = 18;
            countOfPagesTextView.setText("/" + String.valueOf(maxPage));
        }
        dataOnRyclerView = questionsPerPage.get(0);
        questionAdapter = new QuestionAdapter(this, dataOnRyclerView,0, typeOfContext);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
