package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterBusinessActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView veteris, registerBusinessUser;
    private EditText registerBusinessEmail, registerBusinessUsername, registerCountry, registerPhoneNumber, registerPostalCode, registerBusinessPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);

        mAuth = FirebaseAuth.getInstance();

        veteris = (TextView) findViewById(R.id.veteris);
        veteris.setOnClickListener(this);

        registerBusinessEmail = (EditText) findViewById(R.id.registerBusinessEmail);
        registerBusinessEmail.setOnClickListener(this);

        registerBusinessUsername = (EditText) findViewById(R.id.registerBusinessUsername);
        registerBusinessUsername.setOnClickListener(this);

        registerCountry = (EditText) findViewById(R.id.registerCountry);
        registerCountry.setOnClickListener(this);

        registerPhoneNumber = (EditText) findViewById(R.id.registerPhoneNumber);
        registerPhoneNumber.setOnClickListener(this);

        registerPostalCode = (EditText) findViewById(R.id.registerPostalCode);
        registerPostalCode.setOnClickListener(this);

        registerBusinessPassword = (EditText) findViewById(R.id.registerBusinessPassword);
        registerBusinessPassword.setOnClickListener(this);


        registerBusinessUser = (Button) findViewById(R.id.registerBusinessUser);
        registerBusinessUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.veteris:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.registerBusinessUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String username = registerBusinessUsername.getText().toString().trim();
        String email = registerBusinessEmail.getText().toString().trim();
        String password = registerBusinessPassword.getText().toString().trim();
        String phone = registerPhoneNumber.getText().toString().trim();
        String country = registerCountry.getText().toString().trim();
        String postalCode = registerPostalCode.getText().toString().trim();

        if (username.isEmpty()) {
            registerBusinessUsername.setError("Username is required");
            registerBusinessUsername.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            registerBusinessEmail.setError("Email is required");
            registerBusinessEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerBusinessEmail.setError("Please provide a valid email");
            registerBusinessEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            registerBusinessPassword.setError("Password is required");
            registerBusinessPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerBusinessPassword.setError("Min password length should be 6 characters");
            registerBusinessPassword.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            registerPhoneNumber.setError("Phone is required");
            registerPhoneNumber.requestFocus();
            return;
        }
        if (phone.length() != 9) {
            registerPhoneNumber.setError("Phone must be 9 digits");
            registerPhoneNumber.requestFocus();
            return;
        }
        if (country.isEmpty()) {
            registerCountry.setError("Country is required");
            registerCountry.requestFocus();
            return;
        }
        if (postalCode.isEmpty()) {
            registerPostalCode.setError("Postal code is required");
            registerPostalCode.requestFocus();
            return;
        }
        if (postalCode.length() != 5) {
            registerPostalCode.setError("Postal code must be 5 digits");
            registerPostalCode.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Provider provider = new Provider(username,email,phone,country,postalCode);
                            FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
                                    .child("Provider")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(provider).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) { //We look if user has been inserted in DataBase
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterBusinessActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                user.sendEmailVerification();
                                                startActivity(new Intent(RegisterBusinessActivity.this, LoginActivity.class));
                                            } else {
                                                Toast.makeText(RegisterBusinessActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterBusinessActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}