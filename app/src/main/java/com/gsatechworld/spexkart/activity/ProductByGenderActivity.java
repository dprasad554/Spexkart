package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.ProductByCatAdapter;
import com.gsatechworld.spexkart.adapter.ProductByGenderAdpater;
import com.gsatechworld.spexkart.adapter.ProductBySubAdapter;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCat;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCategoryInputData;
import com.gsatechworld.spexkart.beans.productbygender.ProductByGender;
import com.gsatechworld.spexkart.beans.productbygender.ProductByGenderInputData;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProduct;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProductInputData;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBYSubcat;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBySubInputData;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductByGenderActivity  extends AppCompatActivity implements OnResponseListner {
    RecyclerView product_recyclerView;
    ConnectionDetector mDetector;
    String id;
    SpecialProductInputData specialProductInputData;
    SpecialProduct specialProduct;
    LinearLayout ll_load;
    ImageView iv_back;
    RelativeLayout ll_main,rl_noproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productby_gender);
        product_recyclerView =(RecyclerView)findViewById(R.id.product_recyclerView);
        ll_load = (LinearLayout)findViewById(R.id.ll_load);
        id = getIntent().getStringExtra("gender");
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductByGenderActivity.this, MainActivity.class);
                ProductByGenderActivity.this.startActivity(intent);
            }
        });
        ll_main = findViewById(R.id.ll_main);
        rl_noproduct = findViewById(R.id.rl_noproduct);
        setValues();
        CallSpecialProduct();
    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallSpecialProduct() {
        if (this.mDetector.isConnectingToInternet()) {
            specialProductInputData = new SpecialProductInputData();
            specialProductInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            specialProductInputData.setEyeglassCategoryId(id);
            new WebServices(this).ProductBySpecialCategory(WebServices.SK_Services,
                    WebServices.ApiType.productbyspecialcategory,specialProductInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }
    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.productbyspecialcategory) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                specialProduct = (SpecialProduct) response;

                if (!specialProduct.getSuccess().equals(true)) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } if (specialProduct.getProductLists().size() == 0) {
                    ll_main.setVisibility(View.GONE);
                    rl_noproduct.setVisibility(View.VISIBLE);
                }else {
                    ll_main.setVisibility(View.VISIBLE);
                    rl_noproduct.setVisibility(View.GONE);
                    if(specialProduct.getProductLists().size() >= 6){
                        ll_load.setVisibility(View.GONE);
                    }else {
                        ll_load.setVisibility(View.GONE);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
                    product_recyclerView.setLayoutManager(gridLayoutManager);
                    ProductByGenderAdpater productByGenderAdpater = new ProductByGenderAdpater(this,specialProduct);
                    product_recyclerView.setAdapter(productByGenderAdpater);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProductByGenderActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}