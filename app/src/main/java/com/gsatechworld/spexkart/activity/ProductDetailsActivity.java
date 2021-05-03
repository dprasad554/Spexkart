package com.gsatechworld.spexkart.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.FaceshapeAdapter;
import com.gsatechworld.spexkart.adapter.LensesCategoryAdapter;
import com.gsatechworld.spexkart.adapter.SectionsPagerAdapter;
import com.gsatechworld.spexkart.adapter.SimilarAdapter;
import com.gsatechworld.spexkart.adapter.SliderImageAdapter;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDetailsActivity extends AppCompatActivity {

    ViewPager viewPager;
    SpringDotsIndicator dot1;
    int slide[] = {R.drawable.details_image, R.drawable.details_image, R.drawable.details_image};
    Timer timer1;
    SliderImageAdapter sliderImageAdapter;
    RecyclerView similar_recyclerView;
    ArrayList faceshapes = new ArrayList<>(Arrays.asList(R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,
            R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image));
    ArrayList glassframes = new ArrayList<>(Arrays.asList(R.drawable.glass_fram,R.drawable.glass_fram,R.drawable.glass_fram,
            R.drawable.glass_fram,R.drawable.glass_fram,R.drawable.glass_fram));
    Button select_lenses;
    Dialog paypopup,viewpopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        dot1 = findViewById(R.id.dot1);
        viewPager = (ViewPager) findViewById(R.id.pager);

        //For First Slider Timmer
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % slide.length);
                    }
                });
            }
        };

        timer1 = new Timer();
        timer1.schedule(timerTask1, 3400, 3400);

        //For First Slide
        sliderImageAdapter = new SliderImageAdapter(this, slide);
        viewPager.setAdapter(sliderImageAdapter);
        dot1.setViewPager(viewPager);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));

        //For Costomer review
        similar_recyclerView =(RecyclerView)findViewById(R.id.similar_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,GridLayoutManager.HORIZONTAL,false);
        similar_recyclerView.setLayoutManager(linearLayoutManager);
        SimilarAdapter similarAdapter = new SimilarAdapter(this,faceshapes,glassframes);
        similar_recyclerView.setAdapter(similarAdapter);

        select_lenses = findViewById(R.id.select_lences);
        select_lenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializeSelectLensespopup();
                initializeSelectLensesPopup();
            }
        });
    }

    private void InitializeSelectLensespopup() {
        viewpopup = new Dialog(this);
        viewpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewpopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        viewpopup.setContentView(R.layout.popup_select_lenses);
        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
    }
    private void initializeSelectLensesPopup() {
        viewpopup.setContentView(R.layout.popup_select_lenses);
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();

        RecyclerView lenses_category = viewpopup.findViewById(R.id.lenses_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lenses_category.setLayoutManager(linearLayoutManager);
        LensesCategoryAdapter lensesCategoryAdapter = new LensesCategoryAdapter(this);
        lenses_category.setAdapter(lensesCategoryAdapter);

        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        deletePopup.getWindow().setLayout(width, height);
        viewpopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewpopup.getWindow().setGravity(Gravity.BOTTOM);

        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();
    }
}