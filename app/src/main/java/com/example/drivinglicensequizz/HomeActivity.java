package com.example.drivinglicensequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements OptionDialog.CheckTypeContest {

    private ImageButton trafficSign;
    private ImageButton questionButton;
    private ImageButton tipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        trafficSign = findViewById(R.id.traffic_sign);
        trafficSign.setOnClickListener(toTrafficSignActivity);
        questionButton = findViewById(R.id.questions);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialog dialog = new OptionDialog();
                dialog.show(getSupportFragmentManager(), "OptionDialog");
            }
        });
        tipButton = findViewById(R.id.tips);
        tipButton.setOnClickListener(toTipActivity);
    }

    private View.OnClickListener toTipActivity = new View.OnClickListener() {
        public void onClick(View v) {
        Intent homeToTipIntent = new Intent(HomeActivity.this, TheoryTipActivity.class);
        startActivity(homeToTipIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };

    private View.OnClickListener toTrafficSignActivity = new View.OnClickListener() {
        public void onClick(View v) {
        Intent homeIntent = new Intent(HomeActivity.this, TrafficSignActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };
    @Override
    public void sendTypeContest(int type) {
        Intent homeToQuestionIntent = new Intent(HomeActivity.this, QuestionsActivity.class);
        homeToQuestionIntent.putExtra("TypeOfContest", type);
        startActivity(homeToQuestionIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
