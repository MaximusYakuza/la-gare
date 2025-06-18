package com.example.lagare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lagare.R;
import com.example.lagare.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartModel> list;

    public CartAdapter(List<CartModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel item = list.get(position);

        // Mostrar imagen con Glide
        Glide.with(holder.itemView.getContext())
                .load(item.getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.name.setText(item.getName());
        holder.rating.setText(item.getRating());
        holder.price.setText("$" + item.getPrice());

        // 👉 Eliminar producto del carrito
        holder.deleteBtn.setOnClickListener(v -> {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference()
                    .child("Cart").child(uid);

            cartRef.orderByChild("name").equalTo(item.getName())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot itemSnap : snapshot.getChildren()) {
                                itemSnap.getRef().removeValue();
                                list.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), list.size());
                                Toast.makeText(holder.itemView.getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                                break; // solo elimina el primero que coincida
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(holder.itemView.getContext(), "Error al eliminar: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, deleteBtn;
        TextView name, rating, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            rating = itemView.findViewById(R.id.detailed_rating);
            price = itemView.findViewById(R.id.textView7);
            deleteBtn = itemView.findViewById(R.id.delete_btn); // 🔥 importante
        }
    }
}
