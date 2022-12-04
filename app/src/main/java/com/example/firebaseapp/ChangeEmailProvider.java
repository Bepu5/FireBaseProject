package com.example.firebaseapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeEmailProvider extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button save;
    private Button exit;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Provider");
        userID = user.getUid();

        final EditText email = findViewById(R.id.emailReset);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Provider providerProfile = snapshot.getValue(Provider.class);
                if(providerProfile != null){
                    String emailUser = providerProfile.email;

                    email.setText(emailUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAuth = FirebaseAuth.getInstance();



        save = findViewById(R.id.saveEmail);
        exit = findViewById(R.id.exitEmail);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEmail(email);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void resetEmail(EditText email) {
        final FirebaseUser user = mAuth.getCurrentUser();

        String emailText = email.getText().toString().trim();

        if (emailText.isEmpty()) {
            Toast.makeText(ChangeEmailProvider.this, "Email is required", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            Toast.makeText(ChangeEmailProvider.this, "Please provide a valid email", Toast.LENGTH_LONG).show();
            return;
        }
        user.updateEmail(emailText).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                reference.child(userID).child("email").setValue(emailText);
                Toast.makeText(ChangeEmailProvider.this, "Email changed succesfuly, verify your email!", Toast.LENGTH_LONG).show();
                user.sendEmailVerification();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChangeEmailProvider.this, "Change email failed!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
