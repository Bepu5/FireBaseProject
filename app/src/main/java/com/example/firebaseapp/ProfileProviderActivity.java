package com.example.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ProfileProviderActivity extends AppCompatActivity {

    private ImageView back_btn;
    private AppCompatButton username_btn, email_btn, pwd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        back_btn = findViewById(R.id.back_btn);
        username_btn = findViewById(R.id.edit_username_btn);
        email_btn = findViewById(R.id.edit_email_btn);
        pwd_btn =findViewById(R.id.edit_password_btn);

        username_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileProviderActivity.this, ProviderProfileSettingsActivity.class);
                startActivity(intent);
            }
        });

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileProviderActivity.this, ChangeEmailProvider.class);
                startActivity(intent);
            }
        });

        pwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileProviderActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}