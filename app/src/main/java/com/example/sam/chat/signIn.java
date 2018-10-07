package com.example.sam.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class signIn extends AppCompatActivity {
    TextInputLayout phoneNumber;
    TextInputLayout otp;

    FirebaseAuth mAuth;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        phoneNumber = findViewById(R.id.phoneNumber);
        //Try to set phonenumber taken
        otp = findViewById(R.id.OTP);


        findViewById(R.id.Enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();

                findViewById(R.id.OTPrelative).setVisibility(View.VISIBLE);
                findViewById(R.id.enterRelative).setVisibility(View.INVISIBLE);

            }
        });
        findViewById(R.id.inputOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });
    }

    private void verifySignInCode() {
        String code = otp.getEditText().getText().toString();
        if(code.length()<6) {
            otp.setError("Input Correct OTP");
            return;
        }

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
            signInWithPhoneAuthCredential(credential);

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //open new activity
                            Toast.makeText(signIn.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signIn.this, first.class);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid


                            }


                        }
                    }

                });
    }

    private void sendVerificationCode() {

        String phone = phoneNumber.getEditText().getText().toString();


        if (phone.isEmpty()) {
            phoneNumber.setError("Phone Number required");
            phoneNumber.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            phoneNumber.setError("Enter correct number");
            phoneNumber.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    codeSent = s;

                }
            };

}
