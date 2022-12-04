package com.example.firebaseapp.uiprovider.dashboardProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebaseapp.R;
import com.example.firebaseapp.databinding.FragmentDashboardBinding;
import com.example.firebaseapp.uiprovider.dashboardProvider.DashboardProviderViewModel;

public class DashboardProviderFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard_provider, container, false);

        DashboardProviderViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardProviderViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}