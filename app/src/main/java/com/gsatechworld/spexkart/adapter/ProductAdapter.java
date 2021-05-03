package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.LoginActivity;
import com.gsatechworld.spexkart.activity.ProductDetailsActivity;
import com.gsatechworld.spexkart.activity.SignUpActivity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<Integer> glassframes;
    ArrayList<String> agegroup ;

    public ProductAdapter(Context context,ArrayList<Integer> glassframes,ArrayList<String> agegroup) {
        this.context = context;
        this.agegroup = agegroup;
        this.glassframes = glassframes;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product,parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, final int position) {
        holder.iv_glass.setImageResource(glassframes.get(position));
        holder.rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass;
        TextView tv_text;
        LinearLayout ll_frame;
        RelativeLayout rl_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            ll_frame = itemView.findViewById(R.id.ll_frame);
            tv_text = itemView.findViewById(R.id.tv_text);
            rl_main = itemView.findViewById(R.id.rl_main);

        }
    }
}