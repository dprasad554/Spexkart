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
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBYSubcat;
import com.gsatechworld.spexkart.beans.search.SearchResults;
import com.squareup.picasso.Picasso;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    //Variables
    private Context context;
    String url,weburl;
    SearchResults searchResults;


    public SearchResultAdapter(Context context,SearchResults searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product,parent,false);
        return new SearchResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.ViewHolder holder, final int position) {
        url = searchResults.getProductLists().get(position).getProductImage();
        if (!url.equals("") ){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.face_frame)//if failed
                    .into(holder.iv_glass);
        }

        holder.tv_name.setText(searchResults.getProductLists().get(position).getProductName());
        holder.tv_price.setText("Rs."+searchResults.getProductLists().get(position).getSellingPrice());

        holder.rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                String id = searchResults.getProductLists().get(position).getId().toString();
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
        holder.tv_details.setText(Html.fromHtml(Html.fromHtml(searchResults.getProductLists().get(position).getDetails()).toString()));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.color_recyclerView.setLayoutManager(linearLayoutManager);
        SearchProductColor searchProductColor = new SearchProductColor(context,searchResults.getProductLists().get(position).getProductColor());
        holder.color_recyclerView.setAdapter(searchProductColor);

    }

    @Override
    public int getItemCount() {
        return searchResults.getProductLists().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass,iv_cart;
        TextView tv_name,tv_price,tv_details;
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