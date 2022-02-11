package com.example.djapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.djapp.R;
import com.example.djapp.models.Recommendation;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {


    Context context;

    ArrayList<Recommendation> list;

    public RecAdapter(Context context, ArrayList<Recommendation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.rec_item,parent,false);
        return new RecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        Recommendation recommedation= list.get(position);

        holder.name.setText(recommedation.getName());
        holder.comment.setText(recommedation.getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecViewHolder extends RecyclerView.ViewHolder{
        TextView name,comment;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tvname);
            comment=itemView.findViewById(R.id.tvcomment);

        }
    }






}
