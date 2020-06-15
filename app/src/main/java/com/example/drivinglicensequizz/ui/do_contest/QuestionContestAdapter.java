package com.example.drivinglicensequizz.ui.do_contest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.MapperService;
import com.example.drivinglicensequizz.ui.QuestionViewHolder;
import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionContestAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    Context context;
    Question question;
    int questionNumber;
    int typeContest;
    boolean [][]yourAnswer;
    boolean isSubmitted;
    boolean fromDatabase;
    List<String> answersSaved = new ArrayList<>();

    public QuestionContestAdapter(Context context, Question question, int questionNumber, int typeContest, boolean isSubmitted, boolean fromDatabase) {
        this.context = context;
        this.question = question;
        this.typeContest = typeContest;
        this.questionNumber = questionNumber;
        this.isSubmitted = isSubmitted;
        this.fromDatabase = fromDatabase;
        yourAnswer = new boolean [typeContest == 0 ? 20 : 30][4];
        for (boolean []eachQuestion : yourAnswer) {
            eachQuestion = new boolean[] {false, false, false, false};
        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_contest_row, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.layoutC.setVisibility(View.VISIBLE);
        holder.layoutD.setVisibility(View.VISIBLE);
        holder.image.setVisibility(View.VISIBLE);
        holder.viewAnswer.setVisibility(isSubmitted ? View.VISIBLE : View.INVISIBLE);

        holder.checkBoxA.setChecked(yourAnswer[questionNumber - 1][0]);
        holder.checkBoxB.setChecked(yourAnswer[questionNumber - 1][1]);
        holder.checkBoxC.setChecked(yourAnswer[questionNumber - 1][2]);
        holder.checkBoxD.setChecked(yourAnswer[questionNumber - 1][3]);

        holder.checkBoxA.setEnabled(!isSubmitted);
        holder.checkBoxB.setEnabled(!isSubmitted);
        holder.checkBoxC.setEnabled(!isSubmitted);
        holder.checkBoxD.setEnabled(!isSubmitted);

        holder.checkBoxA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yourAnswer[questionNumber - 1][0] = isChecked;
            }
        });
        holder.checkBoxB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yourAnswer[questionNumber - 1][1] = isChecked;
            }
        });
        holder.checkBoxC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yourAnswer[questionNumber - 1][2] = isChecked;
            }
        });
        holder.checkBoxD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yourAnswer[questionNumber - 1][3] = isChecked;
            }
        });

        holder.questionNumber.setText(String.valueOf(questionNumber));
        holder.question.setText(question.getCauhoi());
        holder.a.setText(question.getA());
        holder.b.setText(question.getB());
        if (question.getC().isEmpty()) {
            holder.layoutC.setVisibility(View.GONE);
        } else {
            holder.c.setText(question.getC());
        }
        if (question.getD().isEmpty()) {
            holder.layoutD.setVisibility(View.GONE);
        } else {
            holder.d.setText(question.getD());
        }
        if (question.getAnh()==0) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setImageBitmap(question.getBienbao());
        }
        holder.answer.setText("Đáp án: " + question.getDapan());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void reloadData(Question question, int questionNumber) {
        this.question = question;
        this.questionNumber = questionNumber;
        notifyDataSetChanged();
    }

    public void setShowAnswer() {
        this.isSubmitted = true;
        notifyDataSetChanged();
    }

    public void setAnswerHistory(List<String> answers) {
        yourAnswer = MapperService.getInstance().convertListStringToBooleanMatrix(answers);
    }
}
