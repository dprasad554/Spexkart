package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gsatechworld.spexkart.R;

import java.util.ArrayList;

public class HomeBannerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<Integer> iplImages;

    //Constructor Created

    public HomeBannerAdapter(Context context, ArrayList<Integer> iplImages) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.iplImages = iplImages;
    }

    @Override
    public int getCount() {
        return iplImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);  //object parameter
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.adapter_home_banner,container, false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.iv_category);
        imageView.setImageResource(iplImages.get(position));
        container.addView(itemView,0);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //container.removeView((View) object);
        ViewPager viewPager = (ViewPager)container;
        View view = (View)object;
        viewPager.removeView(view);
    }
}

