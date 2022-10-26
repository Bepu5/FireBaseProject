package com.example.firebaseapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.MyViewHolder> {

    private Context context;
    private List<Provider> providerList;

    public ProviderAdapter(Context context) {
        this.context = context;
        providerList=new ArrayList<>();
    }

    public void add(Provider provider){
        providerList.add(provider);
        notifyDataSetChanged();
    }

    public void clear(){
        providerList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false); //user_row
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Provider provider = providerList.get(position);
        holder.name.setText(provider.username);
        holder.email.setText(provider.email);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("id", provider.id+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name, email;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.userName);
            email=itemView.findViewById(R.id.userEmail);
        }

    }
}
