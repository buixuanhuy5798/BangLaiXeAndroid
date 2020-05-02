package com.example.drivinglicensequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView titleTextView;
    int typeOfContext = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        backButton = findViewById(R.id.back_button_questions);
        titleTextView = findViewById(R.id.title_textView);
        Intent intent = getIntent();
        typeOfContext = intent.getIntExtra("TypeOfContest", 1);
        setTitle();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void setTitle() {
        if (typeOfContext == 0) {
            titleTextView.setText("Lý thuyết A1,A2");
        } else {
            titleTextView.setText("Lý thuyết B1,B2");
        }
    }
}
