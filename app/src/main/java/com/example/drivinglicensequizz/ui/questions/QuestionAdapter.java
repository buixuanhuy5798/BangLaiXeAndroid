package com.example.drivinglicensequizz.ui.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.QuestionViewHolder;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;
import com.example.drivinglicensequizz.data.model.TypeOfContest;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    Context context;
    List<Question> questions;
    int page;
    int typeContest;

    public QuestionAdapter(Context context, List<Question> questions, int page, int typeContest) {
        this.context = context;
        this.questions = questions;
        this.page = page;
        this.typeContest = typeContest;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_row, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.layoutC.setVisibility(View.VISIBLE);
        holder.layoutD.setVisibility(View.VISIBLE);
        holder.image.setVisibility(View.VISIBLE);

        if (typeContest == TypeOfContest.a1a2) {
            // If Type Of Contest is A1A2, have 15 questions per page
            holder.questionNumber.setText(String.valueOf(page * 15 + position + 1));
        } else {
            // If Type Of Contest is B1B2, havw 25 questions per page
            holder.questionNumber.setText(String.valueOf(page * 25 + position + 1));
        }
        holder.question.setText(questions.get(position).getCauhoi());
        holder.a.setText(questions.get(position).getA());
        holder.b.setText(questions.get(position).getB());
        if (questions.get(position).getC().isEmpty()) {
            holder.layoutC.setVisibility(View.GONE);
        } else {
            holder.c.setText(questions.get(position).getC());
        }
        if (questions.get(position).getD().isEmpty()) {
            holder.layoutD.setVisibility(View.GONE);
        } else {
            holder.d.setText(questions.get(position).getD());
        }
        //holder.image.setImageBitmap(questions.get(position).getBienbao());
        if (questions.get(position).getAnh()==0) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setImageBitmap(questions.get(position).getBienbao());
        }
        holder.answer.setText("Đáp án: " + questions.get(position).getDapan());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void reloadData(List<Question> ques, int page) {
        this.questions = ques;
        this.page = page;
        notifyDataSetChanged();
    }
}
