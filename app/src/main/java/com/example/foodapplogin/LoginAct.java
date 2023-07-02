package com.example.foodapplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAct extends AppCompatActivity {

    EditText email,password;
    Button loginbtn;
    TextView createacc,forget;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser curruser=mAuth.getCurrentUser();
        if(curruser!=null){
            Intent intent=new Intent(getApplicationContext(),NavBar.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        loginbtn=findViewById(R.id.butt1);
        createacc=findViewById(R.id.create);
        progressBar=findViewById(R.id.progressbar1);
        mAuth=FirebaseAuth.getInstance();
        forget=findViewById(R.id.forget);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String mailtxt=email.getText().toString().trim();
                 String passtxt=password.getText().toString().trim();
                if(TextUtils.isEmpty(mailtxt)){
                    email.setError("Email is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(passtxt)){
                    password.setError("Password is Required.");
                    return;
                }
                else if(passtxt.length()<6){
                    password.setError("Password must be 6 Characters or more.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                mAuth.signInWithEmailAndPassword(mailtxt,passtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginAct.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NavBar.class));

                        }else{
                            Toast.makeText(LoginAct.this, "Error Occurred!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterAct.class));
                finish();
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginAct.this,ForgotPassword.class));
            }
        });
    }
}