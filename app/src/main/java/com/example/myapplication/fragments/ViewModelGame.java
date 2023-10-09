package com.example.myapplication.fragments;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Game;
import com.example.myapplication.GameAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ViewModelGame extends ViewModel {
    public List<Game> gameList = new ArrayList<>();

    public void setGame() {
        if (!gameList.isEmpty()) return;
        gameList.add(new Game("Dota 2", 2000, R.drawable.dota));
        gameList.add(new Game("CSGO", 2015, R.drawable.csgo));
        gameList.add(new Game("CS 2", 2025, R.drawable.cs));
        gameList.add(new Game("Valorant", 2020, R.drawable.valorant));
    }

    public ViewModelGame() {
        setGame();
    }
}