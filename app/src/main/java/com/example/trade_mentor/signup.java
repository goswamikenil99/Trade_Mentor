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
        EditText editTextemail=findViewById(R.id.email);
        EditText editTextpassword=findViewById(R.id.password);
        EditText editTextPhone=findViewById(R.id.mobile_number);
        dbhelper helper=new dbhelper(getApplicationContext());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                String email = editTextemail.getText().toString().trim();
                String password = editTextpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    editTextemail.setError("Email is compulsary");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    editTextpassword.setError("password is compulsary");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() != 10) {
                    editTextPhone.setError("Enter a valid 10 digit phone number");
                    editTextPhone.requestFocus();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), otp_verification.class);
                intent.putExtra("Mobile_Number", phoneNumber);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
    }
}