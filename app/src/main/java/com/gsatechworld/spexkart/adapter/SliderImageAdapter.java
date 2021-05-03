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

public class SliderImageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    int slide[];

    //Constructor Created

    public SliderImageAdapter(Context context, int[] slide) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.slide = slide;
    }

    @Override
    public int getCount() {

        return slide.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);  //object parameter
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //LayoutInflater to use image
        View itemView = layoutInflater.inflate(R.layout.layout_imageslider,container, false);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.myImageView);
        imageView.setImageResource(slide[position]);
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
