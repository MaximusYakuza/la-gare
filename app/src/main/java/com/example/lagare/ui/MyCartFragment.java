package com.example.lagare.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.R;
import com.example.lagare.activities.OrderSummaryActivity;
import com.example.lagare.adapters.CartAdapter;
import com.example.lagare.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCartFragment extends Fragment {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<CartModel> cartList;
    TextView totalAmountText;
    Button placeOrderButton;

    DatabaseReference cartRef, ordersRef, userRef;
    String uid;
    double total = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        totalAmountText = view.findViewById(R.id.total_amount_text);
        placeOrderButton = view.findViewById(R.id.button);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList);
        recyclerView.setAdapter(cartAdapter);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(uid);
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        fetchCartItems();

        placeOrderButton.setOnClickListener(v -> enviarOrdenAFirebase());

        return view;
    }

    private void fetchCartItems() {
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartList.clear();
                total = 0;

                for (DataSnapshot itemSnap : snapshot.getChildren()) {
                    CartModel model = itemSnap.getValue(CartModel.class);
                    cartList.add(model);

                    try {
                        total += Double.parseDouble(model.getPrice());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                cartAdapter.notifyDataSetChanged();
                totalAmountText.setText("$" + total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarOrdenAFirebase() {
        if (cartList.isEmpty()) {
            Toast.makeText(getContext(), "Tu carrito está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("name").getValue(String.class);
                String apellido = snapshot.child("paternalSurname").getValue(String.class);
                String usuario = (nombre != null && apellido != null) ? nombre + " " + apellido : "Usuario desconocido";

                DatabaseReference nuevaOrdenRef = ordersRef.push(); // 🟢 agrupamos productos en una orden

                long timestampActual = System.currentTimeMillis();

                for (CartModel item : cartList) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", item.getName());
                    data.put("price", item.getPrice());
                    data.put("rating", item.getRating());
                    data.put("timing", item.getTiming());
                    data.put("description", item.getDescription());
                    data.put("image", item.getImage());
                    data.put("usuario", usuario);
                    data.put("timestamp", System.currentTimeMillis()); // ✅ Solo una vez
                    nuevaOrdenRef.push().setValue(data);
                }


                cartRef.removeValue().addOnSuccessListener(unused -> {
                    cartList.clear();
                    cartAdapter.notifyDataSetChanged();
                    totalAmountText.setText("$0.00");

                    Intent intent = new Intent(getActivity(), OrderSummaryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    requireActivity().finish();
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al leer usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
