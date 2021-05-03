package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.ProductDetailsActivity;
import com.gsatechworld.spexkart.beans.productbygender.ProductByGender;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProduct;
import com.squareup.picasso.Picasso;

public class ProductByGenderAdpater extends RecyclerView.Adapter<ProductByGenderAdpater.ViewHolder> {

    //Variables
    private Context context;
    SpecialProduct specialProduct;
    String url;


    public ProductByGenderAdpater(Context context,SpecialProduct specialProduct) {
        this.context = context;
        this.specialProduct = specialProduct;
    }

    @NonNull
    @Override
    public ProductByGenderAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product,parent,false);
        return new ProductByGenderAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductByGenderAdpater.ViewHolder holder, final int position) {
        url = specialProduct.getProductLists().get(position).getProductImage();
        if (!url.equals("") ){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.face_frame)//if failed
                    .into(holder.iv_glass);
        }

        holder.tv_name.setText(specialProduct.getProductLists().get(position).getProductName());
        holder.tv_price.setText("Rs."+specialProduct.getProductLists().get(position).getSellingPrice());

        holder.rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                String id = specialProduct.getProductLists().get(position).getId().toString();
                intent.putExtra("product_id", id);
                context.startActivity(intent);
            }
        });
        holder.iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tv_details.setText(Html.fromHtml(Html.fromHtml(specialProduct.getProductLists().get(position).getDetails()).toString()));
    }

    @Override
    public int getItemCount() {
        return specialProduct.getProductLists().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_glass, iv_cart;
        TextView tv_name, tv_price, tv_details;
        LinearLayout ll_frame;
        RelativeLayout rl_main;
        RecyclerView color_recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            ll_frame = itemView.findViewById(R.id.ll_frame);
            rl_main = itemView.findViewById(R.id.rl_card);
            iv_cart = itemView.findViewById(R.id.iv_cart);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_details = itemView.findViewById(R.id.tv_details);
            color_recyclerView = itemView.findViewById(R.id.color_recyclerView);
        }
    }
}