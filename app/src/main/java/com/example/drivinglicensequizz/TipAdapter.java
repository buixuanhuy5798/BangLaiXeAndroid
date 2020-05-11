package com.example.drivinglicensequizz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.TipViewHolder> {

    Context context;
    List<Tip> tips;
    OnItemVisible listener;

    public TipAdapter(Context context, List<Tip> tips, OnItemVisible listener) {
        this.context = context;
        this.tips = tips;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tips_row, parent, false);
        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
        holder.content.setText(tips.get(position).getName());
        switch (tips.get(position).getLoai()) {
            case 0: listener.onVisible("MẸO CÂU HỎI LÝ THUYẾT"); break;
            case 1: listener.onVisible("MẸO CÂU HỎI BIỂN BÁO"); break;
            case 2: listener.onVisible("MẸO CÂU HỎI SA HÌNH");break;
            default: listener.onVisible("Bien bao");
        }
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public class TipViewHolder extends RecyclerView.ViewHolder {
        TextView content;

        public TipViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.theory_title);
        }
    }

    interface OnItemVisible {
        public void onVisible(String type);
    }
}
