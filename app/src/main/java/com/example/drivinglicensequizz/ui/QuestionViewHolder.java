package com.example.drivinglicensequizz.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
    import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.R;

public class QuestionViewHolder extends RecyclerView.ViewHolder {
    public TextView questionNumber;
    public TextView question;
    public ImageView image;
    public ConstraintLayout layoutA;
    public ConstraintLayout layoutB;
    public ConstraintLayout layoutC;
    public ConstraintLayout layoutD;
    public TextView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public CheckBox checkBoxA;
    public CheckBox checkBoxB;
    public CheckBox checkBoxC;
    public CheckBox checkBoxD;
    public TextView answer;
    public ConstraintLayout viewAnswer;

    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        questionNumber = itemView.findViewById(R.id.question_number_tv);
        question = itemView.findViewById(R.id.question_tv);
        a = itemView.findViewById(R.id.a_tv);
        b = itemView.findViewById(R.id.b_tv);
        c = itemView.findViewById(R.id.c_tv);
        d = itemView.findViewById(R.id.d_tv);
        checkBoxA = itemView.findViewById(R.id.checkboxA);
        checkBoxB = itemView.findViewById(R.id.checkboxB);
        checkBoxC = itemView.findViewById(R.id.checkboxC);
        checkBoxD = itemView.findViewById(R.id.checkboxD);
        layoutA = itemView.findViewById(R.id.layout_a_tv);
        layoutB = itemView.findViewById(R.id.layout_b_tv);
        layoutC = itemView.findViewById(R.id.layout_c_tv);
        layoutD = itemView.findViewById(R.id.layout_d_tv);
        answer = itemView.findViewById(R.id.answer_tv);
        image = itemView.findViewById(R.id.image_question);
        viewAnswer = itemView.findViewById(R.id.viewShowRightAnswer);
    }
}
