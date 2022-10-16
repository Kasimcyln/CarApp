package com.kasimkartal866.demoapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.adapter.Adapter;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.Car;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private TextView tvWelcome;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tvWelcome = rootView.findViewById(R.id.tvWelcome);


        if (getActivity().getIntent().getExtras() != null) {
            String name = getActivity().getIntent().getStringExtra(G.USER_NAME_INTENT_KEY);
            if (name != null) {
                tvWelcome.setText("Welcome " + name);
            }
        }
        return rootView;
    }
}