package com.example.foodapplogin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapplogin.models.CartModel;
import com.example.foodapplogin.models.Dish;
import com.example.foodapplogin.models.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class fragment1 extends Fragment {
    private BottomSheetDialog bottomSheetDialog;
        String RID;
        RecyclerView homeHorizontalRec,homeVerticalRec;
        FirebaseRecyclerOptions<Dish> DishOptions;
        FirebaseRecyclerAdapter<Dish,FoodViewHolder> DishAdapter;
        FirebaseRecyclerOptions<Restaurant> options;
        FirebaseRecyclerAdapter<Restaurant,MyViewHolder> adapter;
        DatabaseReference ResRef,DishRef,refcart;
        CartModel cartModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_fragment1, container, false);
        homeHorizontalRec=root.findViewById(R.id.recycle);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
        ResRef= FirebaseDatabase.getInstance().getReference().child("Restaurants");

        homeVerticalRec=root.findViewById(R.id.vertical_c);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        //homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);
        DishRef= FirebaseDatabase.getInstance().getReference().child("Dishes");

        loadrest();

        ///////////Horizontal RV
       /** homeHormodelList=new ArrayList<>();
        homeHormodelList.add(new HomeHormodel(R.drawable.papa,"Papa John's"));
        homeHormodelList.add(new HomeHormodel(R.drawable.mac,"MacDonald's"));
        homeHormodelList.add(new HomeHormodel(R.drawable.krispy,"Krispy Kreme"));
        homeHormodelList.add(new HomeHormodel(R.drawable.pizzahut,"Pizza Hut"));
        homeHormodelList.add(new HomeHormodel(R.drawable.starbucks,"Starbucks"));
        homeHormodelList.add(new HomeHormodel(R.drawable.arbys,"Arbys"));
        homeHormodelList.add(new HomeHormodel(R.drawable.burgerking,"Burger King"));
        homeHormodelList.add(new HomeHormodel(R.drawable.dd,"Dunkin'Donuts"));
        homeHormodelList.add(new HomeHormodel(R.drawable.kfc,"KFC"));
        homeHormodelList.add(new HomeHormodel(R.drawable.cinnabon,"Cinnabon"));
        homeHoradapter=new HomeHoradapter(this,getActivity(),homeHormodelList);
        homeHorizontalRec.setAdapter(homeHoradapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        ///////////Vertical RV
        homeVermodelList=new ArrayList<>();

        homeVeradapter=new HomeVeradapter(getActivity(),homeVermodelList);
        homeVerticalRec.setAdapter(homeVeradapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        */
        return root;
    }
    private void loadrest(){
        options=new FirebaseRecyclerOptions.Builder<Restaurant>().setQuery(ResRef,Restaurant.class).build();

        adapter=new FirebaseRecyclerAdapter<Restaurant, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Restaurant model) {
                holder.rtext.setText(model.getResName());
                Picasso.get().load(model.getResImg()).into(holder.rimg);
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RID=adapter.getRef(holder.getAdapterPosition()).getKey();
                        LoadFood(RID);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.horiz_slider,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        homeHorizontalRec.setAdapter(adapter);
    }
    private void LoadFood(String RID){
        DishOptions=new FirebaseRecyclerOptions.Builder<Dish>().setQuery(DishRef.orderByChild("RID").equalTo(RID),Dish.class).build();
        DishAdapter=new FirebaseRecyclerAdapter<Dish, FoodViewHolder>(DishOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Dish model) {
                holder.name.setText(model.getDName());
                holder.time.setText(model.getDTime());
                holder.rating.setText(model.getDRating());
                holder.price.setText(model.getDPrice());
                Picasso.get().load(model.getDimg()).into(holder.foodimg);
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog=new BottomSheetDialog(getContext(),R.style.BottSheetTheme);
                        View sheetView=LayoutInflater.from(getContext()).inflate(R.layout.botsheetdialog,null);
                        sheetView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cartModel=new CartModel();
                                cartModel.setName(model.getDName());
                                cartModel.setPrice(model.getDPrice());
                                cartModel.setImage(model.getDimg());
                                cartModel.setRating(model.getDRating());
                                FirebaseUser cartuser;
                                cartuser= FirebaseAuth.getInstance().getCurrentUser();
                                refcart=FirebaseDatabase.getInstance().getReference().child("Cart");
                                refcart.child(cartuser.getUid()).child(getRef(position).getKey()).setValue(cartModel);
                                Toast.makeText(getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                            }
                        });
                        ImageView bottomImg=sheetView.findViewById(R.id.bottomimg);
                        TextView bottomName=sheetView.findViewById(R.id.bottomname);
                        TextView bottomRating=sheetView.findViewById(R.id.bottomrating);
                        TextView bottomPrice=sheetView.findViewById(R.id.bottomprice);
                        TextView bottomTime=sheetView.findViewById(R.id.bottomtiming);
                        bottomName.setText(model.getDName());
                        bottomPrice.setText(model.getDPrice());
                        bottomRating.setText(model.getDRating());
                        bottomTime.setText(model.getDTime());
                        Picasso.get().load(model.getDimg()).into(bottomImg);
                        bottomSheetDialog.setContentView(sheetView);
                        bottomSheetDialog.show();

                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_slider, parent, false);
                return new FoodViewHolder(view);
            }
        };
        DishAdapter.startListening();
        homeVerticalRec.setAdapter(DishAdapter);

    }


}