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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AgeGroupAdapter  extends RecyclerView.Adapter<AgeGroupAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> categoryNames;
    Array position;
    ArrayList<String> agegroup;
    ArrayList<Integer> glassframes ;

    public AgeGroupAdapter(Context context,ArrayList<String> agegroup) {
        this.context = context;
        this.agegroup = agegroup;
    }

    @NonNull
    @Override
    public AgeGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_agegroup,parent,false);
        return new AgeGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeGroupAdapter.ViewHolder holder, final int position) {
       holder.tv_text.setText(agegroup.get(position));

        if(position == 4){
            holder.ll_frame.setBackgroundResource(R.drawable.circle_drawable_red);
            holder.tv_text.setTextColor(Color.parseColor("#FF0D41"));
        }

    }

    @Override
    public int getItemCount() {
        return 6;
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