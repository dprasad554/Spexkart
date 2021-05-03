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

public class FaceshapeAdapter extends RecyclerView.Adapter<FaceshapeAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> categoryNames;
    Array position;
    ArrayList<Integer> allgameImages;
    ArrayList<Integer> glassframes ;

    public FaceshapeAdapter(Context context,ArrayList<Integer> allgameImages,ArrayList<Integer> glassframes) {
        this.context = context;
        this.allgameImages = allgameImages;
        this.glassframes = glassframes;
    }

    @NonNull
    @Override
    public FaceshapeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_faceshape,parent,false);
        return new FaceshapeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaceshapeAdapter.ViewHolder holder, final int position) {
        holder.face_shape.setImageResource(allgameImages.get(position));
        holder.iv_glass.setImageResource(glassframes.get(position));
        if(position == 4){
            holder.ll_frame.setBackgroundResource(R.drawable.square_drawable_red);
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView face_shape,iv_glass;
        TextView categoryName;
        LinearLayout ll_frame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            face_shape = itemView.findViewById(R.id.face_shape);
            ll_frame = itemView.findViewById(R.id.ll_frame);
            iv_glass  = itemView.findViewById(R.id.iv_glass);

        }
    }
}