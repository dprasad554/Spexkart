package com.gsatechworld.spexkart.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.FaceshapeAdapter;
import com.gsatechworld.spexkart.adapter.GlassAdapter;
import com.gsatechworld.spexkart.adapter.HomeBannerAdapter;
import com.gsatechworld.spexkart.adapter.ProductAdapter;
import com.gsatechworld.spexkart.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    RecyclerView glass_recyclerView,type_recyclerView,product_recyclerView;
    ArrayList glassframes = new ArrayList<>(Arrays.asList(R.drawable.sp_image_one,R.drawable.sp_image_two,R.drawable.sp_image_three,
            R.drawable.sp_image_four,R.drawable.sp_image_five,R.drawable.sp_image_one));
    ArrayList agegroup = new ArrayList<>(Arrays.asList("Computer\nglasses","Color Blink\nGlasses","Eyeglasses","Sunglasses","Power\nGlasses"));
    ViewPager viewpager_top_slider;
    ArrayList banner_images = new ArrayList<>(Arrays.asList(R.drawable.banner,R.drawable.banner,R.drawable.banner,R.drawable.banner));
    int currentPage = 0;
    final long DELAY_MS = 100;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    Timer timer;
    ArrayList type_glass = new ArrayList<>(Arrays.asList(R.drawable.sp_one,R.drawable.sp_one,R.drawable.sp_one));
    ArrayList type = new ArrayList<>(Arrays.asList("MEN eYEGLASSES","wOMEN eYEGLASSES","Best selling"));
    ArrayList product_image = new ArrayList<>(Arrays.asList(R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,
            R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image));

    public HomeFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //For Costomer review
        glass_recyclerView =(RecyclerView)view.findViewById(R.id.glass_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        glass_recyclerView.setLayoutManager(linearLayoutManager);
        GlassAdapter glassAdapter = new GlassAdapter(getActivity(),glassframes,agegroup);
        glass_recyclerView.setAdapter(glassAdapter);

        type_recyclerView =(RecyclerView)view.findViewById(R.id.type_recyclerView);
        LinearLayoutManager typeLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        type_recyclerView.setLayoutManager(typeLayoutManager);
        TypeAdapter typeAdapter = new TypeAdapter(getActivity(),type_glass,type);
        type_recyclerView.setAdapter(typeAdapter);

        product_recyclerView =(RecyclerView)view.findViewById(R.id.product_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL,false);
        product_recyclerView.setLayoutManager(gridLayoutManager);
        ProductAdapter productAdapter = new ProductAdapter(getActivity(),product_image,type);
        product_recyclerView.setAdapter(productAdapter);

        viewpager_top_slider = view.findViewById(R.id.viewpager_top_slider);
        HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(getActivity(),banner_images);
        viewpager_top_slider.setAdapter(homeBannerAdapter);

        final Handler handler_top = new Handler();
        final Runnable Update_top = new Runnable() {
            public void run() {
                if (currentPage == banner_images.size()) {
                    currentPage = 0;
                }
                viewpager_top_slider.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler_top.post(Update_top);
            }
        }, DELAY_MS, PERIOD_MS);


        return view;
    }


}