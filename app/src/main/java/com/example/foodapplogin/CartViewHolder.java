package com.example.foodapplogin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.rxjava3.annotations.NonNull;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView cartimg;
    TextView cartname;
    TextView cartprice;
    TextView cartrating;
    Button cartcancel;
    public CartViewHolder(@NonNull View itemView){
        super(itemView);
        cartimg=itemView.findViewById(R.id.detailed_img);
        cartname=itemView.findViewById(R.id.detailed_txt);
        cartprice=itemView.findViewById(R.id.detailed_price);
        cartrating=itemView.findViewById(R.id.detailed_rating);
        cartcancel=itemView.findViewById(R.id.cancel);
    }
}
