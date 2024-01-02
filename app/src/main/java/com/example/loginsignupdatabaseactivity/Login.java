package com.example.loginsignupdatabaseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText uname,pass;
    Button btnLogin;
    TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname=findViewById(R.id.loginUsername);
        pass=findViewById(R.id.loginPassword);

        btnLogin=findViewById(R.id.btnlogin);
        signup=findViewById(R.id.signupAcc);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateUsername() | !validatePassword())
                {

                }
                else
                {
                    checkUser();
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(Login.this,SignUp.class);
                startActivity(signup);
            }
        });
    }
    public boolean validateUsername()
    {
        String val=uname.getText().toString();

        if(val.isEmpty())
        {
            uname.setError("Username cannot be empty");
            return false;
        }
        else {
            uname.setError(null);
            return true;
        }
    }

        public boolean validatePassword()
        {
            String val1 = pass.getText().toString();
            if (val1.isEmpty()) {
                pass.setError("password cannot be empty");
                return false;
            }
            else {
                pass.setError(null);
                return true;
            }
        }
        public void checkUser()
        {
            String un=uname.getText().toString();
            String pa=pass.getText().toString();

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
            Query checkUserDatabase=reference.orderByChild("username").equalTo(un);

            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        uname.setError(null);
                        String passwordDB=snapshot.child(un).child("password").getValue(String.class);
                        if(!Objects.equals(passwordDB,pa))
                        {
                            uname.setError(null);
                            Intent i1=new Intent(Login.this,Welcome.class);
                            startActivity(i1);
                        }
                        else {
                            pass.setError("invalid password");
                            pass.requestFocus();
                        }
                    }
                    else {
                        uname.setError("user does not exist");
                        uname.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

}