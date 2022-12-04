package com.example.firebaseapp.uiprovider.settingsProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firebaseapp.ProfileProviderActivity;
import com.example.firebaseapp.Provider;
import com.example.firebaseapp.R;
import com.example.firebaseapp.SettingsActivity;

import com.example.firebaseapp.databinding.FragmentSettingsBinding;
import com.example.firebaseapp.ui.settings.SettingsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsProviderFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private ImageView settings;
    private ImageView back_btn;
    private AppCompatButton edit_profile_btn;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel SettingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        settings = root.findViewById(R.id.settings);
        edit_profile_btn = root.findViewById(R.id.edit_profile_btn);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileProviderActivity.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Provider");
        userID = user.getUid();

        final TextView username = root.findViewById(R.id.userName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Provider providerProfile = snapshot.getValue(Provider.class);
                if(providerProfile != null){
                    String username2 = providerProfile.username;

                    username.setText(username2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //final TextView textView = binding.textSettings;
        //SettingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        super.onCreate(savedInstanceState);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    /*@Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference button = getPreferenceManager().findPreference("logoutButton");
        Preference userProfile = getPreferenceManager().findPreference("userProfile");

        if (button != null) {
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                    return true;
                }
            });
        }

        if(userProfile != null){
            userProfile.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    startActivity(new Intent(getActivity(), UserProfileSettingsActivity.class));
                    return true;
                }
            });
        }

    }*/
}