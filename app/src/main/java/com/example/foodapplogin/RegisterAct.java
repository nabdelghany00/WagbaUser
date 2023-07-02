package com.example.foodapplogin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.Locale;

public class RegisterAct extends AppCompatActivity {
    EditText fullname,email,phone,pass,cpass;
    Button registerbtn;
    TextView loginbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    static UserDatabase userDatabase;
    private static UserDao userDao;


    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-2800f-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname=findViewById(R.id.username);
        email=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass1);
        cpass=findViewById(R.id.conf);
        registerbtn=findViewById(R.id.regbtn);
        loginbtn=findViewById(R.id.logintrial);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),NavBar.class));
            finish();
        }

        userDatabase = Room.databaseBuilder(
                getApplicationContext(),
                UserDatabase.class,
                "user_database"
        ).allowMainThreadQueries().build();
        userDao = userDatabase.userDao();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mailtxt=email.getText().toString().trim();
                final String passtxt=pass.getText().toString().trim();
                final String cpasstxt=cpass.getText().toString().trim();
                final String phonetxt=phone.getText().toString().trim();
                final String nametxt=fullname.getText().toString().trim();
                if(TextUtils.isEmpty(mailtxt)){
                    email.setError("Email is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(passtxt)){
                    pass.setError("Password is Required.");
                    return;
                }

                else if(TextUtils.isEmpty(phonetxt)){
                    phone.setError("Phone Number is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(nametxt)){
                    fullname.setError("A Username is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(nametxt)){
                    cpass.setError("A Password Confirmation is Required.");
                    return;
                }
                else if(!passtxt.equals(cpasstxt)){
                    Toast.makeText(RegisterAct.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(passtxt.length()<6){
                    pass.setError("Password must be 6 Characters or more.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //REGISTER USER IN FIREBASE

                mAuth.createUserWithEmailAndPassword(mailtxt,passtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(mailtxt.toLowerCase(Locale.ROOT),nametxt,phonetxt);
                            userDao.insert(user);
                            Toast.makeText(RegisterAct.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NavBar.class));
                        }else{
                            Toast.makeText(RegisterAct.this, "Error Occurred!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginAct.class));
            }
        });
    }

}