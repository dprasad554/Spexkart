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

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<Integer> menuicons;
    ArrayList<String> menunames ;

    public NavigationAdapter(Context context,ArrayList<Integer> menuicons,ArrayList<String> menunames) {
        this.context = context;
        this.menuicons = menuicons;
        this.menunames = menunames;
    }

    @NonNull
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_navigationmenu,parent,false);
        return new NavigationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.ViewHolder holder, final int position) {
        holder.menu_icon.setImageResource(menuicons.get(position));
        holder.menu_name.setText(menunames.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView menu_icon,iv_glass;
        TextView menu_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_icon = itemView.findViewById(R.id.menu_icon);
            menu_name = itemView.findViewById(R.id.menu_name);
        }
    }
}
