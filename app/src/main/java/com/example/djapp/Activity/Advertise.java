package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djapp.R;
import com.example.djapp.models.AdvertiseClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Advertise extends AppCompatActivity {

    Button publish;

    FirebaseAuth mAuth;

    TextView price;
    EditText name,phone,creditCard,freeText;

    public static int priceCal = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        price = (TextView) findViewById(R.id.priceAdv);
        name = (EditText) findViewById(R.id.nameAdv);
        phone = (EditText) findViewById(R.id.phoneAdv);
        creditCard = (EditText) findViewById(R.id.creaditAdv);
        freeText = (EditText) findViewById(R.id.freeTextAdv);


        price.setText(""+priceCal+"$");

        publish = (Button)findViewById(R.id.buttonPublish);

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        String nameString = name.getText().toString().trim();
        String phoneString = phone.getText().toString().trim();
        String creditCardString = creditCard.getText().toString().trim();
        String freeTextString = freeText.getText().toString().trim();
        String priceString = "100";
        int priceInt = 100;

       //price.setText(priceInt);



        if (nameString.isEmpty()) {
            name.setError("name is required !");
            name.requestFocus();
            return;
        }

        if (phoneString.isEmpty()) {
            phone.setError("name is required !");
            phone.requestFocus();
            return;
        }

        //maybe check creticard
        if (creditCardString.isEmpty()) {
            creditCard.setError("name is required !");
            creditCard.requestFocus();
            return;
        }

        if (freeTextString.isEmpty()) {
            freeText.setError("name is required !");
            freeText.requestFocus();
            return;
        }

        AdvertiseClass advertiseClass = new AdvertiseClass(nameString, phoneString, freeTextString, creditCardString);
        advertiseClass.setPrice(priceCal);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("advertise").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        myRef.setValue(advertiseClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Advertise.this, "Advertise has been add successfully !", Toast.LENGTH_LONG).show(); //suc register on db
                    priceCal = priceCal +100;
                    Intent intent = new Intent(Advertise.this,AppInterface.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Advertise.this, "Failed to add Advertise ! try again !", Toast.LENGTH_LONG).show();
                }
            }
        });
            }
        });
    }
}