package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;




public class signin_with_google extends AppCompatActivity {
    private EditText editTextPhone, editTextOtp;
    private Button buttonSendOtp, buttonVerifyOtp;
    private TextView textViewResendOtp;
    private FirebaseAuth mAuth;
    private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_with_google);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextOtp = findViewById(R.id.editTextOtp);
        buttonSendOtp = findViewById(R.id.buttonSendOtp);
        buttonVerifyOtp = findViewById(R.id.buttonVerifyOtp);
        textViewResendOtp = findViewById(R.id.textViewResendOtp);

        mAuth = FirebaseAuth.getInstance();

        buttonSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), otp_verification.class);
                intent.putExtra("Mobile_Number", phoneNumber);
                if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() != 10) {
                    editTextPhone.setError("Enter a valid 10 digit phone number");
                    editTextPhone.requestFocus();
                    return;
                }
                startActivity(intent);
                finish();

                //sendOtp(phoneNumber);
            }
        });

        buttonVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = editTextOtp.getText().toString().trim();

                if (TextUtils.isEmpty(otp) || otp.length() != 6) {
                    editTextOtp.setError("Enter a valid 6 digit OTP");
                    editTextOtp.requestFocus();
                    return;
                }

                verifyOtp(otp);
            }
        });

        textViewResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                resendOtp(phoneNumber);
            }
        });
    }
    private void sendOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNumber,  // Phone number to verify
                60,                  // Timeout duration
                TimeUnit.SECONDS,    // Unit of timeout
                this,                // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // Auto-retrieval or instant verification completed
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // Failed to verify phone number
                        Toast.makeText(signin_with_google.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId = s;

                        editTextPhone.setVisibility(View.GONE);
                        buttonSendOtp.setVisibility(View.GONE);
                        editTextOtp.setVisibility(View.VISIBLE);
                        buttonVerifyOtp.setVisibility(View.VISIBLE);
                        textViewResendOtp.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void verifyOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,otp);
        signInWithPhoneAuthCredential(credential);
    }
    private void resendOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNumber,  // Phone number to verify
                60,                  // Timeout duration
                TimeUnit.SECONDS,    // Unit of timeout
                this,                // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // Auto-retrieval or instant verification completed
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // Failed to verify phone number
                        Toast.makeText(signin_with_google.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId = s;
                        Toast.makeText(signin_with_google.this, "OTP resent", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Verification successful, do something
                            Toast.makeText(signin_with_google.this, "Verification successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // Verification failed
                            Toast.makeText(signin_with_google.this, "Verification failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}