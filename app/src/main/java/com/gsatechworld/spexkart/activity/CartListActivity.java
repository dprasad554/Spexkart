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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class CartListActivity extends AppCompatActivity {
    RecyclerView order_recyclerView;
    ArrayList iplImages = new ArrayList<>(Arrays.asList(R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //For Costomer review
        order_recyclerView =(RecyclerView)findViewById(R.id.order_recyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL,false);
        order_recyclerView.setLayoutManager(gridLayoutManager);
        OrderListAdapter orderListAdapter = new OrderListAdapter(this,iplImages);
        order_recyclerView.setAdapter(orderListAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CartListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}