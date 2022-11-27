package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseapp.ui.settings.SettingsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity{

    private ImageView back_btn, logout_btn;
    SwitchCompat notifications_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        back_btn = findViewById(R.id.back_btn);
        logout_btn = findViewById(R.id.logout_btn);
        notifications_switch = findViewById(R.id.notificationsSwitch);

        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        notifications_switch.setChecked(sharedPreferences.getBoolean("value",true));

        notifications_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifications_switch.isChecked())
                {
                    // When switch checked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    notifications_switch.setChecked(true);
                }
                else
                {
                    // When switch unchecked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    notifications_switch.setChecked(false);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SettingsActivity.this,LoginActivity.class));
            }
        });
    }
}