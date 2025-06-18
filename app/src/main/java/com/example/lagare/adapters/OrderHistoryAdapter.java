package com.example.lagare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lagare.R;
import com.example.lagare.models.CartModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    List<CartModel> historyList;

    public OrderHistoryAdapter(List<CartModel> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel item = historyList.get(position);

        holder.name.setText(item.getName());
        holder.price.setText("$" + item.getPrice());
        holder.rating.setText(item.getRating());

        // 🔐 Seguridad: controlar si el timestamp existe
        long timestamp = item.getTimestamp();
        if (timestamp > 0) {
            String fecha = convertirTimestamp(timestamp);
            holder.timestampText.setText(fecha);
        } else {
            holder.timestampText.setText("Sin fecha registrada");
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, rating, timestampText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.textView7);
            rating = itemView.findViewById(R.id.detailed_rating);
            timestampText = itemView.findViewById(R.id.timestamp_text);
        }
    }

    private String convertirTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy - hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
