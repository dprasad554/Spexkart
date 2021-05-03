package com.gsatechworld.spexkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.LoginActivity;
import com.gsatechworld.spexkart.activity.MainActivity;
import com.gsatechworld.spexkart.activity.ProductListActivity;
import com.gsatechworld.spexkart.activity.SignUpActivity;

import java.util.ArrayList;

public class Navigationmenu_ItemAdapter extends RecyclerView.Adapter<Navigationmenu_ItemAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> menunitems ;

    public Navigationmenu_ItemAdapter(Context context,ArrayList<String> menunitems) {
        this.context = context;
        this.menunitems = menunitems;
    }

    @NonNull
    @Override
    public Navigationmenu_ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_navitem,parent,false);
        return new Navigationmenu_ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Navigationmenu_ItemAdapter.ViewHolder holder, final int position) {
        holder.item_name.setText(menunitems.get(position));
        holder.cv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_name;
        CardView cv_home;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            cv_home = itemView.findViewById(R.id.cv_home);

        }
    }
}