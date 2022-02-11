package com.example.djapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djapp.Fragments.mainFragment;
import com.example.djapp.Fragments.registerFragment;
import com.example.djapp.R;
import com.example.djapp.models.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private FirebaseAuth mAuth;
    //private Spinner spinnerAccount;

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone, editTextId, editTextGenre; //for reg
    private Spinner spinnerTextAccount;  //for reg
    private ProgressBar progressBar,progressBarLog;

    private EditText emailTextLog,passwordTextLog; //for logon
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //spinnerAccount = findViewById(R.id.spinnerAcc);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerAccount.setAdapter(adapter);

        //spinnerAccount.setOnItemSelectedListener(this);


        mAuth = FirebaseAuth.getInstance();
    }


    public void regFunc() {

        /*String email = ((EditText) findViewById(R.id.emailReg)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordReg)).getText().toString();
        String name = ((EditText) findViewById(R.id.nameReg)).getText().toString();
        String account = ((Spinner) findViewById(R.id.spinnerAcc)).getSelectedItem().toString();
        String phone = ((EditText) findViewById(R.id.phoneReg)).getText().toString();*/

        editTextEmail = (EditText) findViewById(R.id.emailReg);
        editTextGenre = (EditText) findViewById(R.id.genreReg);
        editTextPassword = (EditText) findViewById(R.id.passwordReg);
        editTextName = (EditText) findViewById(R.id.nameReg);
        editTextPhone = (EditText) findViewById(R.id.phoneReg);
        spinnerTextAccount = (Spinner) findViewById(R.id.spinnerAcc);
        editTextId = (EditText) findViewById(R.id.idReg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String email = editTextEmail.getText().toString().trim();
        String genre = editTextGenre.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String account = spinnerTextAccount.getSelectedItem().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String id = editTextId.getText().toString().trim();

        if (id.isEmpty()) {
            editTextId.setError("ID is required !");
            editTextId.requestFocus();
            return;
        }

        if (id.length() != 9) {
            editTextId.setError("ID length must be 9 characters !");
            editTextId.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required !");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email !");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Min password length must be 6 characters !");
            editTextPassword.requestFocus();
            return;
        }


        if (phone.isEmpty()) {
            editTextPhone.setError("Phone is required !");
            editTextPhone.requestFocus();
            return;
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            editTextPhone.setError("Please provide valid phone !");
            editTextPhone.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Full name is required !");
            editTextName.requestFocus();
            return;
        }

        if (genre.isEmpty()) {
            editTextGenre.setError("Favorite Genre is required !");
            editTextGenre.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Person person = new Person(name, email, phone, account, id, genre);
                            DatabaseReference myRef;
                            FirebaseDatabase database = FirebaseDatabase.getInstance();


                            //Toast.makeText(MainActivity.this, account, Toast.LENGTH_LONG).show();
                            if (account.equals("Dj"))
                            {
                                // Write a message to the database
                                //DatabaseReference myRef = database.getReference("users").child(person.getId());
                                 myRef = database.getReference("djs").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                            }
                            else
                            {
                                // Write a message to the database
                                //DatabaseReference myRef = database.getReference("users").child(person.getId());
                                 myRef = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            }

                            myRef.setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "User has been registered successfully !", Toast.LENGTH_LONG).show(); //suc register on db
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Failed to register ! try again !", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }


                            });

                        }
                    }
                });
    }




  /*  public void addData() {

        String name = ((EditText) findViewById(R.id.nameReg)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailReg)).getText().toString();
        // String password = ((EditText) findViewById(R.id.pass)).getText().toString();
        String account = ((Spinner) findViewById(R.id.spinnerAcc)).getSelectedItem().toString();
        String phone = ((EditText) findViewById(R.id.phoneReg)).getText().toString();
        String id = ((EditText) findViewById(R.id.idReg)).getText().toString();

        Person person = new Person (name,email,phone,account,id);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(person.getId());

        myRef.setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "User has been registered successfully !" , Toast.LENGTH_LONG).show(); //suc register on db
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to register ! try again !" , Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });



    }
*/

    public void loginFunc() {

        emailTextLog = (EditText) findViewById(R.id.emailText);
        passwordTextLog= (EditText) findViewById(R.id.passwordText);
        progressBarLog = (ProgressBar) findViewById(R.id.progressBarLog);

        String emailLog = emailTextLog.getText().toString().trim();
        String passwordLog = passwordTextLog.getText().toString().trim();

        if (emailLog.isEmpty()) {
            emailTextLog.setError("Email is required !");
            emailTextLog.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailLog).matches()) {
            emailTextLog.setError("Please provide valid email !");
            emailTextLog.requestFocus();
            return;
        }

        if (passwordLog.isEmpty()) {
            passwordTextLog.setError("Password is required !");
            passwordTextLog.requestFocus();
            return;
        }

        if (passwordLog.length() < 6) {
            passwordTextLog.setError("Min password length must be 6 characters !");
            passwordTextLog.requestFocus();
            return;
        }

        progressBarLog.setVisibility(View.VISIBLE);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("users").child(emailLog);
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(emailLog, passwordLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {

                    //Toast.makeText(MainActivity.this, "Login succeed !", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(getApplicationContext(), mainFragment.class);
                    //callback.onComplete(true);

                    //startActivity(new Intent(MainActivity.this, registerFragment.class));
                    //Intent intent = new Intent(getApplicationContext(),registerFragment.class);
                    //startActivity(intent);


                    //startActivity(new Intent(getApplicationContext(),registerFragment.class));

                    //setContentView(R.layout.activity_main);
                    //Navigation.findNavController(main).navigate(R.id.action_mainFragment_to_registerFragment);

                    if (mAuth.getCurrentUser().isEmailVerified())
                    {
                        //startActivity(new Intent(getApplicationContext(),registerFragment.class));
                        Toast.makeText(MainActivity.this, "Login succeed !", Toast.LENGTH_LONG).show();
                        Intent app = new Intent(MainActivity.this,AppInterface.class);
                        startActivity(app);

                    }
                    else
                    {
                        mAuth.getCurrentUser().sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify your account !", Toast.LENGTH_LONG).show();
                    }



                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login failed ! try again !", Toast.LENGTH_LONG).show();

                }

            }
        });
        progressBarLog.setVisibility(View.GONE);
    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedAccount = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, selectedAccount, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


