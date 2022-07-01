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

public class Register extends AppCompatActivity {
    ImageView gotologin;
    EditText name,regpass,regeamil;
    Button save;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        gotologin=findViewById(R.id.gotologin);
        name=findViewById(R.id.name);
        regeamil=findViewById(R.id.regemail);
        regpass=findViewById(R.id.respass);
        save=findViewById(R.id.register);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String nam = name.getText().toString();
        String password = regpass.getText().toString();
        String email = regeamil.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Pass is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Usermodel usermodel=new Usermodel(nam,email,password);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Admin").child(id).setValue(usermodel);

                            Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}