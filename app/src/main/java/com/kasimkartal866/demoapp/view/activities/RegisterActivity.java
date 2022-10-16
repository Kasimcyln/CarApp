package com.kasimkartal866.demoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kasimkartal866.demoapp.R;
import com.kasimkartal866.demoapp.common.App;
import com.kasimkartal866.demoapp.orm.User;
import com.kasimkartal866.demoapp.common.Utilities;

public class RegisterActivity extends AppCompatActivity {
    private EditText etRegisterMail, etRegisterPass, etRegisterPhone,etRegisterPass2;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindViews();
        onClickMethods();
        App.getPref().getUserName();

    }

    public void bindViews () {
        etRegisterMail = findViewById(R.id.etRegisterMail);
        etRegisterPass = findViewById(R.id.etRegisterPass);
        etRegisterPhone = findViewById(R.id.etRegisterPhone);
        etRegisterPass2 = findViewById(R.id.etRegisterPass2);
        btnSave = findViewById(R.id.btnSave);
    }
    public void onClickMethods () {
        btnSave.setOnClickListener(v -> {

            boolean state = false;
            if (TextUtils.isEmpty(etRegisterMail.getText().toString()) ) {
                etRegisterMail.setError("enter e-mail");
                state = true;
            }
            if (!Utilities.isValidEmail(etRegisterMail.getText().toString()) ) {
                etRegisterMail.setError("enter .gmail.com");
                state = true;
            }
            if (TextUtils.isEmpty(etRegisterPhone.getText().toString())) {
                etRegisterPhone.setError("enter phone");
                state = true;
            }

            if (TextUtils.isEmpty(etRegisterPass.getText().toString())) {
                etRegisterPass.setError("enter password");
                state = true;
            }
            if (!Utilities.isValidPassword(etRegisterPass.getText().toString())) {
                etRegisterPass.setError("büyük harf, küçük harf, rakam ve noktalama işareti içermelidir");
                state = true;
            }
            if (TextUtils.isEmpty(etRegisterPass.getText().toString())) {
                etRegisterPass.setError("try enter password");
                state = true;
            } else if (!etRegisterPass2.getText().toString().contentEquals(etRegisterPass.getText().toString())) {
                etRegisterPass2.setError("passwords are incompatible");
                state = true;
            }
            if (state){
                return;
            }
            User user = new User();
            user.setEmail(etRegisterMail.getText().toString());
            user.setPhone(etRegisterPhone.getText().toString());
            user.setPassword(etRegisterPass.getText().toString());
            user.setPassword2(etRegisterPass2.getText().toString());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    App.dao.addUser(user);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }).start();
//            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
        });
    }
}
// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);