package com.example.firebaseapp.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.example.firebaseapp.R;
import com.example.firebaseapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView sign;
    //private EditTextPreference tv_signature;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


       loadSettings();
    }

    private void loadSettings() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String signature = preferences.getString("signature","");
        sign = (TextView) getView().findViewById(R.id.tv_signature);
        sign.setText("Signature: " + signature);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}