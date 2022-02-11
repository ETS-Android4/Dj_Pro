package com.example.djapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.djapp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{


    Context context;

    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user= list.get(position);

        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,email;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tvname);
            phone=itemView.findViewById(R.id.tvphone);
            email=itemView.findViewById(R.id.tvemail);
        }
    }



}
