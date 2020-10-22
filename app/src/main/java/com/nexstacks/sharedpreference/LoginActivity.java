package com.nexstacks.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefManager = getApplicationContext().getSharedPreferences("Nextstacks", MODE_PRIVATE);
        editor = prefManager.edit();

        final EditText mEtUsername = findViewById(R.id.et_email_address);
        final EditText mEtPassword = findViewById(R.id.et_password);


        Button mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();

                editor.putString("USERNAME", username);
                editor.putString("PASSWORD", password);
                editor.putBoolean("ISLOGINSUCCESS", true);
                editor.apply();
//                editor.commit();

                login();
            }
        });

        boolean isUserAlreadyLoggedIn = prefManager.getBoolean("ISLOGINSUCCESS", false);

        if(isUserAlreadyLoggedIn){
            login();
        }
    }

    private void login(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}