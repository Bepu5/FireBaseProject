package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button save;
    private Button exit;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        userID = user.getUid();

        final EditText password = findViewById(R.id.passwordReset);
        mAuth = FirebaseAuth.getInstance();

        password.setText("********");

        save = findViewById(R.id.savePassword);
        exit = findViewById(R.id.exitPassword);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword(password);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void resetPassword(EditText password) {
        final FirebaseUser user = mAuth.getCurrentUser();

        String textPassword = password.getText().toString().trim();

        if(textPassword.equals("********")){
            Toast.makeText(ChangePassword.this, "Provide a valid password", Toast.LENGTH_LONG).show();
            return;
        }
        if (textPassword.isEmpty()) {
            Toast.makeText(ChangePassword.this, "Password is required", Toast.LENGTH_LONG).show();
            return;
        }
        if (textPassword.length() < 6) {
            Toast.makeText(ChangePassword.this, "Min password length should be 6 characters", Toast.LENGTH_LONG).show();
            return;
        }
        user.updatePassword(textPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ChangePassword.this, "Password changed succesfuly!", Toast.LENGTH_LONG).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChangePassword.this, "Change password failed!", Toast.LENGTH_LONG).show();
            }
        });

    }
}