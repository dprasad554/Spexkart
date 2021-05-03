package com.gsatechworld.spexkart.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gsatechworld.spexkart.R;

public class UploadPrescriptionActivity extends AppCompatActivity {
    Button bn_eyetest;
    Dialog paypopup,viewpopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadprescription);
        bn_eyetest = findViewById(R.id.bn_eyetest);
        bn_eyetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializeAppointmentpopup();
                initializeAppointmentPopup();
            }
        });
    }


    private void InitializeAppointmentpopup() {
        viewpopup = new Dialog(this);
        viewpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewpopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        viewpopup.setContentView(R.layout.popup_appointment);
        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
    }
    private void initializeAppointmentPopup() {
        viewpopup.setContentView(R.layout.popup_appointment);
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();
        Button btnNext = viewpopup.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadPrescriptionActivity.this, CartListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        deletePopup.getWindow().setLayout(width, height);
        viewpopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewpopup.getWindow().setGravity(Gravity.CENTER);

        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();
    }




}