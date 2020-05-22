package com.example.drivinglicensequizz.ui.home_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.drivinglicensequizz.ImagePanelFragment;
import com.example.drivinglicensequizz.ui.choose_contest.ChooseContestActivity;
import com.example.drivinglicensequizz.ui.custom_ui.OptionDialog;
import com.example.drivinglicensequizz.ui.questions.QuestionsActivity;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.ui.tips.tips_theory.TheoryTipActivity;
import com.example.drivinglicensequizz.ui.traffic_sign.TrafficSignActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements OptionDialog.CheckTypeContest {

    private ImageButton trafficSign;
    private ImageButton questionButton;
    private ImageButton makeQuizzButton;
    private FragmentStatePagerAdapter pagerAdapter;
    private ImageButton tipButton;

    int checkButtonTapped = 0;

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
                checkButtonTapped = 1;
                OptionDialog dialog = new OptionDialog();
                dialog.show(getSupportFragmentManager(), "OptionDialog");
            }
        });
        makeQuizzButton = findViewById(R.id.make_quizz_button);
        makeQuizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButtonTapped = 2;
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
        Intent intent;
        if (checkButtonTapped == 1) {
            intent = new Intent(HomeActivity.this, QuestionsActivity.class);
        } else {
            intent = new Intent(HomeActivity.this, ChooseContestActivity.class);
        }
        intent.putExtra("TypeOfContest", type);
        startActivity(intent);
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
        pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), 0, fragmentList);
        ViewPager pager = findViewById(R.id.pagerTitle);
        pager.setAdapter(pagerAdapter);
    }
}
