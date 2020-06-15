package com.example.drivinglicensequizz.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drivinglicensequizz.MapperService;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.ContestState;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.entity.ContestStateRealm;
import com.example.drivinglicensequizz.entity.HistoryRealm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class HistoryContestActivity extends AppCompatActivity {

    private ImageButton backButon;
    private ListView listContestHistory;
    private ArrayList<ContestState> contestStates = new ArrayList<>();
    private static final int limit = 5;

    Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_history);
        setupLayout();
        configListView();
        setupAction();
    }

    @Override
    public void finish() {
        super.finish();
        mRealm.close();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void readAllContests() {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<ContestStateRealm> resultAllContests = mRealm.where(ContestStateRealm.class).findAll().sort("saveAt", Sort.DESCENDING);

        if (resultAllContests.size() > limit) {
            ContestStateRealm contestStateRealm = mRealm.where(ContestStateRealm.class).findFirst();
            int idToDelete = contestStateRealm.getId();
            contestStateRealm.deleteFromRealm();

            HistoryRealm historyRealm = mRealm.where(HistoryRealm.class).equalTo("id", idToDelete).findFirst();
            historyRealm.deleteFromRealm();
        }

        for (ContestStateRealm contestStateRealm: resultAllContests) {
            contestStates.add(MapperService.getInstance().mapContestStateRealmToContestState(contestStateRealm));
        }
        mRealm.commitTransaction();
    }

    private void setupLayout() {
        backButon = findViewById(R.id.back_button);
        listContestHistory = findViewById(R.id.listContestHistory);
    }

    private void configListView() {
        readAllContests();
        if (contestStates == null) {
            listContestHistory.setVisibility(View.INVISIBLE);
        } else {
            listContestHistory.setAdapter(new HistoryRowAdapter(this, contestStates));
            listContestHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mRealm.beginTransaction();
                    HistoryRealm historyRealm = mRealm.where(HistoryRealm.class).equalTo("id", contestStates.get(position).getId()).findFirst();
                    mRealm.commitTransaction();
                    Log.d("Đề", historyRealm.getId() + "");
                    ArrayList<Question> historyQuestions = MapperService.getInstance().mapRealmListToArrayList(historyRealm.getListQuestions());
                    Intent intent = new Intent(getApplicationContext(), ShowDetailContestActivity.class);
                    intent.putParcelableArrayListExtra("listQuestionContest", historyQuestions);
                    intent.putExtra("sttContest", historyRealm.getId());
                    startActivity(intent);
                }
            });
        }
    }

    private void setupAction() {
        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
