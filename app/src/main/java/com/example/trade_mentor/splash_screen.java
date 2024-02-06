package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
}