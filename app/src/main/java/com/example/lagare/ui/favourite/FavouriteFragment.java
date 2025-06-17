package com.example.lagare.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lagare.R;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lagare.databinding.FragmentFavouriteBinding;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourite,container, false);
        return root;
    }

}
