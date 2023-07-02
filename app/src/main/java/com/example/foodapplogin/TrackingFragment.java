package com.example.foodapplogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrackingFragment extends Fragment {
    TextView tv;
    String orderid,st;
    ImageView first,second,third,fourth;
    Button cancelbtn;
    DatabaseReference reforder;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_tracking, container, false);
        user= FirebaseAuth.getInstance().getCurrentUser();
        tv=root.findViewById(R.id.trackdate);
        first=root.findViewById(R.id.img2);
        second=root.findViewById(R.id.img3);
        third=root.findViewById(R.id.img5);
        fourth=root.findViewById(R.id.img6);
        cancelbtn=root.findViewById(R.id.cancebtn);
        Bundle bundle=this.getArguments();
        if(bundle !=null) {

            orderid = bundle.getString("ID");
            reforder = FirebaseDatabase.getInstance().getReference("OrderHistory").child(user.getUid()).child(orderid);


            cancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reforder.child("status").setValue("Cancelled");
                    Toast.makeText(getActivity(), "Order Cancelled Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getContext(), NavBar.class);
                    startActivity(intent2);

                }
            });
            reforder.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    st=snapshot.child("status").getValue().toString();

                    if(st.equalsIgnoreCase("Ordered")){

                        first.setColorFilter(ContextCompat.getColor(getContext(), R.color.reddish), android.graphics.PorterDuff.Mode.MULTIPLY);
                        second.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);
                        third.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);
                        fourth.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);

                        cancelbtn.setEnabled(true);
                        cancelbtn.setBackgroundColor(Color.parseColor("#ff9d00"));

                    }
                    else if(st.equalsIgnoreCase("In Progress")){

                        second.setColorFilter(ContextCompat.getColor(getContext(), R.color.reddish), android.graphics.PorterDuff.Mode.MULTIPLY);


                        cancelbtn.setEnabled(false);
                        cancelbtn.setBackgroundColor(Color.parseColor("#8b94a3"));
                    }
                    else if(st.equalsIgnoreCase("Out for Delivery")){

                        third.setColorFilter(ContextCompat.getColor(getContext(), R.color.reddish), android.graphics.PorterDuff.Mode.MULTIPLY);


                        cancelbtn.setEnabled(false);
                        cancelbtn.setBackgroundColor(Color.parseColor("#8b94a3"));
                    }
                     else if(st.equalsIgnoreCase("Delivered")){

                        fourth.setColorFilter(ContextCompat.getColor(getContext(), R.color.reddish), android.graphics.PorterDuff.Mode.MULTIPLY);

                        cancelbtn.setEnabled(false);
                        cancelbtn.setBackgroundColor(Color.parseColor("#8b94a3"));
                    }
                   else{
                        first.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);
                        second.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);
                        third.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);
                        fourth.setColorFilter(ContextCompat.getColor(getContext(), R.color.mediumGrey), android.graphics.PorterDuff.Mode.MULTIPLY);

                        cancelbtn.setEnabled(false);
                        cancelbtn.setBackgroundColor(Color.parseColor("#8b94a3"));
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }





            return root;
    }
}