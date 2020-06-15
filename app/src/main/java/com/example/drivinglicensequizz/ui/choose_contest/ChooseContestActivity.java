package com.example.drivinglicensequizz.ui.choose_contest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.drivinglicensequizz.MapperService;
import com.example.drivinglicensequizz.data.model.ContestStatus;
import com.example.drivinglicensequizz.entity.ContestStatusRealm;
import com.example.drivinglicensequizz.ui.do_contest.DoContestActivity;
import com.example.drivinglicensequizz.ui.history.HistoryContestActivity;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.data.source.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChooseContestActivity extends AppCompatActivity implements ChooseContestRowAdapter.ItemClickListener {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    int typeOfContest = 0;
    ChooseContestUseCase useCase = new ChooseContestUseCase();
    ImageButton backButton;
    RecyclerView recyclerView;
    ChooseContestRowAdapter chooseContestRowAdapter;
    List<ArrayList<Question>> questionsPerContest = new ArrayList<>();
    TextView titleScreen;
    Button showHistory;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contest);
        setUpLayout();
        setUpActions();
        setUpData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        chooseContestRowAdapter.setContestStatuses(readAllStatusContests());
    }

    private void setUpLayout() {
        titleScreen = findViewById(R.id.title_textView);
        recyclerView = findViewById(R.id.contest_number_recyclerview);
        backButton = findViewById(R.id.back_button_makequizz);
        showHistory = findViewById(R.id.showHistory);
    }

    private void setUpActions() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseContestActivity.this, HistoryContestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void setUpData() {
        Intent intent = getIntent();
        typeOfContest = intent.getIntExtra("TypeOfContest", 1);
        titleScreen.setText(typeOfContest == 0 ? "Làm đề thi A1-A2" : "Làm đề thi B1-B2");
        chooseContestRowAdapter = new ChooseContestRowAdapter(this, this, readAllStatusContests(), typeOfContest);
        GridLayoutManager layout = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(chooseContestRowAdapter);
        questionsPerContest = useCase.setQuestionForEachContest(typeOfContest, databaseHelper.getAllQuestions(typeOfContest));
    }

    private List<ContestStatus> readAllStatusContests() {
        List<ContestStatus> contestStatuses = new ArrayList<>();
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<ContestStatusRealm> contestStatusRealms = mRealm.where(ContestStatusRealm.class).findAll();
        Log.d("status", contestStatusRealms.size() + "");
        for (ContestStatusRealm contestStatusRealm: contestStatusRealms) {
            contestStatuses.add(MapperService.getInstance().mapContestStatusRealmToContestStatus(contestStatusRealm));
        }
        mRealm.commitTransaction();
        mRealm.close();
        return contestStatuses;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, DoContestActivity.class);
        intent.putParcelableArrayListExtra("listQuestionContest", questionsPerContest.get(position));
        intent.putExtra("typeOfContest", typeOfContest);
        intent.putExtra("numberContest", position + 1);
        startActivity(intent);
        Log.d("TYPE", String.valueOf(typeOfContest));
        Log.d("TEST", String.valueOf(position+1));
    }
}
