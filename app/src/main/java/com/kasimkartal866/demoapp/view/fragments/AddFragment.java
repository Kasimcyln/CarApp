package com.kasimkartal866.demoapp.view.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.Car;
import com.kasimkartal866.demoapp.view.activities.MainPageActivity;


public class AddFragment extends Fragment {
    View rootView;
    private EditText etBrand, etModel, etKm, etColor;
    private ImageView ivSelect;
    private Button btnRecord;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private String imageUri = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add, container, false);

        etBrand = rootView.findViewById(R.id.etBrand);
        etModel = rootView.findViewById(R.id.etModel);
        etKm = rootView.findViewById(R.id.etKm);
        etColor = rootView.findViewById(R.id.etColor);
        ivSelect = rootView.findViewById(R.id.ivSelect);
        btnRecord = rootView.findViewById(R.id.btnRecord);


        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = etModel.getText().toString();
                String brand = etBrand.getText().toString();
                String km = etKm.getText().toString();
                String color = etColor.getText().toString();


                int userId = MyPref.getInstance().getUserId();
                Car car = new Car(model, brand, km, color, imageUri, userId);
                App.getRoomExecutor().addCar(car);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add a Car")
                        .setMessage("The Car Was Added")
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()).create();
                builder.show();

                Intent intent = new Intent(getContext(), MainPageActivity.class);
                startActivity(intent);
                getActivity().finish();



            }
        });
        ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions, PERMISSION_CODE);
                    }else {
                        pickImageFromGallery();
                    }
                }else {
                    pickImageFromGallery();
                }
            }

        });
        return rootView;
    }
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                }else {
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data.getData().toString();
            ivSelect.setImageURI(data.getData());
        }
    }
}