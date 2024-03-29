package com.example.firebaseapp.uiprovider.homeProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebaseapp.R;
import com.example.firebaseapp.databinding.FragmentDashboardBinding;
import com.example.firebaseapp.uiprovider.notificationsProvider.NotificationsProviderViewModel;

public class HomeProviderFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeProviderViewModel homeProviderViewModel =
                new ViewModelProvider(this).get(HomeProviderViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        homeProviderViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}