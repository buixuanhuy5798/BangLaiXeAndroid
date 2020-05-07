package com.example.drivinglicensequizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA1A2 extends Fragment {

    private Button kinhNghiemA1A2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a1_a2, container, false);
        kinhNghiemA1A2 = view.findViewById(R.id.kinhNghiemThiA1A2);
        kinhNghiemA1A2.setOnClickListener(toKinhNghiemA1A2);
        return view;
    }

    private View.OnClickListener toKinhNghiemA1A2 = new View.OnClickListener() {
        public void onClick(View v) {
        Intent kinhNghiemA1A2Intent = new Intent(getActivity(), KinhNghiemA1A2.class);
        startActivity(kinhNghiemA1A2Intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };
}
