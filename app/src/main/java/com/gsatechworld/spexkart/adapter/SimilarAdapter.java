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

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> categoryNames;
    Array position;
    ArrayList<Integer> faceshapes;
    ArrayList<Integer> glassframes ;

    public SimilarAdapter(Context context,ArrayList<Integer> faceshapes,ArrayList<Integer> glassframes) {
        this.context = context;
        this.faceshapes = faceshapes;
        this.glassframes = glassframes;
    }

    @NonNull
    @Override
    public SimilarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_similar_product,parent,false);
        return new SimilarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAdapter.ViewHolder holder, final int position) {
        holder.iv_glass.setImageResource(faceshapes.get(position));

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass;
        TextView categoryName;
        LinearLayout ll_frame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            ll_frame = itemView.findViewById(R.id.ll_frame);
            iv_glass  = itemView.findViewById(R.id.iv_glass);

        }
    }
}