package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.AddressAdapter;
import com.gsatechworld.spexkart.adapter.CouponListAdapter;
import com.gsatechworld.spexkart.beans.addaddress.AddAddress;
import com.gsatechworld.spexkart.beans.addaddress.AddAddressInputData;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddress;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.ForgetPasswordInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.Forgetpassword;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

public class AddressActivity extends AppCompatActivity implements OnResponseListner{
    ImageView iv_back;
    RecyclerView address_recyclerView;
    LinearLayout ll_addaddress;
    String cart_id,sub_total,discount,grand_total,gst_amount,gst_per;
    ConnectionDetector mDetector;
    AddressListInputData addressListInputData;
    AddressList addressList;
    DeleteAddressInputData deleteAddressInputData;
    DeleteAddress deleteAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        setValues();
        iv_back = findViewById(R.id.iv_back);
        address_recyclerView = findViewById(R.id.address_recyclerView);
        ll_addaddress = findViewById(R.id.ll_addaddress);

        cart_id = getIntent().getStringExtra("cart_id");
        sub_total = getIntent().getStringExtra("sub_total");
        discount = getIntent().getStringExtra("discount");
        grand_total = getIntent().getStringExtra("grand_total");
        gst_amount = getIntent().getStringExtra("gst_amount");
        gst_per = getIntent().getStringExtra("gst_per");
        CallService();



        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this, CheckOutActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ll_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            addressListInputData = new AddressListInputData();
            addressListInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            addressListInputData.setUserId(Prefs.getUserId(this));
            new WebServices(this).Addresslist(WebServices.SK_Services,
                    WebServices.ApiType.addresslist, addressListInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }
    private void DeleteAddress(String id) {
        if (this.mDetector.isConnectingToInternet()) {
            deleteAddressInputData = new DeleteAddressInputData();
            deleteAddressInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            deleteAddressInputData.setUserId(Prefs.getUserId(this));
            deleteAddressInputData.setAddressId(id);
            new WebServices(this).DeleteAddress(WebServices.SK_Services,
                    WebServices.ApiType.deleteaddress, deleteAddressInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.addresslist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                addressList = (AddressList) response;

                if (!addressList.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } if (addressList == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } if (addressList.getAddressList().size() == 0) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    /*LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
                    address_recyclerView.setLayoutManager(gridLayoutManager);
                    AddressAdapter addressAdapter = new AddressAdapter(this,addressList);
                    address_recyclerView.setAdapter(addressAdapter);*/
                }
            }
        }if (apiType == WebServices.ApiType.deleteaddress) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                deleteAddress = (DeleteAddress) response;

                if (!deleteAddress.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } if (deleteAddress == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }else {
                    SnackBar.makeText(this, deleteAddress.getMessage(), SnackBar.LENGTH_SHORT).show();
                    CallService();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddressActivity.this, CheckOutActivity.class);
        startActivity(intent);
        finish();
    }




}