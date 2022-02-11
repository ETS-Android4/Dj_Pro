package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.djapp.R;
import com.example.djapp.models.Publish;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AppInterface extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_interface);

        ImageView djs,clubs,advertise,users;
        TextView name,phone,freeText;

        djs = findViewById(R.id.djListDash);
        clubs = findViewById(R.id.clubsListDash);
        advertise = findViewById(R.id.advertiseDash);
        users = findViewById(R.id.userListDash);

        name = findViewById(R.id.nameAdvPub);
        phone = findViewById(R.id.phoneAdvPub);
        freeText = findViewById(R.id.freeTextAdvPub);

        mAuth = FirebaseAuth.getInstance();

        final ArrayList<Publish> list = new ArrayList<Publish>();
        int max = 0;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("advertise");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();

            for (DataSnapshot snapshot1 : snapshot.getChildren())
            {
                Publish publish = snapshot1.getValue(Publish.class);
                list.add(publish);

            }

                Publish maxPublish = list.stream().max(Comparator.comparing(Publish::getPrice)).get();
                name.setText(maxPublish.getName());
                phone.setText(maxPublish.getPhone());
                freeText.setText(maxPublish.getFreeText());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent club = new Intent(AppInterface.this,clublist.class);
                startActivity(club);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(AppInterface.this,reclist.class);
                startActivity(user);
            }
        });

        djs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent djs = new Intent(AppInterface.this,djlist.class);
                startActivity(djs);

            }
        });



        advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent adv = new Intent(AppInterface.this,Advertise.class);
                startActivity(adv);

            }
        });

    }
}