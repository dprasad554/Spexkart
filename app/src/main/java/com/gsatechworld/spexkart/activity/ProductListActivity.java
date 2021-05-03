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
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.FaceshapeAdapter;
import com.gsatechworld.spexkart.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView product_recyclerView;
    ArrayList product_image = new ArrayList<>(Arrays.asList(R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,
            R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image,R.drawable.sp_image));
    ArrayList type = new ArrayList<>(Arrays.asList("MEN eYEGLASSES","wOMEN eYEGLASSES","Best selling"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        product_recyclerView =(RecyclerView)findViewById(R.id.product_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        product_recyclerView.setLayoutManager(gridLayoutManager);
        ProductAdapter productAdapter = new ProductAdapter(this,product_image,type);
        product_recyclerView.setAdapter(productAdapter);
    }

}