package com.example.drivinglicensequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageButton trafficSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        trafficSign = findViewById(R.id.traffic_sign);
        trafficSign.setOnClickListener(toTrafficSignActivity);

    }

    private View.OnClickListener toTrafficSignActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent homeIntent = new Intent(HomeActivity.this, TrafficSignActivity.class);
            startActivity(homeIntent);
        }
    };
}
