package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail, editPassword;
    private Button logIn, register;
    private TextView createBusinessUser;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this); //Listener for onClick function

        logIn = (Button) findViewById(R.id.loginUser);
        logIn.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);

        createBusinessUser = (TextView) findViewById(R.id.createBusinessAcc);
        createBusinessUser.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register: //TextView Register
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.loginUser:
                loginUser();
                break;
            case R.id.createBusinessAcc:
                startActivity(new Intent(this,RegisterBusinessActivity.class));
        }
    }

    private void loginUser() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please enter a valid email");
            editEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Min password length is 6 characeters!");
            editPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) { //redirect to home
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid().toString();
                    if (user.isEmailVerified()) {
                        DatabaseReference rootRef = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Provider");
                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                int code = 0; //Code == 0 //User || Code == 1 //Provider
                                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                                    if(uid.equals(snapshot.child(dataSnapshot.getKey()).getKey())){
                                        code = 1;
                                    }
                                }
                                if(code == 0) { //User
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class)); //MainActivity
                                } else if (code == 1){ //Provider
                                    startActivity(new Intent(LoginActivity.this, MainActivityProvider.class)); //MainActivity
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}