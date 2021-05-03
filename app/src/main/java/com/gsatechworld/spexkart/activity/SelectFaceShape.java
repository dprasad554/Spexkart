package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.FaceshapeAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectFaceShape extends AppCompatActivity {
    RecyclerView daily_recyclerView;
    ArrayList faceshapes = new ArrayList<>(Arrays.asList(R.drawable.face_frame,R.drawable.face_frame,R.drawable.face_frame,
            R.drawable.face_frame,R.drawable.face_frame,R.drawable.face_frame));
    ArrayList glassframes = new ArrayList<>(Arrays.asList(R.drawable.glass_fram,R.drawable.glass_fram,R.drawable.glass_fram,
            R.drawable.glass_fram,R.drawable.glass_fram,R.drawable.glass_fram));
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faceshape);
        setfullscreen();

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectFaceShape.this, SeletctAgeGroup.class);
                startActivity(intent);
                finish();
            }
        });

        //For Costomer review
        daily_recyclerView =(RecyclerView)findViewById(R.id.face_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3,GridLayoutManager.VERTICAL,false);
        daily_recyclerView.setLayoutManager(gridLayoutManager);
        FaceshapeAdapter faceshapeAdapter = new FaceshapeAdapter(this,faceshapes,glassframes);
        daily_recyclerView.setAdapter(faceshapeAdapter);






    }

    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
