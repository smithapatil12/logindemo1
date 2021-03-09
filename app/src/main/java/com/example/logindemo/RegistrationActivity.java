package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText username ,userpass,useremail;
    private Button regButton;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth =FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (validate()) {
                   //upload data to database
                   String user_email=useremail.getText().toString().trim();
                   String user_password=userpass.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "registration successful", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                           } else {
                               Toast.makeText(RegistrationActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

               }
            }
        });
        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

    }
    private void setupUIViews(){
        username=(EditText)findViewById(R.id.etUserName);
        userpass=(EditText)findViewById(R.id.etuserPassword);
        useremail=(EditText)findViewById(R.id.etUserEmail);
        regButton=(Button)findViewById(R.id.bntnRegister);
        UserLogin=(TextView)findViewById(R.id.tvuserLogin);
    }
    private Boolean validate(){
        Boolean result= false;

        String name=username.getText().toString();
        String password=userpass.getText().toString();
        String email=useremail.getText().toString();

        if(name.isEmpty() || password.isEmpty() ||email.isEmpty()){
            Toast.makeText(this,"please enter all deatails",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }

}
