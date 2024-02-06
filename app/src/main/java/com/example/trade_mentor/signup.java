package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import soup.neumorphism.NeumorphButton;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        NeumorphButton signup=findViewById(R.id.signup);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        EditText mobile_number=findViewById(R.id.mobile_number);
        dbhelper helper=new dbhelper(getApplicationContext());
        signup.setOnClickListener(new View.OnClickListener() {
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
                if(TextUtils.isEmpty(mobile_number.getText().toString())){
                    mobile_number.setError("mobile number is compulsary");
                    return;
                }
                    helper.add_user_info(email.getText().toString(),password.getText().toString(),mobile_number.getText().toString());
                    Toast.makeText(getApplicationContext(),"Sign-up Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),home.class);
                    startActivity(intent);
                    finish();
            }
        });
    }
}