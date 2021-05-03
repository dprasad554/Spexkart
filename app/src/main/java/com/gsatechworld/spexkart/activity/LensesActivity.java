package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.fragment.EyeglasessLenses;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LensesActivity extends AppCompatActivity {
    TextView tv_create;
    Button btnNext;
    ViewPager Team_viewpager;
    ArrayList lensdeatils = new ArrayList<>(Arrays.asList("Thin Blue Light Blocker Lenses","Premium anti glare lanses with UV protection",
            "Photochromatic & blue light blocker", "Photochromatic lenses", "Color blind lenses","Blue light blocker lenses","Unbreakable Blue light blocker lenses","Thinnest lenses(1.74 index)"));
    TabLayout tabs;
    SpringDotsIndicator dot1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenses);
        Team_viewpager = findViewById(R.id.Team_viewpager);
        setupViewPager(Team_viewpager);
        dot1 = findViewById(R.id.dot1);
        dot1.setViewPager(Team_viewpager);


    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterPager adapter = new AdapterPager(getSupportFragmentManager());
        try {
            for(int j=0; j<lensdeatils.size(); j++){

                adapter.addFragment(new EyeglasessLenses().getInstance((String) lensdeatils.get(j)), (String) lensdeatils.get(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }

    // pager adapter
    static class AdapterPager extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public AdapterPager(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}