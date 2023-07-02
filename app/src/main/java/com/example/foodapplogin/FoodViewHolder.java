package com.example.foodapplogin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FoodViewHolder extends RecyclerView.ViewHolder {
    ImageView foodimg;
    TextView rating,time,price,name;
    CardView cardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        foodimg=itemView.findViewById(R.id.img);
        rating=itemView.findViewById(R.id.rating);
        price=itemView.findViewById(R.id.price);
        name=itemView.findViewById(R.id.tv1);
        time=itemView.findViewById(R.id.timing);
        cardView=itemView.findViewById(R.id.cv1);

    }
}
