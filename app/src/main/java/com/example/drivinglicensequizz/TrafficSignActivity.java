package com.example.drivinglicensequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class TrafficSignActivity extends AppCompatActivity implements TrafficSignAdapter.OnItemVisible {

    TraficSignDBHelper trafficSignsHelper = new TraficSignDBHelper(this);
    List<TrafficSign> signs;
    RecyclerView recyclerView;
    TextView textType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_sign);
        recyclerView = findViewById(R.id.traffic_sign_recyclerview);
        signs = trafficSignsHelper.getAllTrafficSigns();
        addImageToArray();
        TrafficSignAdapter trafficSignAdapter = new TrafficSignAdapter(this, signs, this);
        recyclerView.setAdapter(trafficSignAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textType = findViewById(R.id.textType);
    }

    private void addImageToArray() {
        //List<Image> images;
        for(int i = 0; i < signs.size(); i++) {
            try {
                Bitmap bitmap =  BitmapFactory.decodeStream(getAssets().open(String.valueOf(signs.get(i).getAnh())+".png"));
                signs.get(i).setBienbao(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onVisible(String type) {
        textType.setText(type);
    }
}
