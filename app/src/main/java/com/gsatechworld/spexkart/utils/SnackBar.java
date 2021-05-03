package com.gsatechworld.spexkart.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.spexkart.R;


public class SnackBar {
    public static final int LENGTH_SHORT = -1;

    public static Snackbar makeText(Context context, int resId, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), context.getResources().getText(resId), duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.white));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.black));
        if (readLocale(context).equals("pa")) {
            font = Typeface.createFromAsset(context.getAssets(), "NIRMALA.TTF");
        } else {
            font = Typeface.createFromAsset(context.getAssets(), "NIRMALA.TTF");
        }
        text.setTypeface(font);
        return snackbar;
    }

    public static Snackbar makeText(Context context, String message, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), message, duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.black));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.white));
        return snackbar;
    }

    public static Snackbar makeText(Context context, View view, String message, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(view, message, duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.black));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.white));
        return snackbar;
    }

    public static Snackbar makeText(Context context, View view, int resId, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(view, context.getResources().getText(resId), duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.black));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.white));
        return snackbar;
    }

    private static String readLocale(Context mContext) {
        return mContext.getSharedPreferences("Locale", 0).getString("Language", "en");
    }
}
