package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class edit_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        NeumorphButton edit_profile=findViewById(R.id.edit_profile);
        EditText user_name=findViewById(R.id.user_name);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        EditText mobile_number=findViewById(R.id.mobile_number);
        dbhelper helper=new dbhelper(getApplicationContext());
//        ArrayList<user_info_model> model=helper.fatch_user_info();
//        for(int i=0;i<model.size();i++){
//            Log.d("user_info",model.get(i).name+model.get(i).email+model.get(i).password+model.get(i).mobile_number);
//        }
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user_name.getText().toString())){
                    user_name.setError("username is compulsary");
                    return;
                }
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Email is compulsary");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("password is compulsary");
                    return;
                }
                if(TextUtils.isEmpty(mobile_number.getText().toString())){
                    mobile_number.setError("Mobile Number is compulsary");
                    return;
                }
                SharedPreferences pref= getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor edit= pref.edit();
                edit.putBoolean("flag",false);
                edit.apply();
                user_info_model model=new user_info_model();
                model.id=1;
                model.name=user_name.getText().toString();
                model.email=email.getText().toString();
                model.password=password.getText().toString();
                model.mobile_number=mobile_number.getText().toString();
                helper.update_user_info(model);
                Toast.makeText(getApplicationContext(),user_name.getText().toString()+" \n "+email.getText().toString()+" \n "+password.getText().toString()+" \n "+mobile_number.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}