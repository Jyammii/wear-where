package com.example.closetifiy_finalproject;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class OutfitDetails extends AppCompatActivity {

    private ImageView canvasImageView;
    private List<Uri> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outfitdetails);

        canvasImageView = findViewById(R.id.canvasfits);

        selectedItems = getIntent().getParcelableArrayListExtra("selectedItems");

        // Assuming you want to display the first selected item in the ImageView
        if (selectedItems != null && !selectedItems.isEmpty()) {
            canvasImageView.setImageURI(selectedItems.get(0));
        }
    }
}