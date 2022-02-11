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

public class DjAdapter extends RecyclerView.Adapter<DjAdapter.DjViewHolder> {

    Context context;

    ArrayList<Dj> list;

    public DjAdapter(Context context, ArrayList<Dj> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DjViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.dj_item,parent,false);
        return new DjViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DjViewHolder holder, int position) {
        Dj dj= list.get(position);
        holder.genre.setText(dj.getGenre());
        holder.name.setText(dj.getName());
        holder.phone.setText(dj.getPhone());
        holder.email.setText(dj.getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DjViewHolder extends RecyclerView.ViewHolder{
        TextView genre,name,phone,email;

        public DjViewHolder(@NonNull View itemView) {
            super(itemView);

            genre=itemView.findViewById(R.id.tvgenre);
            name=itemView.findViewById(R.id.tvname);
            phone=itemView.findViewById(R.id.tvphone);
            email=itemView.findViewById(R.id.tvemail);
        }
    }
}
