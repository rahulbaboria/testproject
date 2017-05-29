package com.loyality.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class SignUpScreen extends AppCompatActivity {
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editEmailId;
    private EditText editPhoneNo;
    private EditText editPassword;

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void findViews() {
        editFirstName = (EditText) findViewById(R.id.edit_first_name);
        editLastName = (EditText) findViewById(R.id.edit_last_name);
        editEmailId = (EditText) findViewById(R.id.edit_email_id);
        editPhoneNo = (EditText) findViewById(R.id.edit_phone_no);
        editPassword = (EditText) findViewById(R.id.edit_password);
        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editFirstName.getText().toString().trim();
                String lastName = editLastName.getText().toString().trim();
                String emailId = editEmailId.getText().toString().trim();
                String phoneNo = editPhoneNo.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if (validateDetails(firstName, lastName, emailId, phoneNo, password)) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(SignUpScreen.this);
                    databaseHelper.insertUser(firstName, lastName, emailId, phoneNo, password);
                    clearDetails();
                    Intent in = new Intent(SignUpScreen.this,LoginScreen.class);
                    startActivity(in);
                } else {
                    Toast.makeText(SignUpScreen.this, "Details are invalid.", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    private void clearDetails() {
        editFirstName.setText("");
        editLastName.setText("");
        editEmailId.setText("");
        editPhoneNo.setText("");
        editPassword.setText("");

    }

    private boolean validateDetails(String firstName, String lastName, String emailId, String phoneNo, String password) {
        if (firstName.isEmpty() || firstName.equalsIgnoreCase(""))
            return false;
        if (lastName.isEmpty() || lastName.equalsIgnoreCase(""))
            return false;
        if (emailId.isEmpty() || emailId.equalsIgnoreCase("") || !isValidEmail(emailId))
            return false;
        if (phoneNo.isEmpty() || phoneNo.equalsIgnoreCase(""))
            return false;
        if (password.isEmpty() || password.equalsIgnoreCase(""))
            return false;

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
    }
}
