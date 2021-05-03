package com.gsatechworld.spexkart.utils;

import android.content.Context;

import com.gsatechworld.spexkart.R;


public class ProgressDialog {
    private static android.app.ProgressDialog pdLoading = null;


    public static android.app.ProgressDialog getInstance(Context context) {
        if (pdLoading != null && pdLoading.getContext() == context) {
            return pdLoading;
        }
        pdLoading = new android.app.ProgressDialog(context, R.style.MyTheme);
        pdLoading.setCancelable(false);
        pdLoading.setCanceledOnTouchOutside(false);
        return pdLoading;
    }

}
