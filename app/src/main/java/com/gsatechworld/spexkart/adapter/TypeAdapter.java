package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<Integer> glassframes;
    ArrayList<String> agegroup ;

    public TypeAdapter(Context context,ArrayList<Integer> glassframes,ArrayList<String> agegroup) {
        this.context = context;
        this.agegroup = agegroup;
        this.glassframes = glassframes;
    }

    @NonNull
    @Override
    public TypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_type,parent,false);
        return new TypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeAdapter.ViewHolder holder, final int position) {
        holder.iv_glass.setImageResource(glassframes.get(position));
        holder.tv_text.setText(agegroup.get(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass;
        TextView tv_text;
        LinearLayout ll_frame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            ll_frame = itemView.findViewById(R.id.ll_frame);
            tv_text = itemView.findViewById(R.id.tv_text);

        }
    }
}