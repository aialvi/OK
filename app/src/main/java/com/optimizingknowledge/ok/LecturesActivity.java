package com.optimizingknowledge.ok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LecturesActivity extends AppCompatActivity {

    private RecyclerView LecRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);


        LecRecyclerView = findViewById(R.id.LecturesRecyclerViewId);
        LecRecyclerView.setHasFixedSize(true);

    }
}
