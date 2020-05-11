package com.example.drivinglicensequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OptionDialog.CheckTypeContest {

    private ImageButton trafficSign;
    private ImageButton questionButton;
    private FragmentStatePagerAdapter pagerAdapter;
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
        setUpHomePageView();
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

    private void setUpHomePageView() {
        ArrayList<String> listImage = new ArrayList<String>() {
            {
                add("hoc_lai_xe_image.jpg");
                add("ly_thuyet_image.jpg");
                add("bien_bao_image.jpg");
                add("sa_hinh_image.jpeg");
            }
        };

        ArrayList<String> listTitle = new ArrayList<String>() {
            {
                add("Thi thử các đề giống đề thi thật");
                add("Học lý thuyết thi lái xe A1, A2, B1, B2");
                add("Ông tập các loại biển báo");
                add("Cáo mẹo thi bằng lái xe");
            }
        };

        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(4);
        for(int i = 0; i<4; i++) {
            Fragment fragment = new ImagePanelFragment(listImage.get(i), listTitle.get(i));
            fragmentList.add(fragment);
        }
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 0, fragmentList);
        ViewPager pager = findViewById(R.id.pagerTitle);
        pager.setAdapter(pagerAdapter);
    }
}
