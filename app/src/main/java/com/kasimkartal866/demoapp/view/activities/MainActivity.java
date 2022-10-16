package com.kasimkartal866.demoapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.G;
import com.kasimkartal866.demoapp.common.MyPref;

public class MainActivity extends AppCompatActivity {
    private ImageView ivCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivCar = findViewById(R.id.ivCar);


        Thread thread = new Thread() {
            @Override
            public void run () {
                try {
                    sleep(3000);
                }catch (Exception e) {

                    e.printStackTrace();
                }finally {
/*
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();*/

                    String name = MyPref.getInstance().getUserName();
                    if(name.equals("")) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }else {
                        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(G.USER_NAME_INTENT_KEY, name);
                        startActivity(intent);
                        finish();

                    }
                }
            }
        };
        thread.start();
    }
}