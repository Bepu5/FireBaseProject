package com.example.firebaseapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.firebaseapp.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatUserActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    String senderId, receiverId;
    String senderRoom, receiverRoom;
    DatabaseReference databaseReferenceSender, databaseReferenceReceiver, databaseChatSender;
    MessageAdapter messageAdapter;
    String chatid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat);
        setContentView(binding.getRoot());

        senderId = FirebaseAuth.getInstance().getUid();
        receiverId=getIntent().getStringExtra("id");


        messageAdapter = new MessageAdapter(this);
        binding.recycler.setAdapter(messageAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        databaseChatSender = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Chats");

        databaseReferenceReceiver = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Provider").child(receiverId).child("chat");


        databaseReferenceSender = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("chat");
        chatid = databaseReferenceSender.push().getKey();
        databaseReferenceSender.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                    Chat cha = dataSnapshot.getValue(Chat.class);
                    if(receiverId.equals(cha.getUserName())){
                        Chat value = dataSnapshot.getValue(Chat.class);
                        chatid = value.getChatId();
                        databaseChatSender = databaseChatSender.child(value.getChatId());
                        databaseChatSender.addValueEventListener(new ValueEventListener() {
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
                    }
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
        //Create Message on Chat
        Chat chatSender = new Chat(chatid, receiverId);
        databaseReferenceSender.child(chatid).setValue(chatSender);

        databaseChatSender = FirebaseDatabase.getInstance("https://missatgeria-serveis-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Chats").child(chatid);
        String id_message = databaseChatSender.push().getKey();
        Message messageModel = new Message(FirebaseAuth.getInstance().getUid(), senderId, message);
        messageAdapter.add(messageModel);
        databaseChatSender.child(id_message).setValue(messageModel);

        Chat chatReceiver = new Chat(chatid, senderId);
        databaseReferenceReceiver.child(chatid).setValue(chatReceiver);
    }
}