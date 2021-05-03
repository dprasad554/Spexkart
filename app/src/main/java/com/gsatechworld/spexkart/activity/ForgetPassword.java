package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.forgetpassword.ForgetPasswordInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.Forgetpassword;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

public class ForgetPassword extends AppCompatActivity implements OnResponseListner {
    TextView tv_create;
    Button btnNext;
    ConnectionDetector mDetector;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText et_email;
    Forgetpassword forgetPassword;
    ForgetPasswordInputData forgetPasswordInputData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        setfullscreen();
        setValues();



        tv_create = findViewById(R.id.tv_create);
        et_email = findViewById(R.id.et_email);

        btnNext = findViewById(R.id.btnNext);
        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallService();
            }
        });
    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            if (!et_email.getText().toString().isEmpty() || !et_email.getText().toString().equals("")) {
                if (et_email.getText().toString().matches(emailPattern)) {
                    forgetPasswordInputData = new ForgetPasswordInputData();
                    forgetPasswordInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
                    forgetPasswordInputData.setEmail(et_email.getText().toString());
                        new WebServices(this).ForgetPassword(WebServices.SK_Services,
                                WebServices.ApiType.forgetpassword, forgetPasswordInputData);
                } else {
                    SnackBar.makeText(this, "Please Give Valid Email Address", SnackBar.LENGTH_SHORT).show();
                }
            } else {
                SnackBar.makeText(this, "Please Enter Email", SnackBar.LENGTH_SHORT).show();
            }
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.forgetpassword) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                forgetPassword = (Forgetpassword) response;

                if (!forgetPassword.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }









    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}