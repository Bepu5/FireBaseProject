package com.example.firebaseapp.uiprovider.notificationsProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebaseapp.R;
import com.example.firebaseapp.databinding.FragmentDashboardBinding;
import com.example.firebaseapp.uiprovider.homeProvider.HomeProviderViewModel;

public class NotificationsProviderFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NotificationsProviderViewModel notificationsProviderViewModel =
                new ViewModelProvider(this).get(NotificationsProviderViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        notificationsProviderViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}