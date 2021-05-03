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
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.newproducts.ProductColor;

import java.util.List;

public class NewProductColorAdapter extends RecyclerView.Adapter<NewProductColorAdapter.ViewHolder> {

    //Variables
    private Context context;
    String url;
    List<ProductColor> productColors;

    public NewProductColorAdapter(Context context, List<ProductColor> productColors) {
        this.context = context;
        this.productColors = productColors;
    }

    @NonNull
    @Override
    public NewProductColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_color,parent,false);
        return new NewProductColorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductColorAdapter.ViewHolder holder, final int position) {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_drawable_);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable,Color.parseColor(productColors.get(position).getCode()));
    }

    @Override
    public int getItemCount() {
        return productColors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_frame,rl_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}