package com.example.drivinglicensequizz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LyThuyetFragment extends Fragment implements TipAdapter.OnItemVisible {

    private TipAdapter tipAdapter;
    private TipDBHelper tipDBHelper;
    private List<Tip> tips;
    private TextView titleTheory;
    private TextView textType;
    private RecyclerView recyclerView;
    private static LyThuyetFragment shared = new LyThuyetFragment();

    private LyThuyetFragment() {

    }

    public static LyThuyetFragment getInstance() {
        return shared;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lythuyet_tips_fragment, container, false);
        textType = view.findViewById(R.id.textType);
        titleTheory = view.findViewById(R.id.theory_title);
        recyclerView = view.findViewById(R.id.lythuyet_recyclerview);
        tipDBHelper = new TipDBHelper(this.getContext());
        tips = tipDBHelper.getAllTips();
        tipAdapter = new TipAdapter(this.getContext(), tips, this);
        recyclerView.setAdapter(tipAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void onVisible(String type) {
        textType.setText(type);
    }
}
