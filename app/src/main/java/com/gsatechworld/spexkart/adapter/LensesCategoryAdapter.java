package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.LensesActivity;
import com.gsatechworld.spexkart.activity.LoginActivity;
import com.gsatechworld.spexkart.activity.MainActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LensesCategoryAdapter extends RecyclerView.Adapter<LensesCategoryAdapter.ViewHolder> {

    //Variables
    private Context context;

    public LensesCategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LensesCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lenses_category,parent,false);
        return new LensesCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LensesCategoryAdapter.ViewHolder holder, final int position) {
        holder.ll_lens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LensesActivity.class);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass;
        TextView categoryName;
        LinearLayout ll_lens;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            ll_lens = itemView.findViewById(R.id.ll_lens);
            iv_glass  = itemView.findViewById(R.id.iv_glass);

        }
    }
}