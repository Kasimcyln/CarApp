package com.kasimkartal866.demoapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.adapter.Adapter;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.Car;

import java.util.ArrayList;
import java.util.List;

public class MyListFragment extends Fragment {
    View rootView;
    Adapter adapter;
    ArrayList<Car> carArrayList;
    RecyclerView recyclerView;
    int userId = -1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        if (getActivity().getIntent().hasExtra(G.CARS_LIST_INTENT_KEY)) {
            userId = getActivity().getIntent().getIntExtra(G.CARS_LIST_INTENT_KEY, -1);
        }
        adapter = new Adapter();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return rootView;
    }

    @Override
    public void onResume() {
        List<Car> cars = App.getRoomExecutor().getBooksByUser(userId);
        adapter.submitList(cars);
        recyclerView.setAdapter(adapter);
        super.onResume();
    }
}