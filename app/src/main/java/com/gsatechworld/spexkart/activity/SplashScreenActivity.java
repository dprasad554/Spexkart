package com.gsatechworld.spexkart.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gsatechworld.spexkart.R;

public class SplashScreenActivity extends AppCompatActivity {

    LinearLayout ivLogo;
    TextView tvAppName,tvName;
    public Typeface CALIBRI;
    Animation uptodown,fadeout;

    private static final int REQUEST_FOR_STORAGE_PERMISSION = 123;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int MY_PERMISSIONS_REQUEST_ACCOUNTS = 88;
    Handler mHandlerTime;
    Runnable mRunnableTimeOut;

    private int SPLASH_TIME_OUT = 3000;
    LocationManager locationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        setfullscreen();
        initializeFonts();
        setvalues();
    }

    private void initializeFonts() {
        this.CALIBRI = Typeface.createFromAsset(getAssets(), "Calibri_Bold.TTF");
    }
    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
    private void setvalues() {
        if (isTaskRoot() || !getIntent().hasCategory("android.intent.category.LAUNCHER") || getIntent().getAction() == null || !getIntent().getAction().equals("android.intent.action.MAIN")) {
            if (mayRequestReadWriteSd()) {
                if (checkLocationPermission()){
                    /* if (checkAccountsPermission()){*/
                    splashScreenCall();
                    /*}*/
                }

                return;
            } else {
                Log.e(" Permission", "enteredwrong");
                splashScreenCall();
                return;
            }
        }
        finish();
    }
    private boolean mayRequestReadWriteSd() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(SplashScreenActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(
                new String[]{Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_FOR_STORAGE_PERMISSION);
        return false;
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_permission)
                        //.setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(SplashScreenActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private void splashScreenCall() {
        mHandlerTime = new Handler();
        mRunnableTimeOut = new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, SelectGenderActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();

            }
        };
    }

    protected void onPause() {
        if (this.mHandlerTime != null) {
            this.mHandlerTime.removeCallbacks(this.mRunnableTimeOut);
        }
        super.onPause();
    }

    protected void onResume() {
        if (this.mHandlerTime != null) {
            this.mHandlerTime.postDelayed(this.mRunnableTimeOut, (long) this.SPLASH_TIME_OUT);
        }
        super.onResume();
    }

}