package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.fragments.FirstFragment;
import com.example.myapplication.fragments.SettingsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Game> games = new ArrayList<Game>();
    ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragMan = getSupportFragmentManager();
        if (fragMan.getBackStackEntryCount() < 1) {
            fragMan
                    .beginTransaction()
                    .add(R.id.main_fragment, new FirstFragment(), "first_fragment")
                    .commit();
        }
        SharedPreferences prefs = getSharedPreferences(SettingsFragment.PREFS,
                Context.MODE_PRIVATE);
        boolean darkTheme = prefs.getBoolean(SettingsFragment.DARK_QQQQ, false);
        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void setInitialData() {
        games.add(new Game("Dota 2", 2000, R.drawable.dota));
        games.add(new Game("CSGO", 2015, R.drawable.csgo));
        games.add(new Game("CS 2", 2025, R.drawable.cs));
        games.add(new Game("Valorant", 2020, R.drawable.valorant));
    }
}

