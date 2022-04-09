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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //Step 1 : Declaration
    EditText etName,etLogin,etPassword,etConfirm;
    Button bSign;
    TextView tvSignIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //Step 2 : Recuperation des ids
        etName = (EditText) findViewById(R.id.etName);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirm = (EditText) findViewById(R.id.etConfirm);
        bSign = (Button) findViewById(R.id.bSign);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);

        //Association des listeners
        bSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });

    }
        private void registerNewUser() {
           // show the visibility of progress bar to show loading
           // progressBar.setVisibility(View.VISIBLE);

            String name = etName.getText().toString().trim();
            String login = etLogin.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String confirm = etConfirm.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("A full name is required! ");
                etName.requestFocus();
                return;
            }
            if (login.isEmpty()) {
                etLogin.setError("An email is required! ");
                etLogin.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()) {
                etLogin.setError("Please provide a valid email!");
                etLogin.requestFocus();
                return;
            }
            if (pass.isEmpty()) {
                etPassword.setError("A password is required! ");
                etPassword.requestFocus();
                return;
            }
            if (pass.length() < 6) {
                etPassword.setError("Minimum password length should be 6 characters!");
                etPassword.requestFocus();
                return;
            }

            if (confirm.isEmpty()) {
                etConfirm.setError("Please confirm your password! ");
                etConfirm.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(login, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "User has been registered successfully!",
                                        Toast.LENGTH_SHORT).show();
                                // hide the progress bar
                               // progressBar.setVisibility(View.GONE);
                                Intent intent
                                        = new Intent(Register.this,
                                        MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Failed to register!",
                                        Toast.LENGTH_SHORT).show();
                                // hide the progress bar
                               // progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

        }
}









