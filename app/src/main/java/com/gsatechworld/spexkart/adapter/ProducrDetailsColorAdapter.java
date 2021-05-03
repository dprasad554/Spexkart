package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.productdetails.ProductColor;


import java.util.List;

public class ProducrDetailsColorAdapter extends RecyclerView.Adapter<ProducrDetailsColorAdapter.ViewHolder> {

    //Variables
    private Context context;
    List<ProductColor> productColors;
    Chosecolor chosecolor;
    private int checkedPosition = -1;

    public interface Chosecolor {
        void Colorselect(String id);
    }

    public ProducrDetailsColorAdapter(Context context, List<ProductColor> productColors, Chosecolor chosecolor) {
        this.context = context;
        this.productColors = productColors;
        this.chosecolor = chosecolor;
    }

    @NonNull
    @Override
    public ProducrDetailsColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_color, parent, false);
        return new ProducrDetailsColorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProducrDetailsColorAdapter.ViewHolder holder, final int position) {
        String color = productColors.get(position).getCode();
        holder.ll_color.setBackgroundColor(Color.parseColor(color));

        if(productColors.get(position).getStatus()==0){
            holder.ll_main.setBackgroundResource(R.drawable.square_drawable_white);
        }else {
            holder.ll_main.setBackgroundResource(R.drawable.square_drawable_green);
        }

        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkedPosition != -1 && checkedPosition != position) {
                    productColors.get(checkedPosition).setStatus(0);
                    notifyItemChanged(checkedPosition);
                }
                productColors.get(position).setStatus(1);
                notifyItemChanged(position);
                checkedPosition = position;
                chosecolor.Colorselect(productColors.get(position).getColorId().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return productColors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_main,ll_color;
        CardView ll_circle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_color = itemView.findViewById(R.id.ll_color);
            ll_main = itemView.findViewById(R.id.ll_main);
        }
    }
}