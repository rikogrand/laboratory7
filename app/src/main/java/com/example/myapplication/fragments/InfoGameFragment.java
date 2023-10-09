package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Game;
import com.example.myapplication.databinding.FragmentInfoGameBinding;

public class InfoGameFragment extends Fragment {
    private static final String GAME_ARG = "param1";
    private Game game;
    private String mParam2;
    private FragmentInfoGameBinding binding;

    public InfoGameFragment() {
        // Required empty public constructor
    }

    public static InfoGameFragment newInstance(String param1, String param2) {
        InfoGameFragment fragment = new InfoGameFragment();
        Bundle args = new Bundle();
        args.putString(GAME_ARG, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(Game item) {
        InfoGameFragment fragment = new InfoGameFragment();
        Bundle args = new Bundle();
        args.putSerializable(GAME_ARG, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            game = (Game)(getArguments().getSerializable(GAME_ARG));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nameInfo.setText(game.getName());
        binding.ageInfo.setText(String.format(String.valueOf(game.getYear())));
        binding.imageInfo.setImageResource(game.getPhoto());
    }
}