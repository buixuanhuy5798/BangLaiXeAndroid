package com.example.drivinglicensequizz.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivinglicensequizz.R;
import com.example.drivinglicensequizz.data.model.ContestState;

import java.util.List;

public class HistoryRowAdapter extends BaseAdapter {

    List<ContestState> contestStates;
    private static LayoutInflater inflater = null;

    public HistoryRowAdapter(Context context, List<ContestState> contestStates) {
        this.contestStates = contestStates;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contestStates.size();
    }

    @Override
    public Object getItem(int position) {
        return contestStates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contestStates.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.history_row, null);
        }
        TextView sttContest = (TextView) vi.findViewById(R.id.sttContest);
        TextView typeOfContest = (TextView) vi.findViewById(R.id.typeOfContest);
        ImageView contestState = (ImageView) vi.findViewById(R.id.contestState);

        sttContest.setText("Đề số " + String.valueOf(contestStates.get(position).getId()));
        typeOfContest.setText(contestStates.get(position).isA1A2() ? "A1, A2" : "B1, B2");
        contestState.setImageResource(contestStates.get(position).isPassed() ? R.drawable.pass_state : R.drawable.fail_state);
        return vi;
    }
}
