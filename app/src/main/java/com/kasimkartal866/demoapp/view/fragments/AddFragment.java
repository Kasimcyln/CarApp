package com.kasimkartal866.demoapp.view.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.Car;
import com.kasimkartal866.demoapp.view.activities.MainPageActivity;


public class AddFragment extends Fragment {

    private EditText etBrand, etModel, etKm;
    private Spinner spColor;
    private ImageView ivSelect;
    private Button btnRecord;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private String imageUri = "";
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add, container, false);

        etBrand = rootView.findViewById(R.id.etBrand);
        etModel = rootView.findViewById(R.id.etModel);
        etKm = rootView.findViewById(R.id.etKm);
        spColor = rootView.findViewById(R.id.spColor);
        ivSelect = rootView.findViewById(R.id.ivSelect);
        btnRecord = rootView.findViewById(R.id.btnRecord);
        initspinnerfooter();

        btnRecord.setOnClickListener(v -> {
            String model = etModel.getText().toString();
            String brand = etBrand.getText().toString();
            String km = etKm.getText().toString();
            String color = spColor.getSelectedItem().toString();

            int userId = MyPref.getInstance().getUserId();
            Car car = new Car(model, brand, km, color, imageUri, userId);
            App.getRoomExecutor().addCar(car);

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Add a book")
                    .setMessage("The Book was added.")
                    .setPositiveButton("OK", (dialog, i) -> {
                        dialog.dismiss();
                    })
                    .create();
            builder.show();
            Intent intent = new Intent(getContext(), MainPageActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {

                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions, PERMISSION_CODE);

                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data.getData().toString();
            ivSelect.setImageURI(data.getData());
        }
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private void initspinnerfooter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor.setAdapter(adapter);
        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}