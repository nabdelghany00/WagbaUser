package com.example.foodapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapplogin.models.CartModel;
import com.example.foodapplogin.models.OrderModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class checkout extends AppCompatActivity {
    RadioButton cod,cc,gate3,gate4,pm1,pm2;
    Button confirm;
    DatabaseReference history,cart;
    OrderModel order;
    String date,time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        cod=findViewById(R.id.meth1);
        cc=findViewById(R.id.meth2);
        gate3=findViewById(R.id.meth3);
        gate4=findViewById(R.id.meth4);
        pm1=findViewById(R.id.meth5);
        pm2=findViewById(R.id.meth6);
        confirm=findViewById(R.id.confbutt);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cod.isChecked() && !cc.isChecked()){
                    Toast.makeText(checkout.this,"Please Select your Desired Payment Method.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!gate3.isChecked() && !gate4.isChecked()){
                    Toast.makeText(checkout.this, "Please Select your Desired Gate.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pm1.isChecked() && !pm2.isChecked()){
                    Toast.makeText(checkout.this,"Please Select your Delivery Time.",Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String ID=UUID.randomUUID().toString().replace("-","").substring(0,8);
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd/MM/yyyy");
                date=currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
                time=currentTime.format(calendar.getTime());
                order=new OrderModel();
                order.setId("Order ID: "+ID);
                if(pm1.isChecked()){
                    order.setTime("12 PM");
                    try {
                        Date date1=currentTime.parse(time);
                        Date date2=currentTime.parse("10:30:00 AM");
                        Date date3=currentTime.parse("05:00:00 AM");
                        if(date1.before(date3) || date1.after(date2)){
                            Toast.makeText(checkout.this,"Please Reorder between 05:30 AM and 10:30 AM for 12:00 PM Deliveries",Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    order.setTime("3 PM");
                    try {
                        Date date4 =currentTime.parse(time);
                        Date date5 =currentTime.parse("01:30:00 PM");
                        Date date6 =currentTime.parse("05:00:00 AM");
                        if(date4.before(date6) || date4.after(date5)){
                            Toast.makeText(checkout.this,"Please Reorder between 05:30 AM and 01:30 PM for 03:00 PM Deliveries",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                }
                if(gate3.isChecked()){
                    order.setGate("Gate 3");
                }
                else{
                    order.setGate("Gate 4");
                }
                order.setStatus("Ordered");
                Intent intent=getIntent();
                order.setPrice(Float.valueOf(String.valueOf(intent.getFloatExtra("Price",-1))));
                order.setDate(date);
                history=FirebaseDatabase.getInstance().getReference().child("OrderHistory").child(user.getUid()).child(ID);
                history.setValue(order);
                cart=FirebaseDatabase.getInstance().getReference().child("Cart").child(user.getUid());
                cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot msnap: snapshot.getChildren()){
                            msnap.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(checkout.this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), NavBar.class);
                startActivity(intent2);
                finish();



            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), NavBar.class));
        finish();

    }

}