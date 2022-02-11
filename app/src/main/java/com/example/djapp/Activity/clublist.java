package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.djapp.R;
import com.example.djapp.models.Club;
import com.example.djapp.models.ClubAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class clublist extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    ClubAdapter clubAdapter;
    ArrayList<Club> c_list;

    FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clublist);

        recyclerView = findViewById(R.id.clubList);
        database = FirebaseDatabase.getInstance().getReference("clubs");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

      //mStorage=FirebaseStorage.getInstance();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        c_list = new ArrayList<>();
        clubAdapter = new ClubAdapter(this, c_list);
        recyclerView.setAdapter(clubAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Club club = dataSnapshot.getValue(Club.class);
                    c_list.add(club);
                }
                clubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }
}