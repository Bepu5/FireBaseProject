package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    String receiverId;
    String senderRoom, receiverRoom;
    DatabaseReference databaseReferenceSender, databaseReferenceReceiver;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat);
        setContentView(binding.getRoot());

        receiverId=getIntent().getStringExtra("id");
        senderRoom = FirebaseAuth.getInstance().getUid() + receiverId;
        receiverRoom = receiverId + FirebaseAuth.getInstance().getUid();

        messageAdapter = new MessageAdapter(this);
        binding.recycler.setAdapter(messageAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        databaseReferenceSender = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Chats").child(senderRoom);
        databaseReferenceReceiver = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Chats").child(receiverRoom);

        databaseReferenceSender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageAdapter.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Message message = dataSnapshot.getValue(Message.class);
                    messageAdapter.add(message);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String message = binding.messageEd.getText().toString();
                if(message.trim().length() > 0){
                    sendMessage(message);
                }
            }
        });
    }

    private void sendMessage(String message){
        String key = databaseReferenceSender.push().getKey();
        Message messageModel = new Message(key, FirebaseAuth.getInstance().getUid(), message);

        messageAdapter.add(messageModel);
        databaseReferenceSender.child(key).setValue(messageModel);
        databaseReferenceReceiver.child(key).setValue(messageModel);
    }
}