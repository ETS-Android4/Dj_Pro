package com.example.djapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.djapp.R;


import java.util.ArrayList;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    Context context;

    ArrayList<Club> list;

    public ClubAdapter(Context context, ArrayList<Club> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.club_item,parent,false);
        return new ClubViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club= list.get(position);
        holder.genre.setText(club.getGenre());
        holder.name.setText(club.getName());
        holder.phone.setText(club.getPhone());
        holder.picture.setText(club.getPicture());
        holder.address.setText(club.getAddress());

        //String imageUri=null;
        //imageUri=club.getPicture();
       //Picasso.get().load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder{
        TextView genre,name,phone,picture,address;
        ImageView imageView; //mine

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);

            genre=itemView.findViewById(R.id.tvgenre);
            name=itemView.findViewById(R.id.tvname);
            phone=itemView.findViewById(R.id.tvphone);
            picture=itemView.findViewById(R.id.tvpicture);
            address=itemView.findViewById(R.id.tvaddress);
            //imageView=itemView.findViewById(R.id.imageView); //mine
        }
    }

}
