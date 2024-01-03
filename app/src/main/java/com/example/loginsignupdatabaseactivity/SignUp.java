package com.example.loginsignupdatabaseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText username,email,password,contact;
    TextView loginAcc;
    Button btnSignup;

    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.signupUsername);
        email=findViewById(R.id.signupEmail);
        password=findViewById(R.id.signupPassword);
        contact=findViewById(R.id.signupContact);
//for check login or not
        loginAcc=findViewById(R.id.loginAcc);

        //button click
        btnSignup=findViewById(R.id.btnsignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName1 = username.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = password.getText().toString();
                long password1 = Long.parseLong(pass1)  ;
                String contact1 = contact.getText().toString();

                database=FirebaseDatabase.getInstance();
                databaseReference=database.getReference("users");

              HelperClass helperClass=new HelperClass(userName1,email1,pass1,contact1);
              databaseReference.child(userName1).setValue(helperClass);

                Toast.makeText(SignUp.this, "You have sign up successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
        loginAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }
}