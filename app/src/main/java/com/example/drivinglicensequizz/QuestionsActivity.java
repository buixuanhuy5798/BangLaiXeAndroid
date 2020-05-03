package com.example.drivinglicensequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView titleTextView;
    TextView countOfPagesTextView, pageTextView;
    Button peviousPageButton, nextPageButton;
    int typeOfContext = 1;
    int maxPage = 10;
    int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        backButton = findViewById(R.id.back_button_questions);
        peviousPageButton = findViewById(R.id.previous_button);
        nextPageButton = findViewById(R.id.next_button);
        titleTextView = findViewById(R.id.title_textView);
        countOfPagesTextView = findViewById(R.id.count_of_pages_tv);
        pageTextView = findViewById(R.id.page_tv);
        Intent intent = getIntent();
        typeOfContext = intent.getIntExtra("TypeOfContest", 1);
        setTitle();
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void updatePageNumber(Boolean nextPage) {
        if (nextPage) {
            if (pageNumber < maxPage) {
                pageNumber += 1;
            }
        } else {
            if (pageNumber > 1) {
                pageNumber -= 1;
            }
        }
        pageTextView.setText(String.valueOf(pageNumber));
    }

    private void setTitle() {
        if (typeOfContext == 0) {
            titleTextView.setText("Lý thuyết A1,A2");
            maxPage = 10;
            countOfPagesTextView.setText("/" + String.valueOf(maxPage));
        } else {
            titleTextView.setText("Lý thuyết B1,B2");
            maxPage = 18;
            countOfPagesTextView.setText("/" + String.valueOf(maxPage));
        }
    }
}
