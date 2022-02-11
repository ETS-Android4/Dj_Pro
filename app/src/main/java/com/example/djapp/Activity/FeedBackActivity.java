package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.djapp.R;
import com.example.djapp.models.AdvertiseClass;
import com.example.djapp.models.Recommendation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBackActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextName,editTextRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        Button button = findViewById(R.id.buttonSendFeedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextName = (EditText) findViewById(R.id.nameFeedback);
                editTextRec = (EditText) findViewById(R.id.feedbackFeedback);

                String name = editTextName.getText().toString().trim();
                String recommendation = editTextRec.getText().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("Full name is required !");
                    editTextName.requestFocus();
                    return;
                }

                if (recommendation.isEmpty()) {
                    editTextRec.setError("Full name is required !");
                    editTextRec.requestFocus();
                    return;
                }

                Recommendation recommendationClass = new Recommendation(name,recommendation);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //DatabaseReference myRef = database.getReference("recs").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DatabaseReference myRef = database.getReference("recs").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                myRef.setValue(recommendationClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FeedBackActivity.this, "Advertise has been add successfully !", Toast.LENGTH_LONG).show(); //suc register on db
                            Intent intent = new Intent(FeedBackActivity.this,reclist.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(FeedBackActivity.this, "Failed to add Advertise ! try again !", Toast.LENGTH_LONG).show();
                        }


                    }
                });


            }
        });


    }
}