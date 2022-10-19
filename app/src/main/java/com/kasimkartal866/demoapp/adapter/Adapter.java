package com.kasimkartal866.demoapp.adapter;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.Utilities;
import com.kasimkartal866.demoapp.orm.Car;
import com.kasimkartal866.demoapp.view.activities.MainPageActivity;


import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Car> data = new ArrayList<>();
    private Context context;
    public Adapter() {
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBrand, tvModel, tvKm, tvColor;
        ConstraintLayout cl;
        ImageView ivPic;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvKm = itemView.findViewById(R.id.tvKm);
            tvColor = itemView.findViewById(R.id.tvColor);
            cl = itemView.findViewById(R.id.cl);
            ivPic = itemView.findViewById(R.id.ivPic);

        }
    }

    public void submitList(List<Car> cars) {
        data = cars;
        this.notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Car car = data.get(position);
        holder.tvBrand.setText(car.getBrand());
        holder.tvModel.setText(car.getModel());
        holder.tvColor.setText(car.getColor());
        holder.tvKm.setText(car.getKm());

        Utilities.loadImage(context,car.getImageAddress(),holder.ivPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainPageActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


}
