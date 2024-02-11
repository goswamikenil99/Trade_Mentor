package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NeumorphButton signup=findViewById(R.id.signup);
        NeumorphButton login=findViewById(R.id.login);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        //login.setShapeType(Integer.parseInt("pressed"));
        dbhelper helper=new dbhelper(getApplicationContext());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(getApplicationContext(), signup.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Email is compulsary");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("password is compulsary");
                    return;
                }
                ArrayList<user_info_model> model=helper.fatch_user_info();
                for(int i=0;i<model.size();i++){
                    Log.d("user_info",model.get(i).email+"  "+model.get(i).password);
                    Log.d("user_info",email.getText().toString()+"  "+password.getText().toString());
//                    email.setText(model.get(i).email);
//                    password.setText(model.get(i).password);
                    if(email.getText().toString().trim().equals(model.get(i).email) && password.getText().toString().trim().equals(model.get(i).password)){
                        SharedPreferences pref= getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor edit= pref.edit();
                        edit.putBoolean("flag",false);//update false to true
                        edit.apply();
                        Intent intent= new  Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Invalid User!!!!!! ",Toast.LENGTH_LONG).show();
                    }
                }
                if(model.size()>=0){
                    Toast.makeText(getApplicationContext(),"Invalid User!!!!!! ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}