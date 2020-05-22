package com.example.drivinglicensequizz.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.ui.home_screen.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private static int SLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toHomeScreen();
            }
        },SLASH_TIME_OUT);
    }

    private void toHomeScreen() {
        Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }
}
