package com.example.drivinglicensequizz.ui.choose_contest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.ContestStatus;
import com.example.drivinglicensequizz.entity.ContestStatusRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChooseContestRowAdapter extends RecyclerView.Adapter<ChooseContestRowAdapter.MakeQuizViewHodler> {

    Context context;
    ItemClickListener itemClickListener;
    List<ContestStatus> contestStatuses;
    int typeOfContest;

    public ChooseContestRowAdapter(Context context, ItemClickListener itemClickListener, List<ContestStatus> contestStatuses, int typeOfContest) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.contestStatuses = contestStatuses;
        this.typeOfContest = typeOfContest;
    }

    public void setContestStatuses(List<ContestStatus> contestStatuses) {
        this.contestStatuses = contestStatuses;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MakeQuizViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.choose_contest_row, parent, false);
        return new MakeQuizViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakeQuizViewHodler holder, int position) {
        holder.numberOfContest.setText(String.valueOf(position+1));
        if (position < 14) {
            if (position < contestStatuses.size()) {
                if (contestStatuses.get(position).getTypeOfContest() == this.typeOfContest) {
                    switch (contestStatuses.get(position).getStatus()) {
                        case "UNTESTED":
                            holder.state.setImageResource(R.drawable.untest_state);
                            break;
                        case "PASSED":
                            holder.state.setImageResource(R.drawable.pass_state);
                            break;
                        case "FAILED":
                            holder.state.setImageResource(R.drawable.fail_state);
                            break;
                    }
                } else {
                    holder.state.setImageResource(R.drawable.untest_state);
                }
            } else {
                holder.state.setImageResource(R.drawable.untest_state);
            }
            holder.numberOfContest.setVisibility(View.VISIBLE);
            holder.state.setVisibility(View.VISIBLE);
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
            suffle = itemView.findViewById(R.id.choose_contest_suffle_im);
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
