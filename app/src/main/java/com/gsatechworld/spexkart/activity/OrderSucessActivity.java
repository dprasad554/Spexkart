package com.gsatechworld.spexkart.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.AddressAdapter;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddress;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList_;
import com.gsatechworld.spexkart.beans.checkout.CheckOut;
import com.gsatechworld.spexkart.beans.checkout.CheckoutInputData;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

public class OrderSucessActivity extends AppCompatActivity{

    ConnectionDetector mDetector;
    TextView tv_slno,tv_orderid,tv_visit;
    String order_id,order_sl_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sucess);

        tv_slno = findViewById(R.id.tv_slno);
        tv_orderid = findViewById(R.id.tv_orderid);
        order_id = getIntent().getStringExtra("order_id");
        order_sl_num = getIntent().getStringExtra("order_sl_num");
        tv_orderid.setText("Order ID : "+order_id);
        tv_slno.setText("Order Sl Number : "+order_sl_num);
        tv_visit = findViewById(R.id.tv_visit);
        tv_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSucessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderSucessActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}