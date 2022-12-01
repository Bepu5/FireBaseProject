package com.example.firebaseapp.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.firebaseapp.ChatActivity;
import com.example.firebaseapp.Provider;
import com.example.firebaseapp.R;
import com.example.firebaseapp.User;
import com.example.firebaseapp.databinding.FragmentNotificationsBinding;
import com.example.firebaseapp.ui.home.HomeViewModel;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class NotificationsFragmentProvider extends Fragment {
    private FragmentNotificationsBinding binding;
    private TextView sign;
    //private EditTextPreference tv_signature;

    ArrayList<User> arrayList;
    ListView list;

    FirebaseListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        list = root.findViewById(R.id.providerListNotifications);
        arrayList = new ArrayList<User>();

        initializeListView();

        return root;
    }

    private void initializeListView() {
        Query query = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users");
        //Log in as an User look for Providers
        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.list_view_providers)
                .setQuery(query,User.class)
                .build();

        //Create Adapter for the diferent options
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView userUsername = v.findViewById(R.id.usernameProvider);
                TextView userEmail = v.findViewById(R.id.shortdescrProvider);

                User user = (User) model;
                userUsername.setText(user.username.toString());
                userEmail.setText(user.email.toString());
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ChatActivity.class);
                        intent.putExtra("id", adapter.getRef(position).getKey() + "");
                        startActivity(intent);
                    }
                });
            }
        };
        list.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadSettings();
        adapter.startListening();
    }

    private void loadSettings() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}