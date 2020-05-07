package com.example.drivinglicensequizz;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class TheoryTipActivity extends AppCompatActivity {

    private ImageButton backButton;
    private CustomViewPager vp;
    private Button lythuyetButton;
    private Button thuchanhButton;
    private FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        vp = findViewById(R.id.vp_tips);
        backButton = findViewById(R.id.back_button_tip);
        lythuyetButton = findViewById(R.id.ly_thuyet_button);
        thuchanhButton = findViewById(R.id.thuc_hanh_button);

        fragmentPagerAdapter = new SelectModeAdapter(getSupportFragmentManager());
        vp.setAdapter(fragmentPagerAdapter);
        vp.disableScroll(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lythuyetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
                lythuyetButton.setBackgroundColor(Color.parseColor("#FFFDC313"));
                thuchanhButton.setBackgroundResource(android.R.drawable.btn_default);
            }
        });

        thuchanhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
                thuchanhButton.setBackgroundColor(Color.parseColor("#FFFDC313"));
                lythuyetButton.setBackgroundResource(android.R.drawable.btn_default);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}


