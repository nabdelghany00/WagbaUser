package com.example.foodapplogin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapplogin.OrderViewHolder;
import com.example.foodapplogin.models.OrderModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<OrderModel> options;
    FirebaseRecyclerAdapter<OrderModel, OrderViewHolder> adapter;
    DatabaseReference reforder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_history, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView =root.findViewById(R.id.rechistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
        reforder= FirebaseDatabase.getInstance().getReference().child("OrderHistory").child(user.getUid());
        recyclerView.setAdapter(adapter);
        ViewHistory();
        return root;
    }

    private void ViewHistory() {
        options=new FirebaseRecyclerOptions.Builder<OrderModel>().setQuery(reforder,OrderModel.class).build();
        adapter=new FirebaseRecyclerAdapter<OrderModel, OrderViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull OrderModel model) {
                holder.id.setText(model.getId());
                holder.date.setText(model.getDate());
                holder.status.setText(model.getStatus());
                holder.gate.setText(model.getGate());
                holder.time.setText(model.getTime()+",");
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle=new Bundle();
                        bundle.putString("ID",getRef(position).getKey());
                        Fragment track_order =new TrackingFragment();
                        track_order.setArguments(bundle);
                        FragmentTransaction transaction= getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frag1,track_order);
                        transaction.addToBackStack(null);
                        transaction.setReorderingAllowed(true);
                        transaction.commit();
                    }
                });

            }

            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_orderitem,parent,false);
                return new OrderViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}