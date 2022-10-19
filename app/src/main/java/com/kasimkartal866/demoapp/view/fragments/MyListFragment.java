package com.kasimkartal866.demoapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.adapter.Adapter;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.Car;
import com.kasimkartal866.demoapp.view.activities.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MyListFragment extends Fragment {
    View rootView;
    Adapter adapter;
    RecyclerView recyclerView;
    int userId = MyPref.getInstance().getUserId();
    private TextView tvWelcome;
    private Button btnExit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewMyList);
        tvWelcome = rootView.findViewById(R.id.tvWelcome);
        btnExit = rootView.findViewById(R.id.btnExit);

        if (getActivity().getIntent().hasExtra(G.CARS_LIST_INTENT_KEY)) {
            userId = getActivity().getIntent().getIntExtra(G.CARS_LIST_INTENT_KEY, -1);
        }
        adapter = new Adapter();
        if (getActivity().getIntent().getExtras() != null) {
            String name = getActivity().getIntent().getStringExtra(G.USER_NAME_INTENT_KEY);
            if (name != null) {
                tvWelcome.setText("Welcome " + name);
            }
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPref.getInstance().clearUserData();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return rootView;
    }
    private void setupRV() {
        recyclerView = rootView.findViewById(R.id.recyclerViewMyList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        List<Car> cars = App.getRoomExecutor().getCarsByUser(userId);
        adapter.submitList(cars);
        recyclerView.setAdapter(adapter);

    }
}