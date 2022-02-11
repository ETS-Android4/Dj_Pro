package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.djapp.R;
import com.example.djapp.models.RecAdapter;
import com.example.djapp.models.Recommendation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reclist extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    RecAdapter recAdapter;
    ArrayList<Recommendation> r_list;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclist);

        button = findViewById(R.id.buttonAddComment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reclist.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.reclist);
        database = FirebaseDatabase.getInstance().getReference("recs");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        r_list = new ArrayList<>();
        recAdapter = new RecAdapter(this, r_list);
        recyclerView.setAdapter(recAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Recommendation recommedation = dataSnapshot.getValue(Recommendation.class);
                    r_list.add(recommedation);
                }
                recAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }
}