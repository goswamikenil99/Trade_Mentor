package com.example.trade_mentor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class edit_profile extends AppCompatActivity {
    AlertDialog.Builder builder;
    String PREF_NAME = "MyPrefs";
    String KEY_VALUE = "value";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        NeumorphButton edit_profile=findViewById(R.id.edit_profile);
        NeumorphButton wallet=findViewById(R.id.wallet);
        EditText user_name=findViewById(R.id.user_name);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        EditText mobile_number=findViewById(R.id.mobile_number);
        builder = new AlertDialog.Builder(this);
        dbhelper helper=new dbhelper(getApplicationContext());
        make_pref(getApplicationContext());
        if(getValue() == null || getValue()==""){
            saveValue("500000.000");
        }
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
        wallet.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Setting message manually and performing action on button click
                builder.setMessage("Current Balance is:-"+getValue())
                        .setCancelable(false)
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Check Your Current Balance");
                alert.show();
                return false;
            }
        });
    }
    public void make_pref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveValue(String value) {
        editor.putString(KEY_VALUE, value);
        editor.commit();
    }

    public String getValue() {
        return sharedPreferences.getString(KEY_VALUE, "");
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}