package com.example.firebaseapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.Provider;
import com.example.firebaseapp.ProviderAdapter;
import com.example.firebaseapp.R;
import com.example.firebaseapp.User;
import com.example.firebaseapp.UserAdapter;
import com.example.firebaseapp.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {
    DatabaseReference databaseReferenceProvider;
    DatabaseReference databaseReferenceUser;
    UserAdapter userAdapter;
    ProviderAdapter providerAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReferenceProvider = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Provider");
        databaseReferenceUser = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");

        //1. If log in is a Provider
        //  1.1 Yes - Look all Users
        //  2.2 No - Look all Providers
        databaseReferenceProvider.addValueEventListener(new ValueEventListener() { //Search for Provider
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.child(FirebaseAuth.getInstance().getUid()).getValue(Provider.class) == null){ //Log in as an User, look for Providers
                    providerAdapter =new ProviderAdapter(getContext());
                    recyclerView.setAdapter(providerAdapter);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                        Provider provider = snapshot.child(dataSnapshot.getKey()).getValue(Provider.class);
                        providerAdapter.add(provider);
                    }
                }
                else{
                    databaseReferenceUser.addValueEventListener(new ValueEventListener() { //Search For User
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            userAdapter =new UserAdapter(getContext());
                            recyclerView.setAdapter(userAdapter);
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                                String uid = snapshot.child(dataSnapshot.getKey()) + "";
                                if (!uid.equals(FirebaseAuth.getInstance().getUid() + "")) {
                                    User user = snapshot.child(dataSnapshot.getKey()).getValue(User.class);
                                    userAdapter.add(user);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}