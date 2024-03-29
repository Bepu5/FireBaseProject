package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context context;
    private List<Message> messages;

    public MessageAdapter(Context context) {
        this.context = context;
        messages=new ArrayList<>();
    }

    public void add(Message userModel){
        messages.add(userModel);
        notifyDataSetChanged();
    }

    public void clear(){
        messages.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message messageList=messages.get(position);
        holder.msg.setText(messageList.getMessage());
        if(messageList.getSenderId().equals(FirebaseAuth.getInstance().getUid())){
            holder.main.setBackgroundColor(context.getResources().getColor(R.color.purple_500));
            holder.msg.setTextColor(context.getResources().getColor(R.color.white));
        } else{
            holder.main.setBackgroundColor(context.getResources().getColor(R.color.purple_700));
            holder.msg.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView msg;
        private LinearLayout main;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            msg=itemView.findViewById(R.id.message);
            main=itemView.findViewById(R.id.mainMessageLayout);
        }
    }
}
