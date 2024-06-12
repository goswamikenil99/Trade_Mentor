package com.example.trade_mentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_verification extends AppCompatActivity {
    private EditText otpEditText;
    private Button buttonResendOtp, buttonVerifyOtp;
    private FirebaseAuth mAuth;
    private String verificationId,phoneNumber,email,password;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    custome_loading_dialog loading=new custome_loading_dialog(otp_verification.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        otpEditText=findViewById(R.id.otpEditText);
        buttonResendOtp=findViewById(R.id.ButtonResendOtp);
        buttonVerifyOtp=findViewById(R.id.buttonVerifyOtp);
        Intent intent = getIntent();
        phoneNumber= intent.getStringExtra("Mobile_Number");
        email= intent.getStringExtra("email");
        password= intent.getStringExtra("password");
        mAuth = FirebaseAuth.getInstance();
        timer();
        sendOtp(phoneNumber);
        buttonVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otpEditText.getText().toString().trim();
                if (TextUtils.isEmpty(otp) || otp.length() != 6) {
                    otpEditText.setError("Enter a valid 6 digit OTP");
                    otpEditText.requestFocus();
                    return;
                }
                loading.startloading();
                verifyOtp(otp);
                otpEditText.setText(null);
            }
        });
        buttonResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer();
                buttonResendOtp.setEnabled(false);
                sendOtp(phoneNumber);
            }
        });
    }
    void add_data(String email,String password,String phoneNumber){
        dbhelper helper=new dbhelper(getApplicationContext());
        helper.add_user_info(email,password,phoneNumber);
        Toast.makeText(getApplicationContext(),"Sign-up Successfully",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(),home.class);
        startActivity(intent);
        finish();
    }
    void timer(){
        timerTextView = findViewById(R.id.timerTextView);

        // Total time in milliseconds (e.g., 1 minute = 60000 ms)
        long totalTime = 80000;

        // Countdown interval (1 second = 1000 ms)
        long interval = 1000;

        countDownTimer = new CountDownTimer(totalTime, interval) {
            public void onTick(long millisUntilFinished) {
                // Update the timer text view
                timerTextView.setText(formatTime(millisUntilFinished));
            }

            public void onFinish() {
                // Set the final text after the timer finishes
                timerTextView.setText("00:00");
                buttonResendOtp.setEnabled(true);
                // Perform any action you need to do when the timer finishes
                performEndOfTimerAction();
            }
        };

        countDownTimer.start();
    }
    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void performEndOfTimerAction() {
        // Example action: change the text color of the timerTextView
        timerTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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
                        loading.endloading();
                        // Failed to verify phone number
                        Toast.makeText(otp_verification.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId = s;
                    }
                });
    }

    private void verifyOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        loading.endloading();
                        if (task.isSuccessful()) {
                            // Verification successful, do something
                            add_data(email,password,phoneNumber);
                            Toast.makeText(otp_verification.this, "Verification successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // Verification failed
                            Toast.makeText(otp_verification.this, "Verification failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}