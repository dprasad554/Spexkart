package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.newproducts.ProductColor;
import com.gsatechworld.spexkart.beans.productdetails.ProductFramesize;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    //Variables
    private Context context;
    String url;
    List<ProductFramesize> productFramesizes;
    ChooseSize chooseSize;
    private int checkedPosition = -1;

    public interface ChooseSize {
        void SelectSize(String size_id);
    }

    public SizeAdapter(Context context,  List<ProductFramesize> productFramesizes,ChooseSize chooseSize) {
        this.context = context;
        this.productFramesizes = productFramesizes;
        this.chooseSize = chooseSize;
    }

    @NonNull
    @Override
    public SizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_size,parent,false);
        return new SizeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.ViewHolder holder, final int position) {
        holder.tv_size.setText(productFramesizes.get(position).getFrameSize());

        if(productFramesizes.get(position).getStatus()==0){

        }else {
            holder.ll_main.setBackgroundResource(R.drawable.square_drawable_green);
        }

        holder.tv_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkedPosition != -1 && checkedPosition != position) {
                    productFramesizes.get(checkedPosition).setStatus(0);
                    notifyItemChanged(checkedPosition);
                }
                productFramesizes.get(position).setStatus(1);
                notifyItemChanged(position);
                checkedPosition = position;
                chooseSize.SelectSize(productFramesizes.get(position).getFrameSize());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productFramesizes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_main,rl_main;
        TextView tv_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_size = itemView.findViewById(R.id.tv_size);
            ll_main = itemView.findViewById(R.id.ll_main);
        }
    }
}