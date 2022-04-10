package com.mouttaqui.quizapp_o41;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
//Step 1 : Declaration
EditText etLogin, etPassword;
Button bLogin;
TextView tvRegister;
//ProgressBar progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //Step 2: Recuperation des ids
        etLogin= (EditText) findViewById(R.id.etLogin);
        etPassword= (EditText) findViewById(R.id.etPassword);
        bLogin= (Button) findViewById(R.id.bLogin);
        tvRegister= (TextView) findViewById(R.id.tvRegister);
        //Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginUserAccount();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
    }

    private void loginUserAccount() {

        // show the visibility of progress bar to show loading
        //progressbar.setVisibility(View.VISIBLE);

        String login = etLogin.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (login.isEmpty()) {
            etLogin.setError("Please enter your email! ");
            etLogin.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()) {
            etLogin.setError("Please provide a valid email!");
            etLogin.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            etPassword.setError("Please enter the password! ");
            etPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(login,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Welcome!",
                            Toast.LENGTH_LONG)
                            .show();
                    // hide the progress bar
                    //progressbar.setVisibility(View.GONE);
                    Intent intent
                            = new Intent(MainActivity.this,
                            Quiz_topic.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Login failed! Please try again!",
                            Toast.LENGTH_LONG)
                            .show();
                    // hide the progress bar
                    //progressbar.setVisibility(View.GONE);
                }
            }
        });

    }





}