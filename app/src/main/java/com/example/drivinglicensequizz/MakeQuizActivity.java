package com.example.drivinglicensequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MakeQuizActivity extends AppCompatActivity implements MakeQuizRowAdapter.ItemClickListener {

    int typeOfContext = 1;
    ImageButton backButton;
    RecyclerView recyclerView;
    MakeQuizRowAdapter makeQuizRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quiz);
        setUpLayout();
        setUpActions();
        setUpData();
    }

    private void setUpLayout() {
        recyclerView = findViewById(R.id.contest_number_recyclerview);
        backButton = findViewById(R.id.back_button_makequizz);
    }

    private void setUpActions() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpData() {
        makeQuizRowAdapter = new MakeQuizRowAdapter(this, this);
        GridLayoutManager layout = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(makeQuizRowAdapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view, int position) {
        Log.d("TYPE", String.valueOf(typeOfContext));
        Log.d("TEST", String.valueOf(position+1));
    }
}
