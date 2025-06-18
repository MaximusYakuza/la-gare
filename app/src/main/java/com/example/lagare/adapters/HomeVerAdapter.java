package com.example.lagare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.R;
import com.example.lagare.models.HomeVerModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {
        HomeVerModel item = list.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.timing.setText(item.getTiming());
        holder.rating.setText(item.getRating());
        holder.price.setText(item.getPrice());

        holder.itemView.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetTheme);
            View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null);

            ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
            TextView bottomName = sheetView.findViewById(R.id.bottom_name);
            TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
            TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);

            bottomName.setText(item.getName());
            bottomPrice.setText(item.getPrice());
            bottomRating.setText(item.getRating());
            bottomImg.setImageResource(item.getImage());

            // 🔥 Agregar al carrito
            sheetView.findViewById(R.id.add_to_cart).setOnClickListener(view -> {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(uid);

                // ✅ Extraer solo el número del precio (por ejemplo, "35")
                String rawPrice = item.getPrice().replaceAll("[^0-9.]", "");

                HashMap<String, Object> product = new HashMap<>();
                product.put("name", item.getName());
                product.put("price", rawPrice); // Solo el número
                product.put("rating", item.getRating());
                product.put("timing", item.getTiming());
                product.put("description", "Descripción no disponible");
                product.put("image", String.valueOf(item.getImage())); // ID como String
                product.put("timestamp", System.currentTimeMillis()); // 🔥 para historial

                cartRef.push().setValue(product)
                        .addOnSuccessListener(unused -> Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());

                bottomSheetDialog.dismiss();
            });

            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, timing, rating, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }
}
