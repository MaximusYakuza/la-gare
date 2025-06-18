package com.example.lagare.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lagare.R;
import com.example.lagare.adapters.OrderHistoryAdapter;
import com.example.lagare.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    OrderHistoryAdapter adapter;
    List<CartModel> historyList;
    DatabaseReference ordersRef;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        recyclerView = view.findViewById(R.id.history_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historyList = new ArrayList<>();
        adapter = new OrderHistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);

        loadHistory();

        return view;
    }

    private void loadHistory() {
        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                historyList.clear();
                for (DataSnapshot orderSnap : snapshot.getChildren()) {
                    for (DataSnapshot itemSnap : orderSnap.getChildren()) {
                        CartModel item = itemSnap.getValue(CartModel.class);
                        historyList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
