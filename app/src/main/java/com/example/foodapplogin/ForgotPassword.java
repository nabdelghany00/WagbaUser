package com.example.foodapplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private Button forgetbtn;
    private EditText recoverymail;
    private FirebaseAuth mAuth;
    TextView backtologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth=FirebaseAuth.getInstance();
        recoverymail=findViewById(R.id.forgotemail);
        forgetbtn=findViewById(R.id.forgbutt1);
        backtologin=findViewById(R.id.bcreate);
        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resetmail=recoverymail.getText().toString().trim();
                if(TextUtils.isEmpty(resetmail)){
                    recoverymail.setError("Mail is Required.");
                    return;
                }else{
                    mAuth.sendPasswordResetEmail(resetmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this, "Please Check your Email to Reset your Password.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginAct.class));
                            }else{
                                Toast.makeText(ForgotPassword.this, "Error Occurred:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginAct.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
