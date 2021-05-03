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
import com.gsatechworld.spexkart.adapter.AgeGroupAdapter;
import com.gsatechworld.spexkart.adapter.WishlistAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class WishListFragment extends Fragment {
    RecyclerView wishlist_recyclerView;
    ArrayList iplImages = new ArrayList<>(Arrays.asList(R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist));



    public WishListFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        //For Costomer review
        wishlist_recyclerView =(RecyclerView)view.findViewById(R.id.wishlist_recyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity(),GridLayoutManager.VERTICAL,false);
        wishlist_recyclerView.setLayoutManager(gridLayoutManager);
        WishlistAdapter wishlistAdapter = new WishlistAdapter(getActivity(),iplImages);
        wishlist_recyclerView.setAdapter(wishlistAdapter);

        return view;
    }


}