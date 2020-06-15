package com.example.drivinglicensequizz.ui.tips.tips_theory;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.ui.custom_ui.CustomViewPager;

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
                thuchanhButton.setBackgroundColor(Color.parseColor("#FFAAAAAA"));
            }
        });

        thuchanhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
                thuchanhButton.setBackgroundColor(Color.parseColor("#FFFDC313"));
                lythuyetButton.setBackgroundColor(Color.parseColor("#FFAAAAAA"));
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}


