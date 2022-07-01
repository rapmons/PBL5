package com.niranjan.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    ImageView gotoreg;
    EditText email,password;
    Button login;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        gotoreg=findViewById(R.id.gotoreg);


        email=findViewById(R.id.logemail);
        password=findViewById(R.id.logpass);
        login=findViewById(R.id.loginbtn);
        gotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }
    private void loginUser() {
        String useremail =email.getText().toString();
        String userpass = password.getText().toString();
        if (TextUtils.isEmpty(useremail))
        {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(userpass))
        {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(useremail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MapsActivity.class));
                    finish();
                }
            }
        });
    }
}