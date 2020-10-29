package com.nexstacks.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText mEtUsername;
    private EditText mEtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mEtUsername = findViewById(R.id.et_user_name);
        mEtPhoneNumber = findViewById(R.id.et_phone_number);
    }

    public void onDoneClicked(View view){

        String username = mEtUsername.getText().toString();
        String phoneNo = mEtPhoneNumber.getText().toString();


        Intent returnIntent = new Intent();
        returnIntent.putExtra(HomeActivity.BUNDLE_USERNAME, username);
        returnIntent.putExtra(HomeActivity.BUNDLE_PHONENO, phoneNo);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}