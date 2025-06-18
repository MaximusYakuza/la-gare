package com.example.lagare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lagare.MainActivity;
import com.example.lagare.R;
import com.example.lagare.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.Locale;

public class OrderSummaryActivity extends AppCompatActivity {

    LinearLayout orderItemsLayout;
    TextView successMessage;
    Button backToHomeButton;
    TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Validación de sesión
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_order_summary);

        orderItemsLayout = findViewById(R.id.order_items_container);
        successMessage = findViewById(R.id.textViewOrderSuccess);
        backToHomeButton = findViewById(R.id.buttonBackToHome);

        // Texto dinámico para el total
        totalText = new TextView(this);
        totalText.setTextSize(20);
        totalText.setTextColor(getResources().getColor(R.color.black));
        totalText.setPadding(10, 20, 10, 20);

        loadLastOrderFromFirebase(user.getUid());

        backToHomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Ahora sí se puede cerrar
        });
    }

    private void loadLastOrderFromFirebase(String uid) {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);

        ordersRef.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                orderItemsLayout.removeAllViews();
                double total = 0;
                boolean hasItems = false;

                if (!snapshot.exists()) {
                    successMessage.setText("No se encontraron productos.");
                    return;
                }

                for (DataSnapshot orderSnap : snapshot.getChildren()) {
                    for (DataSnapshot itemSnap : orderSnap.getChildren()) {
                        CartModel model = itemSnap.getValue(CartModel.class);
                        if (model == null) continue;

                        hasItems = true;

                        TextView itemView = new TextView(OrderSummaryActivity.this);
                        itemView.setText("• " + model.getName() + "   $" + model.getPrice());
                        itemView.setTextSize(18);
                        itemView.setPadding(8, 8, 8, 8);
                        orderItemsLayout.addView(itemView);

                        try {
                            total += Double.parseDouble(model.getPrice());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (hasItems) {
                    totalText.setText(String.format(Locale.getDefault(), "\nTotal: $%.2f", total));
                    orderItemsLayout.addView(totalText);
                } else {
                    successMessage.setText("No se encontraron productos.");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(OrderSummaryActivity.this, "Error al cargar: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                successMessage.setText("Error al cargar el pedido.");
            }
        });
    }
}
