package com.example.lagare.ui.dailymeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.R;
import com.example.lagare.adapters.DailyMealAdapter;
import com.example.lagare.databinding.DailyMealFragmentBinding;
import com.example.lagare.models.DailyMealModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {

    RecyclerView recyclerView;
    List<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daily_meal_fragment, container, false);

        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new DailyMealModel(R.drawable.breakfast, "Desayuno", "30% DE DESCUENTO", "Descripcion Descripcion","Desayuno"));
        dailyMealModels.add(new DailyMealModel(R.drawable.lunch, "Almuerzo", "20% DE DESCUENTO", "Descripcion Descripcion","Almuerzo"));
        dailyMealModels.add(new DailyMealModel(R.drawable.dinner, "Cena", "50% DE DESCUENTO", "Descripcion Descripcion","Cena"));
        dailyMealModels.add(new DailyMealModel(R.drawable.sweets, "Dulces", "39% DE DESCUENTO", "Descripcion Descripcion","Dulces"));
        dailyMealModels.add(new DailyMealModel(R.drawable.coffe, "Cafe", "20% DE DESCUENTO", "Descripcion Descripcion","Cafe"));

        dailyMealAdapter = new DailyMealAdapter(getContext(),dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();

        return root;
    }

}
