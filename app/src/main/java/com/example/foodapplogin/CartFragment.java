package com.example.foodapplogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapplogin.adapters.CartAdapter;
import com.example.foodapplogin.models.CartModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    FirebaseRecyclerOptions<CartModel> cartOptions;
    FirebaseRecyclerAdapter<CartModel,CartViewHolder> cartAdapter;
    Button chck;
    DatabaseReference refcart;
    FirebaseUser cartuser;
    RecyclerView cartrec;
    TextView tprice;
    float price=0;
    Boolean f=true;

   /* List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    public CartFragment() {

    }

*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_cart, container, false);
        cartrec=root.findViewById(R.id.cartrec);
        tprice=root.findViewById(R.id.totalprice0);
        chck=root.findViewById(R.id.button);
        cartrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        cartrec.setHasFixedSize(true);
        cartrec.setNestedScrollingEnabled(false);
        cartuser = FirebaseAuth.getInstance().getCurrentUser();
        refcart = FirebaseDatabase.getInstance().getReference().child("Cart").child(cartuser.getUid());
        chck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),checkout.class);
                startActivity(intent);

            }
        });
        onloadDataCart();
        return root;
    }

    private void onloadDataCart() {
        cartOptions=new FirebaseRecyclerOptions.Builder<CartModel>().setQuery(refcart,CartModel.class).build();
        cartAdapter=new FirebaseRecyclerAdapter<CartModel, CartViewHolder>(cartOptions) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull CartModel model) {
                holder.cartname.setText(model.getName());
                holder.cartprice.setText(model.getPrice());
                holder.cartrating.setText(model.getRating());
                Picasso.get().load(model.getImage()).into(holder.cartimg);
                if (f){
                    price=price+Float.valueOf(model.getPrice());
                }
                tprice.setText(String.valueOf(price));
                holder.cartcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refcart.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                price=price-(Float.valueOf(snapshot.child(getRef(holder.getAdapterPosition()).getKey()).child("price").getValue().toString()));
                                f=false;



                                for (DataSnapshot msnap : snapshot.child(getRef(holder.getAdapterPosition()).getKey()).getChildren()) {

                                    msnap.getRef().removeValue();
                                    notifyItemRemoved(holder.getAdapterPosition());
                                }
                                cartrec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });



        }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false);
                return new CartViewHolder(view);
            }
        };
        cartAdapter.startListening();
        cartrec.setAdapter(cartAdapter);
    }
}