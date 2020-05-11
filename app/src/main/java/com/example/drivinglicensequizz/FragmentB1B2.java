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

public class FragmentB1B2 extends Fragment {

    private Button kinhNghiemB1B2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b1_b2, container, false);
        kinhNghiemB1B2 = view.findViewById(R.id.kinhNghiemThiB1B2);
        kinhNghiemB1B2.setOnClickListener(toKinhNghiemB1B2);
        return view;
    }

    private View.OnClickListener toKinhNghiemB1B2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent kinhNghiemB1B2Intent = new Intent(getActivity(), KinhNghiemB1B2.class);
            startActivity(kinhNghiemB1B2Intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };
}
