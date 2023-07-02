package com.example.foodapplogin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplogin.R;
import com.example.foodapplogin.models.HomeHormodel;
import com.example.foodapplogin.models.HomeVermodel;

import java.util.ArrayList;
import java.util.List;

public class HomeHoradapter extends RecyclerView.Adapter<HomeHoradapter.ViewHolder> {
    UpdateVerRec updateVerRec;
    Activity activity;
    ArrayList<HomeHormodel> list;
    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHoradapter(UpdateVerRec updateVerRec, Activity activity, ArrayList<HomeHormodel> list) {
        this.updateVerRec = updateVerRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horiz_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
            homeVermodels.add(new HomeVermodel(R.drawable.ranch, "Large Chicken Ranch Pizza", "10:00 - 23:00", "4.8", "180EGP"));
            homeVermodels.add(new HomeVermodel(R.drawable.wings, "Papa John's Wings", "10:00 - 23:00", "4.8", "80EGP"));
            homeVermodels.add(new HomeVermodel(R.drawable.garden, "Large Garden Special Pizza", "10:00 - 23:00", "4.8", "120EGP"));
            homeVermodels.add(new HomeVermodel(R.drawable.bbq, "Large Chicken BBQ Pizza", "10:00 - 23:00", "4.8", "150EGP"));
            updateVerRec.callBack(position, homeVermodels);
            check = false;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.ranch, "Large Chicken Ranch Pizza", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.wings, "Large Chicken Ranch Pizza", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.garden, "Large Garden Special Pizza", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bbq, "Large Chicken BBQ Pizza", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 1) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 2) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 3) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 4) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 5) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 6) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 7) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 8) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                } else if (position == 9) {
                    ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
                    homeVermodels.add(new HomeVermodel(R.drawable.share, "Share Box", "10:00 - 23:00", "4.8", "180EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigtasty, "Big Tasty Beef Combo", "10:00 - 23:00", "4.8", "80EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.bigmac, "Big Mac Combo", "10:00 - 23:00", "4.8", "120EGP"));
                    homeVermodels.add(new HomeVermodel(R.drawable.macroyale, "Mac Royal Combo", "10:00 - 23:00", "4.8", "150EGP"));
                    updateVerRec.callBack(position, homeVermodels);
                }
            }
        });
        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select=false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.def_bg);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pizza);
            name = itemView.findViewById(R.id.pizza1);
            cardView = itemView.findViewById(R.id.cardV);
        }
    }
}

