package com.example.drivinglicensequizz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ThucHanhFragment extends Fragment implements View.OnClickListener {

    private static ThucHanhFragment shared = new ThucHanhFragment();
    public static final String TAG_A1_A2 = "A1,A2";
    public static final String TAG_B1_B2 = "B1,B2";
    private String mCurrentFragmentTag = TAG_A1_A2;
    private Button mButtonA1A2, mButtonB1B2;

    private ThucHanhFragment() {

    }

    public static ThucHanhFragment getInstance() {
        return shared;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        mButtonA1A2 = view.findViewById(R.id.button_A1_A2);
        mButtonB1B2 = view.findViewById(R.id.button_B1_B2);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuc_hanh, container, false);
        initView(view);
        initListener();
        changeFragment();
        return view;
    }

    private void initListener() {
        mButtonA1A2.setOnClickListener(this);
        mButtonB1B2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_A1_A2:
                mCurrentFragmentTag = TAG_A1_A2;
                mButtonA1A2.setBackgroundColor(getResources().getColor(R.color.statusBarColor));
                mButtonB1B2.setBackgroundResource(android.R.drawable.btn_default);
                break;
            case R.id.button_B1_B2:
                mCurrentFragmentTag = TAG_B1_B2;
                mButtonB1B2.setBackgroundColor(getResources().getColor(R.color.statusBarColor));
                mButtonA1A2.setBackgroundResource(android.R.drawable.btn_default);
                break;
        }
        changeFragment();
    }

    private void changeFragment() {
        Fragment fragment = null;
        switch (mCurrentFragmentTag) {
            case TAG_A1_A2:
                fragment = new FragmentA1A2();
                break;
            case TAG_B1_B2:
                fragment = new FragmentB1B2();
                break;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, mCurrentFragmentTag);
        fragmentTransaction.commit();
    }
}
