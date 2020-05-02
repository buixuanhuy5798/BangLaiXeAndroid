package com.example.drivinglicensequizz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class OptionDialog extends DialogFragment {
    private Button a1Buton;
    private Button b1Button;
    private Button cancelButton;

    public interface CheckTypeContest {
        void sendTypeContest(int type);
    }

    CheckTypeContest checkTypeContest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.option_dialog, container,false);
        cancelButton = view.findViewById(R.id.cancel_button_dialog);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        a1Buton = view.findViewById(R.id.a1_button_dialog);
        a1Buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTypeContest.sendTypeContest(TypeOfContest.a1a2);
                getDialog().dismiss();
            }
        });
        b1Button = view.findViewById(R.id.b1_button_dialog);
        b1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTypeContest.sendTypeContest(TypeOfContest.b1b2);
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        checkTypeContest = (CheckTypeContest) getActivity();
    }
}
