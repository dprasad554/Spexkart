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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gsatechworld.spexkart.R;

public class SizeGuideActivity extends AppCompatActivity {
    WebView webview;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizeguide);

        id = getIntent().getStringExtra("product_id");
        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("http://vkbasket.com/spexkart/size-guide");

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SizeGuideActivity.this,
                ProductDetailsActivity.class);
        intent.putExtra("product_id",id);
        startActivity(intent);
        finish();
    }
}