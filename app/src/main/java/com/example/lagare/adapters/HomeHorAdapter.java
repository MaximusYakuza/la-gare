package com.example.lagare.adapters;

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

import com.example.lagare.models.HomeHorModel;
import com.example.lagare.R;
import com.example.lagare.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder>{

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list != null && position < list.size()) {
            HomeHorModel item = list.get(position);
            if (item != null) {
                holder.imageView.setImageResource(item.getImage());
                holder.name.setText(item.getName());

                if (check) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza 1", "10:00 - 23:00", "4.9", "Min - $35"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza2, "Pizza 2", "10:00 - 23:00", "4.9", "Min - $35"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza3, "Pizza 3", "10:00 - 23:00", "4.9", "Min - $35"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza4, "Pizza 4", "10:00 - 23:00", "4.9", "Min - $35"));

                    updateVerticalRec.callBack(position, homeVerModels);
                    check = false;
                }

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        row_index = position;
                        notifyDataSetChanged();

                        if (position == 0) {


                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                            homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza 1", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.pizza2, "Pizza 2", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.pizza3, "Pizza 3", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.pizza4, "Pizza 4", "10:00 - 23:00", "4.9", "Min - $35"));

                            updateVerticalRec.callBack(position, homeVerModels);
                        } else if (position == 1) {
                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                            homeVerModels.add(new HomeVerModel(R.drawable.burger1, "Hamburguesa 1", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.burger2, "Hamburguesa 2", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.burger4, "Hamburguesa 3", "10:00 - 23:00", "4.9", "Min - $35"));

                            updateVerticalRec.callBack(position, homeVerModels);

                        } else if (position == 2) {
                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                            homeVerModels.add(new HomeVerModel(R.drawable.fries1, "Papas fritas 1", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.fries2, "Papas fritas 2", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.fries3, "Papas fritas 3", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.fries4, "Papas fritas 4", "10:00 - 23:00", "4.9", "Min - $35"));

                            updateVerticalRec.callBack(position, homeVerModels);

                        } else if (position == 3) {
                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                            homeVerModels.add(new HomeVerModel(R.drawable.icecream1, "Helado 1", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.icecream2, "Helado 2", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.icecream3, "Helado 3", "10:00 - 23:00", "4.9", "Min - $35"));

                            updateVerticalRec.callBack(position, homeVerModels);

                        } else if (position == 4) {
                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                            homeVerModels.add(new HomeVerModel(R.drawable.sandwich1, "Sandwich 1", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.sandwich2, "Sandwich 2", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.sandwich3, "Sandwich 3", "10:00 - 23:00", "4.9", "Min - $35"));
                            homeVerModels.add(new HomeVerModel(R.drawable.sandwich4, "Sandwich 4", "10:00 - 23:00", "4.9", "Min - $35"));

                            updateVerticalRec.callBack(position, homeVerModels);

                        }

                    }
                });

                if (select) {
                    if (position == 0) {
                        holder.cardView.setBackgroundResource(R.drawable.change_bg);
                        select = false;

                    }
                } else {
                    if (row_index == position) {
                        holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    } else {
                        holder.cardView.setBackgroundResource(R.drawable.default_bg);
                    }
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
