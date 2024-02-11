package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(isDarkModeOn()){
            changeToLightMode();
        }
        Handler handler=new Handler();
        handler.postDelayed(rn,3000);
    }
    private Runnable rn=new Runnable() {
        @Override
        public void run() {
            SharedPreferences pref= getSharedPreferences("login",MODE_PRIVATE);
            Boolean check=pref.getBoolean("flag",false);
            Intent inext;
            if(check){
                inext=new Intent(getApplicationContext(), home.class);
            }
            else {
                inext=new Intent(getApplicationContext(), MainActivity.class);
            }
            startActivity(inext);
            finish();
        }
    };
    private boolean isDarkModeOn() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    // If the App is in Dark Mode then change it to Light Mode
    private void changeToLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}