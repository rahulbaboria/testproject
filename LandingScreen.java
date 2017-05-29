package com.loyality.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class LandingScreen extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        Button btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_sign_in:
                Intent in = new Intent(LandingScreen.this,LoginScreen.class);
                startActivity(in);
                break;
            case R.id.btn_sign_up:
                Intent intent = new Intent(LandingScreen.this,SignUpScreen.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
