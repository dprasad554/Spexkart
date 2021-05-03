package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.ProductAdapter;
import com.gsatechworld.spexkart.beans.category.CategoryInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.ForgetPasswordInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.Forgetpassword;
import com.gsatechworld.spexkart.beans.newproducts.NewProductList;
import com.gsatechworld.spexkart.beans.newproducts.ProductList;
import com.gsatechworld.spexkart.fragment.HomeFragment;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;
import com.softrunapps.paginatedrecyclerview.PaginatedAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity implements OnResponseListner {

    ConnectionDetector mDetector;
    CategoryInputData categoryInputData;
    NewProductList newProductList;
    List<ProductList> data;
    RecyclerView product_recyclerView;
    Button btnLoad;
    ProductAdapter productAdapter;

    int conunter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        setfullscreen();
        setValues();
        product_recyclerView = findViewById(R.id.product_recyclerView);
        btnLoad = findViewById(R.id.btnLoad);
        NewProductList();
    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void NewProductList() {
        if (this.mDetector.isConnectingToInternet()) {
            categoryInputData = new CategoryInputData();
            categoryInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            new WebServices(this).NewProductList(WebServices.SK_Services,
                    WebServices.ApiType.newproductlist,categoryInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.newproductlist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                newProductList = (NewProductList) response;

                if (!newProductList.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                    product_recyclerView.setVisibility(View.GONE);
                }if (newProductList.getProductLists() == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                    product_recyclerView.setVisibility(View.GONE);
                }else {
                    product_recyclerView.setVisibility(View.VISIBLE);
                    if(newProductList.getProductLists().size()>=6){
                        btnLoad.setVisibility(View.VISIBLE);
                    }else {
                        btnLoad.setVisibility(View.GONE);
                    }
                    ProductAdapter productAdapter = new ProductAdapter();
                    productAdapter.setDefaultRecyclerView(this, R.id.product_recyclerView);
                    //setters
                    productAdapter.setStartPage(1); //set first page of data. default value is 1.
                    productAdapter.setPageSize(6); //set page data size. default value is 10.
                    //getters
                    productAdapter.getStartPage(); // return start page
                    productAdapter.getCurrentPage(); // return last page which loaded
                    productAdapter.getRecyclerView(); // return recycler view
                    productAdapter.setOnPaginationListener(new PaginatedAdapter.OnPaginationListener() {
                        @Override
                        public void onCurrentPage(int page) {
                            //current page which loaded in list
                        }

                        @Override
                        public void onNextPage(int page) {
                            // call your method to get next page
                        }

                        @Override
                        public void onFinish() {
                            // end of the list and all pages loaded
                        }
                    });
                    getNewItems(productAdapter.getStartPage());




                   /* GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
                    product_recyclerView.setLayoutManager(gridLayoutManager);
                    ProductAdapter productAdapter = new ProductAdapter(this,newProductList);
                    product_recyclerView.setAdapter(productAdapter);*/

                }
            }
        }
    }

    public void onGetDate(List<NewProductList> users) {
        System.out.println("Product Name:::::::::::"+users.get(0).getProductLists().get(0).getProductName());
        productAdapter.submitItems(users);
    }


    private void getNewItems(final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<NewProductList> users = new ArrayList<>();
                int start = page * conunter - conunter;
                int end = page * conunter;
                for (int i = start; i < end; i++) {
                    if (i < newProductList.getProductLists().size()) {
                        users.add(newProductList);
                    }
                }
                onGetDate(users);
            }
        }, 3000);
    }


    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            data.add(new ProductList());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DemoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}