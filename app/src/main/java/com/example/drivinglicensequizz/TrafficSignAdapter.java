package com.example.drivinglicensequizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignAdapter.TrafficSignViewHolder> {
    Context context;
    List<TrafficSign> signs;
    OnItemVisible listener;

    public TrafficSignAdapter(Context context, List<TrafficSign> signs, OnItemVisible onItemVisible) {
        this.context = context;
        this.signs = signs;
        this.listener = onItemVisible;
    }

    @NonNull
    @Override
    public TrafficSignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.traffic_sign_row, parent, false);
        return new TrafficSignViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull TrafficSignViewHolder holder, int position) {
        holder.title.setText(signs.get(position).getNoidung());
        holder.image.setImageBitmap(signs.get(position).getBienbao());
        switch (signs.get(position).getLoaibien()) {
            case 1: listener.onVisible("Biển báo nguy hiểm"); break;
            case 2: listener.onVisible("Biển báo cấm"); break;
            case 3: listener.onVisible("Biển báo hiệu lệnh");break;
            case 4: listener.onVisible("Biển báo chỉ dẫn");break;
            case 5: listener.onVisible("Biển báo phụ");break;
            default: listener.onVisible("Bien bao");
        }
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }


    public class TrafficSignViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public TrafficSignViewHolder(@NonNull View itemView) {
           super(itemView);
           title = itemView.findViewById(R.id.title);
           image = itemView.findViewById(R.id.image);
        }
    }

    interface OnItemVisible {
        public void onVisible(String type);
    }
}
