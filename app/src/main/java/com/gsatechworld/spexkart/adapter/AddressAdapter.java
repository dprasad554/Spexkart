package com.gsatechworld.spexkart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.AddAddressActivity;
import com.gsatechworld.spexkart.activity.CheckOutActivity;
import com.gsatechworld.spexkart.activity.ProductDetailsActivity;
import com.gsatechworld.spexkart.activity.SizeGuideActivity;
import com.gsatechworld.spexkart.beans.addresslist.AddressList;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    //Variables
    private Context context;
    AddressList addressList;
    UserAddress userAddress;
    String action;
    private int checkedPosition = -1;

    public interface UserAddress {
        void Addresslist(String address_id,String action,String position);
    }


    public AddressAdapter(Context context,AddressList addressList,UserAddress userAddress) {
        this.context = context;
        this.addressList = addressList;
        this.userAddress = userAddress;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_addresslist,parent,false);
        return new AddressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressAdapter.ViewHolder holder, final int position) {
        holder.tv_name.setText(addressList.getAddressList().get(position).getContactName());
        final String address = addressList.getAddressList().get(position).getLocality()+","+
                addressList.getAddressList().get(position).getLandmark()+","+addressList.getAddressList().get(position).getCity()+
                ","+addressList.getAddressList().get(position).getState()+","+addressList.getAddressList().get(position).getPincode()+","+
                addressList.getAddressList().get(position).getCountry();
        holder.tv_address.setText(address);
        holder.tv_mobile.setText(addressList.getAddressList().get(position).getMobileNumber());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("address_details",(String)new Gson().toJson(addressList.getAddressList().get(position)));
                context.startActivity(intent);
            }
        });
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = "delete";
                userAddress.Addresslist(addressList.getAddressList().get(position).getId().toString(),action, String.valueOf(position));
            }
        });

        if(addressList.getAddressList().get(position).getStatus()==0){

        }else {
            holder.ll_address.setBackgroundResource(R.drawable.square_drawable_red);
        }


        holder.ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkedPosition != -1 && checkedPosition != position) {
                    addressList.getAddressList().get(checkedPosition).setStatus(0);
                    notifyItemChanged(checkedPosition);
                }
                addressList.getAddressList().get(position).setStatus(1);
                notifyItemChanged(position);
                checkedPosition = position;

                action = "select";
                userAddress.Addresslist(addressList.getAddressList().get(position).getId().toString(),action, String.valueOf(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return addressList.getAddressList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_address,tv_mobile;
        Button btnEdit,btndelete;
        LinearLayout ll_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btndelete = itemView.findViewById(R.id.btndelete);
            ll_address = itemView.findViewById(R.id.ll_address);
        }
    }
}