package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.ProductByCatAdapter;
import com.gsatechworld.spexkart.adapter.ProductBySubAdapter;
import com.gsatechworld.spexkart.adapter.SearchResultAdapter;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCat;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCategoryInputData;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBYSubcat;
import com.gsatechworld.spexkart.beans.search.SearchInput;
import com.gsatechworld.spexkart.beans.search.SearchResults;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

public class SearchActivity extends AppCompatActivity implements OnResponseListner {
    LinearLayout ll_search,ll_result,ll_error;
    ImageView iv_close, iv_menu;
    RecyclerView product_recyclerView;
    ConnectionDetector mDetector;
    SearchInput searchInput;
    EditText et_search;
    SearchResults searchResults;
    Editable search_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setValues();
        iv_close = findViewById(R.id.iv_close);
        ll_search = findViewById(R.id.ll_search);
        iv_menu = findViewById(R.id.iv_menu);
        et_search = findViewById(R.id.et_search);
        product_recyclerView = findViewById(R.id.product_recyclerView);
        ll_result = findViewById(R.id.ll_result);
        ll_error = findViewById(R.id.ll_error);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                ll_search.setVisibility(View.GONE);
            }
        });
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() >= 3){
                    search_input = et_search.getText();
                    CallCatService(search_input);
                }

            }
        });

    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallCatService(Editable search_input) {
        if (this.mDetector.isConnectingToInternet()) {
            searchInput = new SearchInput();
            searchInput.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            searchInput.setSearch(String.valueOf(search_input));
            new WebServices(this).Search(WebServices.SK_Services,
                    WebServices.ApiType.searchresluts, searchInput);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.searchresluts) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                searchResults = (SearchResults) response;

                if (!searchResults.getSuccess().equals(true)) {
                    ll_result.setVisibility(View.GONE);
                    ll_error.setVisibility(View.VISIBLE);
                }if (searchResults.getProductLists() == null) {
                    ll_result.setVisibility(View.GONE);
                    ll_error.setVisibility(View.VISIBLE);
                }if (searchResults.getProductLists().size() == 0) {
                    ll_result.setVisibility(View.GONE);
                    ll_error.setVisibility(View.VISIBLE);
                } else {
                    ll_result.setVisibility(View.VISIBLE);
                    ll_error.setVisibility(View.GONE);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
                    product_recyclerView.setLayoutManager(gridLayoutManager);
                    SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this,searchResults);
                    product_recyclerView.setAdapter(searchResultAdapter);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}