package com.loyality.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class SplashActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    private static final int TIME_INTERVAL_FOR_SPLASH_SCREEN = 3 * 1000;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final boolean checkStatus = sharedpreferences.getBoolean("isLogin", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkStatus) {
                    Intent in = new Intent(SplashActivity.this, MyDashBoardScreen.class);

                    startActivity(in);
                } else {
                    Intent in = new Intent(SplashActivity.this, LandingScreen.class);

                    startActivity(in);
                }
                finish();
            }
        }, TIME_INTERVAL_FOR_SPLASH_SCREEN);

    }
}
