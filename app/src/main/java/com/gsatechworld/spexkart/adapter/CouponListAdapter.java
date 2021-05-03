package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> agegroup;

    public CouponListAdapter(Context context,ArrayList<String> agegroup) {
        this.context = context;
        this.agegroup = agegroup;
    }

    @NonNull
    @Override
    public CouponListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon_list,parent,false);
        return new CouponListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponListAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView face_shape,iv_glass;
        TextView tv_text;
        LinearLayout ll_frame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
            ll_frame = itemView.findViewById(R.id.ll_frame);

        }
    }
}