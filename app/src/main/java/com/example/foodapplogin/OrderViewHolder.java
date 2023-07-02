package com.example.foodapplogin;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplogin.R;

import io.reactivex.rxjava3.annotations.NonNull;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView id,status,gate,time,date;
    CardView cardView;
    public OrderViewHolder(@NonNull View ItemView){
        super(ItemView);
        id=ItemView.findViewById(R.id.orderid);
        status=ItemView.findViewById(R.id.orderst);
        gate=ItemView.findViewById(R.id.endpoint);
        time=ItemView.findViewById(R.id.ordertime);
        date=ItemView.findViewById(R.id.orderdate);
        cardView=ItemView.findViewById(R.id.historycv);
    }

}
