package com.example.cofechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginPageActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;

    TextView goToCreateAccount;
    Button mSignInAccount;
    TextInputEditText mEmail, mPassword;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        goToCreateAccount = findViewById(R.id.accessToCreateAccount);
        mSignInAccount = findViewById(R.id.buttonLogIn);
        mEmail = findViewById(R.id.logInEmail);
        mPassword = findViewById(R.id.logInPassword);

        mFirebaseAuth = FirebaseAuth.getInstance();


        mSignInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Objects.requireNonNull(mEmail.getText()).toString();
                password = Objects.requireNonNull(mPassword.getText()).toString();

                logInWithEmailAndPassword();
            }
        });

        goToCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lvIntent = new Intent(LoginPageActivity.this, MainActivity.class);
                startActivity(lvIntent);
            }
        });
    }


    public void logInWithEmailAndPassword(){
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Intent lvIntent = new Intent(LoginPageActivity.this, ChatActivity.class);
                            startActivity(lvIntent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginPageActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
