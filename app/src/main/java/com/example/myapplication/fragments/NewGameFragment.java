package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Game;
import com.example.myapplication.GameAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewGameBinding;

import java.util.Random;

public class NewGameFragment extends Fragment {
    private FragmentNewGameBinding binding;
    private GameAdapter adapter;

    public NewGameFragment(GameAdapter adapter) {

        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addGame();
        initClose();
    }

    private void initClose() {
        binding.btnClose.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .popBackStack();
        });
    }

    private void addGame() {
        binding.btnAdd.setOnClickListener((v) -> {
            String gameName = binding.addName.getText().toString();
            int gameYear = Integer.valueOf(binding.addYear.getText().toString());
            Random randphoto = new Random();
             int photoId;
            switch (randphoto.nextInt(5)) {
                case 1:
                    photoId = R.drawable.cs;
                    break;
                case 2:
                    photoId = R.drawable.dota;
                    break;
                case 3:
                    photoId = R.drawable.csgo;
                case 4:
                    photoId = R.drawable.valorant;
                break;
                default:
                photoId = R.drawable.dota;
            }
            if (gameYear == 0 || gameName.equals("")) return;
            Game Game2Add = new Game(gameName, gameYear, photoId);
            adapter.add(Game2Add);
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

}

