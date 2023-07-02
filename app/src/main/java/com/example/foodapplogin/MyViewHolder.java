package com.example.foodapplogin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView rimg;
    TextView rtext;
    CardView cardView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        rimg=itemView.findViewById(R.id.pizza);
        rtext=itemView.findViewById(R.id.pizza1);
        cardView=itemView.findViewById(R.id.cardV);

    }
}
