package com.example.drivinglicensequizz;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class ImagePanelFragment extends Fragment {

    private String name;
    private String title;

    public ImagePanelFragment(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_title, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = view.findViewById(R.id.image_panel);
        TextView titleTv = view.findViewById(R.id.text_title);
        try {
            image.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open(name)));
            titleTv.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
