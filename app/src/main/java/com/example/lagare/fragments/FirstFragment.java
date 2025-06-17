package com.example.lagare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lagare.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.adapters.FeaturedAdapter;
import com.example.lagare.adapters.FeaturedVerAdapter;
import com.example.lagare.models.FeaturedModel;
import com.example.lagare.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    ////////////Featured Hor RecyclerView
    List<FeaturedModel> featuredModelList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;

    ////////////Featured Ver RecyclerView
    List<FeaturedVerModel> featuredVerModelList;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;

    public FirstFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);


        ////////////Featured Hor RecyclerView
        recyclerView = view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        featuredModelList = new ArrayList<>();

        featuredModelList.add(new FeaturedModel(R.drawable.fav1, "Presentador 1", "Descripcion 1"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav2, "Presentador 2", "Descripcion 2"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav3, "Presentador 3", "Descripcion 3"));

        featuredAdapter = new FeaturedAdapter(featuredModelList);
        recyclerView.setAdapter(featuredAdapter);

        ////////////Featured Ver RecyclerView

        recyclerView2 = view.findViewById(R.id.featured_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        featuredVerModelList = new ArrayList<>();

        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1, "Presentador 1", "Descripcion 1", "4.8", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2, "Presentador 2", "Descripcion 2", "4.8", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3, "Presentador 3", "Descripcion 3", "4.8", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1, "Presentador 1", "Descripcion 1", "4.8", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2, "Presentador 2", "Descripcion 2", "4.8", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3, "Presentador 3", "Descripcion 3", "4.8", "10:00 - 8:00"));

        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModelList);
        recyclerView2.setAdapter(featuredVerAdapter);

        return view;
    }
}
