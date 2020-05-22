package com.example.drivinglicensequizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MakeQuizRowAdapter extends RecyclerView.Adapter<MakeQuizRowAdapter.MakeQuizViewHodler> {
    Context context;
    ItemClickListener itemClickListener;

    public MakeQuizRowAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MakeQuizViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.make_quiz_row, parent, false);
        return new MakeQuizViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakeQuizViewHodler holder, int position) {
        holder.numberOfContest.setText(String.valueOf(position+1));
        if (position != 14) {
            holder.suffle.setVisibility(View.GONE);
        } else {
            holder.numberOfContest.setVisibility(View.GONE);
            holder.state.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MakeQuizViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView numberOfContest;
        ImageView suffle;
        ImageView state;

        public MakeQuizViewHodler(@NonNull View itemView) {
            super(itemView);
            numberOfContest = itemView.findViewById(R.id.text_test_number);
            suffle = itemView.findViewById(R.id.make_quizz_row_suffle_im);
            state = itemView.findViewById(R.id.state_im);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }


    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}
