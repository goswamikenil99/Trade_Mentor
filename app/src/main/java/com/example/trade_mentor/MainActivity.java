package com.example.trade_mentor;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.ShapeType;

public class MainActivity extends AppCompatActivity {
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 100;
    int valid=0;
    custome_loading_dialog loanding=new custome_loading_dialog(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NeumorphButton signup=findViewById(R.id.signup);
        NeumorphButton login=findViewById(R.id.login);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        //login.setShapeType(Integer.parseInt("pressed"));
        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()

                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId("426534916744-nafg389h1o2uugcj4uovkp6gfdhpt37k.apps.googleusercontent.com")
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .setAutoSelectEnabled(true)
                .build();
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
                loanding.startloading();
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
                        loanding.endloading();
                        Intent intent= new  Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                        valid=1;
                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                if(valid!=1){
                    loanding.endloading();
                    Toast.makeText(getApplicationContext(),"Invalid User!!!!!! ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void buttonGoogleSignIn(View view){
        loanding.startloading();
        oneTapClient.beginSignIn(signInRequest)

                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {

                    @Override

                    public void onSuccess(BeginSignInResult result) {

                        try {

                            startIntentSenderForResult(

                                    result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,

                                    null, 0, 0, 0);
                            loanding.endloading();

                        } catch (IntentSender.SendIntentException e) {

                            Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                            loanding.endloading();

                        }

                    }

                })

                .addOnFailureListener(this, new OnFailureListener() {

                    @Override

                    public void onFailure(@NonNull Exception e) {

                        // No saved credentials found. Launch the One Tap sign-up flow, or

                        // do nothing and continue presenting the signed-out UI.

                        Log.d(TAG, e.getLocalizedMessage());

                    }

                });

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQ_ONE_TAP:

                try {

                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);

                    String idToken = credential.getGoogleIdToken();

                    String username = credential.getId();

                    String password = credential.getPassword();
                    String name=credential.getDisplayName();
                    loanding.endloading();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    // Set the message show for the Alert time
                    builder.setMessage("Authentication done.\nWelcome "+name+" Continue With Trade Mentor");

                    // Set Alert Title
                    builder.setTitle("Sign-In Successfully Completed");

                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // When the user click yes button then app will close
                        SharedPreferences pref= getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor edit= pref.edit();
                        edit.putBoolean("flag",false);//update false to true
                        edit.apply();
                        valid=1;
                        Intent intent= new  Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                    });

                    // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // If user click no then dialog box is canceled.
                        dialog.cancel();
                    });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                    if (idToken !=  null) {

                        // Got an ID token from Google. Use it to authenticate

                        // with your backend.

                        Log.d(TAG, "Got ID token.");

                    } else if (password != null) {

                        // Got a saved username and password. Use them to authenticate

                        // with your backend.

                        Log.d(TAG, "Got password.");

                    }

                } catch (ApiException e) {

                    //textView.setText(e.toString());

                }
                break;
        }
    }
}