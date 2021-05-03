package com.gsatechworld.spexkart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.CouponListAdapter;
import com.gsatechworld.spexkart.adapter.OrderListAdapter;
import com.gsatechworld.spexkart.adapter.ProductByCatAdapter;
import com.gsatechworld.spexkart.adapter.ProductBySubAdapter;
import com.gsatechworld.spexkart.beans.coupons.CouponList;
import com.gsatechworld.spexkart.beans.coupons.CouponsInputData;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCat;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCategoryInputData;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBYSubcat;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

import java.util.ArrayList;
import java.util.Arrays;

public class CouponListActivity extends AppCompatActivity implements OnResponseListner {
    RecyclerView coupon_recyclerView;
    ArrayList iplImages = new ArrayList<>(Arrays.asList(R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist,R.drawable.wishlist));
    ImageView iv_back;
    ConnectionDetector mDetector;
    CouponsInputData couponsInputData;
    CouponList couponList;
    String cart_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        cart_id = getIntent().getStringExtra("cart_id");
        setValues();
        CallService();

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CouponListActivity.this, CartListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //For Costomer review
        coupon_recyclerView =(RecyclerView)findViewById(R.id.coupon_recyclerView);

    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            couponsInputData = new CouponsInputData();
            couponsInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            new WebServices(this).CouponList(WebServices.SK_Services,
                    WebServices.ApiType.couponlist,couponsInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.couponlist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                couponList = (CouponList) response;

                if (!couponList.getSuccess().equals(true)) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }if(couponList.getDataList() == null){
                    SnackBar.makeText(this, "No Coupons Available", SnackBar.LENGTH_SHORT).show();
                }if(couponList.getDataList().size() == 0){
                    SnackBar.makeText(this, "No Coupons Available", SnackBar.LENGTH_SHORT).show();
                }else {
                    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
                    coupon_recyclerView.setLayoutManager(gridLayoutManager);
                    CouponListAdapter couponListAdapter = new CouponListAdapter(this,couponList);
                    coupon_recyclerView.setAdapter(couponListAdapter);
                }
            }
        }
    }






    public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {

        //Variables
        private Context context;
        CouponList couponList;

        public CouponListAdapter(Context context,CouponList couponList) {
            this.context = context;
            this.couponList = couponList;
        }

        @NonNull
        @Override
        public CouponListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon_list,parent,false);
            return new CouponListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CouponListAdapter.ViewHolder holder, final int position) {
            holder.tv_code.setText(couponList.getDataList().get(position).getCouponCode());
            holder.tv_description.setText(couponList.getDataList().get(position).getShortDesc());
            holder.ll_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   holder.tv_apply.setText("Applied");
                    String coupon_id = couponList.getDataList().get(position).getId().toString();
                    String coupon_code = couponList.getDataList().get(position).getCouponCode();
                    String coupon_description = couponList.getDataList().get(position).getShortDesc();
                    Intent intent = new Intent(CouponListActivity.this, CartListActivity.class);
                    intent.putExtra("coupon_id",coupon_id);
                    intent.putExtra("coupon_code",coupon_code);
                    intent.putExtra("coupon_description",coupon_description);
                    intent.putExtra("cart_id", cart_id);
                    startActivity(intent);
                    finish();
                }
            });

        }

        @Override
        public int getItemCount() {
            return couponList.getDataList().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv_code,tv_description,tv_apply;
            LinearLayout ll_submit;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_code = itemView.findViewById(R.id.tv_code);
                tv_description = itemView.findViewById(R.id.tv_description);
                ll_submit = itemView.findViewById(R.id.ll_submit);
                tv_apply = itemView.findViewById(R.id.tv_apply);

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CouponListActivity.this, CartListActivity.class);
        startActivity(intent);
        finish();
    }

}