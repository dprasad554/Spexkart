package com.gsatechworld.spexkart.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.activity.UploadPrescriptionActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EyeglasessLenses extends Fragment {
    private String clothing_name;
    String type;
    List<String> teamlist = new ArrayList<>();
    RecyclerView team_RecyclerView;
    Dialog paypopup,viewpopup;
    String name;
    TextView tv_fragment;
    Button btnNext;

    public EyeglasessLenses getInstance(String team_name) {
        EyeglasessLenses topRated = new EyeglasessLenses();
        // Supply index input as an argument
        Bundle args = new Bundle();
        args.putString("team_", team_name);
        name = team_name;

        //args.putString("name", name);
        topRated.setArguments(args);
        return topRated;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            clothing_name = args.getString("team_", "0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eyeglass_lenses, container, false);
        tv_fragment = view.findViewById(R.id.tv_fragment);
        tv_fragment.setText(clothing_name);
        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UploadPrescriptionActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return  view;
    }
}