package com.example.drivinglicensequizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
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

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionNumber;
        TextView question;
        ImageView image;
        ConstraintLayout layoutA;
        ConstraintLayout layoutB;
        ConstraintLayout layoutC;
        ConstraintLayout layoutD;
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView answer;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.question_number_tv);
            question = itemView.findViewById(R.id.question_tv);
            a = itemView.findViewById(R.id.a_tv);
            b = itemView.findViewById(R.id.b_tv);
            c = itemView.findViewById(R.id.c_tv);
            d = itemView.findViewById(R.id.d_tv);
            layoutA = itemView.findViewById(R.id.layout_a_tv);
            layoutB = itemView.findViewById(R.id.layout_b_tv);
            layoutC = itemView.findViewById(R.id.layout_c_tv);
            layoutD = itemView.findViewById(R.id.layout_d_tv);
            answer = itemView.findViewById(R.id.answer_tv);
            image = itemView.findViewById(R.id.image_question);
        }
    }
}
