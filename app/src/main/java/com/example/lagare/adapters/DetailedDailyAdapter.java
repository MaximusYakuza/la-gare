package com.example.lagare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.R;
import com.example.lagare.models.DetailedDailyModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class DetailedDailyAdapter extends RecyclerView.Adapter<DetailedDailyAdapter.ViewHolder> {

    List<DetailedDailyModel> list;
    Context context;

    public DetailedDailyAdapter(List<DetailedDailyModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daily_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailedDailyModel model = list.get(position);

        holder.imageView.setImageResource(model.getImage());
        holder.price.setText(model.getPrice());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.timing.setText(model.getTiming());
        holder.rating.setText(model.getRating());

        holder.addToCart.setOnClickListener(v -> {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(uid).push();

            HashMap<String, Object> cartItem = new HashMap<>();
            cartItem.put("name", model.getName());
            cartItem.put("price", model.getPrice());
            cartItem.put("rating", model.getRating());
            cartItem.put("timing", model.getTiming());
            cartItem.put("description", model.getDescription());
            cartItem.put("image", String.valueOf(model.getImage()));

            cartRef.setValue(cartItem).addOnSuccessListener(unused ->
                    Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
            ).addOnFailureListener(e ->
                    Toast.makeText(context, "Error al agregar: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, description, rating, timing;
        Button addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.detailed_price);
            description = itemView.findViewById(R.id.detailed_des);
            rating = itemView.findViewById(R.id.detailed_rating);
            timing = itemView.findViewById(R.id.detailed_timing);
            addToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
