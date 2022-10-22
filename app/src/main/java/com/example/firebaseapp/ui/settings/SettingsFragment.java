package com.example.firebaseapp.ui.settings;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.firebaseapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference button = getPreferenceManager().findPreference("toastMsg");

        if (button != null) {
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    Toast.makeText(getActivity(), "Preference button is clicked",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }


}