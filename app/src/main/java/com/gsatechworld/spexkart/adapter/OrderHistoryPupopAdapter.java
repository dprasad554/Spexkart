package com.gsatechworld.spexkart.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.orderhistory.OrderHistory;
import com.gsatechworld.spexkart.beans.orderhistory.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryPupopAdapter extends RecyclerView.Adapter<OrderHistoryPupopAdapter.ViewHolder> {

    //Variables
    private Context context;
    String url;
    List<ProductItem> productItems;

    public OrderHistoryPupopAdapter(Context context,List<ProductItem> productItems) {
        this.context = context;
        this.productItems = productItems;
    }

    @NonNull
    @Override
    public OrderHistoryPupopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_orderhistory,parent,false);
        return new OrderHistoryPupopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryPupopAdapter.ViewHolder holder, final int position) {

        url = productItems.get(position).getProductImage();
        if (!url.equals("") ){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.face_frame)//if failed
                    .into(holder.iv_glass);
        }
        holder.tv_count.setVisibility(View.GONE);
        holder.tv_Name.setText(productItems.get(position).getProductName());
        holder.vT_price.setText("Rs."+productItems.get(position).getProductPrice());
        holder.vT_price.setText("Rs."+productItems.get(position).getProductPrice());
        holder.vT_deliverydate.setVisibility(View.GONE);
        holder.tv_status.setVisibility(View.GONE);
        holder.tv_status_head.setVisibility(View.GONE);
        holder.tv_order.setVisibility(View.GONE);
        holder.ll_bookapointment.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_glass;
        TextView tv_Name,vT_price,vT_deliverydate,tv_status,tv_count,tv_status_head,tv_order;
        CardView rl_main;
        LinearLayout ll_bookapointment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_glass = itemView.findViewById(R.id.iv_glass);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            vT_price = itemView.findViewById(R.id.vT_price);
            vT_deliverydate = itemView.findViewById(R.id.vT_deliverydate);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_count = itemView.findViewById(R.id.tv_count);
            rl_main = itemView.findViewById(R.id.rl_main);
            tv_status_head = itemView.findViewById(R.id.tv_status_head);
            tv_order = itemView.findViewById(R.id.tv_order);
            ll_bookapointment = itemView.findViewById(R.id.ll_bookapointment);
        }
    }
}
