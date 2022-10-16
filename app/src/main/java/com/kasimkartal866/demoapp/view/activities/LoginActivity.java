package com.kasimkartal866.demoapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.common.MyPref;
import com.kasimkartal866.demoapp.orm.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail, etPass;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        onClickMethods();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void bindViews() {
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
    }

    public void onClickMethods() {
        btnLogin.setOnClickListener(v -> {

            String emailText = etMail.getText().toString();
            String passwordText = etPass.getText().toString();

            if (TextUtils.isEmpty(etMail.getText().toString())) {
                etMail.setError("enter e-mail");
            }
            if (TextUtils.isEmpty(etPass.getText().toString())) {
                etPass.setError("enter password");
            } else {
                User user = App.getRoomExecutor().checkUserPass(emailText, passwordText);

                if (user == null) {
                    Toast.makeText(LoginActivity.this, "Geçersiz Giriş Bilgileri",
                            Toast.LENGTH_SHORT).show();

                } else {
                    MyPref.getInstance().saveUserInfo(user);
                    gotoMainPage(user.getEmail());
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("yes", (dialog, which) -> finish())
                .setNegativeButton("no", null).show();
        super.onBackPressed();
    }

    private void gotoMainPage(String email) {
        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
        intent.putExtra("name", email);
        startActivity(intent);
        finish();
    }
}