package com.gsatechworld.spexkart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.OrderHistoryAdapter;
import com.gsatechworld.spexkart.adapter.OrderListAdapter;
import com.gsatechworld.spexkart.adapter.WishlistAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdersFragment extends Fragment {
    RecyclerView order_recyclerView;
    ArrayList iplImages = new ArrayList<>(Arrays.asList(R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist));



    public OrdersFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        //For Costomer review
        order_recyclerView =(RecyclerView)view.findViewById(R.id.order_recyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity(), GridLayoutManager.VERTICAL,false);
        order_recyclerView.setLayoutManager(gridLayoutManager);
        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(getActivity(),iplImages);
        order_recyclerView.setAdapter(orderHistoryAdapter);

        return view;
    }


}