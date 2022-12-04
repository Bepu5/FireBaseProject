package com.example.firebaseapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.firebaseapp.databinding.ActivityMainProviderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityProvider extends AppCompatActivity {

    private ActivityMainProviderBinding binding;
    private TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     BottomNavigationView navView = findViewById(R.id.nav_view_provider);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_configuration)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_provider);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewProvider, navController);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);

        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String signature = preferences.getString("signature","");
        sign = (TextView) findViewById(R.id.tv_signature);
        sign.setText("Signature: " + signature);*/






    }
}
