package com.kasimkartal866.demoapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.adapter.Adapter;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.orm.Car;

import java.util.List;

public class MainFragment extends Fragment {
    View rootView;
    private RadioButton rbAllList, rbMyList;
    Adapter adapter;
    RecyclerView recyclerView;
    int userId = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rbAllList = rootView.findViewById(R.id.rbAllList);
        rbMyList = rootView.findViewById(R.id.rbMyList);
        adapter = new Adapter();



        if (getActivity().getIntent().hasExtra(G.CARS_LIST_INTENT_KEY)) {
            userId = getActivity().getIntent().getIntExtra(G.CARS_LIST_INTENT_KEY, -1);
        }
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        List<Car> cars = App.getRoomExecutor().getCars();
        adapter.submitList(cars);
        recyclerView.setAdapter(adapter);
    }

    private void setupRV() {
        recyclerView = rootView.findViewById(R.id.recyclerViewMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}