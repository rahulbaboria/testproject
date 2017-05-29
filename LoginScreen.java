package com.loyality.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.loyality.testproject.SplashActivity.MyPREFERENCES;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;
    private TextView textForgotPassword;

    private void findViews() {
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        textForgotPassword = (TextView) findViewById(R.id.text_forgot_password);
        btnLogin.setOnClickListener(this);
    }
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            if (isValidEmail(email))
            {
                DatabaseHelper databaseHelper = new DatabaseHelper(LoginScreen.this);
                UserModel userModel = databaseHelper.getUserDetails(email);
                if (userModel!=null)
                {
                    SharedPreferences sharedpreferences;

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("firstName",userModel.getfName());
                    editor.putString("lastName",userModel.getlName());
                    editor.putString("email",userModel.getEmailId());
                    editor.putString("phoneNo",userModel.getPhoneNo());
                    editor.putString("password",userModel.getPassword());
                    editor.putInt("userId",userModel.getUserId());
                    editor.putBoolean("isLogin",true);
                    editor.commit();

                    Intent in = new Intent(LoginScreen.this,MyDashBoardScreen.class);
                    startActivity(in);
                }else
                {
                    Toast.makeText(LoginScreen.this,"Details are invalid.",Toast.LENGTH_SHORT).show();
                }
            }else
            {
                Toast.makeText(LoginScreen.this,"Please enter valid email id.",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }
}
