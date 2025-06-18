package com.example.lagare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagare.R;
import com.example.lagare.adapters.HomeHorAdapter;
import com.example.lagare.adapters.HomeVerAdapter;
import com.example.lagare.adapters.UpdateVerticalRec;
import com.example.lagare.models.HomeHorModel;
import com.example.lagare.models.HomeVerModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    TextView textViewHolaMauro;  // Referencia al TextView de bienvenida

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Referencia al TextView donde se mostrará el nombre del usuario
        textViewHolaMauro = root.findViewById(R.id.textViewHolaMauro);

        // 🔥 Obtener UID del usuario actual
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // 🔥 Obtener referencia al nodo del usuario en la base de datos
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(uid);

        // 🔥 Escuchar una sola vez para obtener el nombre y apellido
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("name").getValue(String.class);
                String apellido = snapshot.child("paternalSurname").getValue(String.class);
                String apellidoMaterno = snapshot.child("maternalSurname").getValue(String.class);

                if (nombre != null && apellido != null) {
                    textViewHolaMauro.setText("Bienvenido " + nombre + " " + apellido + " " + apellidoMaterno);
                } else {
                    textViewHolaMauro.setText("Bienvenido Usuario");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                textViewHolaMauro.setText("Bienvenido Usuario");
            }
        });

        // Horizontal RecyclerView
        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeHorModelList = new ArrayList<>();
        homeHorModelList.add(new HomeHorModel(R.drawable.pizza, "Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger, "Hamburguesa"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes, "Papas Fritas"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream, "Helado"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sandwich, "Sandwich"));

        homeHorAdapter = new HomeHorAdapter(this, getActivity(), homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        // Vertical RecyclerView
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        homeVerModelList = new ArrayList<>();
        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return root;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}
