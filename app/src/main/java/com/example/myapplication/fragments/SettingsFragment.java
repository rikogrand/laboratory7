package com.example.myapplication.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Game;
import com.example.myapplication.GameAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSettingsBinding;

import java.io.Serializable;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    private static final String SETTINGS_ARG = "param1";
    private Game game;
    private GameAdapter adapter;
    private FragmentSettingsBinding binding;
    SharedPreferences settings;
    private static final String PREF_NAME = "Name";
    private String mParam2;

    public SettingsFragment(GameAdapter adapter) {
        this.adapter = adapter;
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(SETTINGS_ARG, param1);
        fragment.setArguments(args);
        return fragment;

    }

    public static SettingsFragment newInstance(Game item) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SETTINGS_ARG, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            game = (Game) (getArguments().getSerializable(SETTINGS_ARG));
        }
        settings = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SaveSettings();
        getSettings();
    }


    public void SaveSettings() {
        binding.saveButton.setOnClickListener((v) -> {
            String name = binding.nameBox.getText().toString();
            SharedPreferences prefs = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("NAME", name);
            editor.apply();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, new FirstFragment(), "fragment_first")
                    .addToBackStack("fragment_first")
                    .commit();
        });
    }

    public void setDarkTheme(boolean darkTheme) {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(DARK_QQQQ, darkTheme);
        editor.apply();
        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void getSettings() {

        SharedPreferences pref = requireContext().getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
        );
        String name = pref.getString("NAME", null);
        boolean darkTheme = pref.getBoolean(DARK_QQQQ, false);
        binding.nameBox.setText(name);
        binding.changeTheme.setChecked(darkTheme);
        binding.changeTheme.setOnCheckedChangeListener((compoundButton, b) -> {
            setDarkTheme(b);
        });
    }


    public static final String PREFS = "PREFS";
    public static final String DARK_QQQQ = "Dark_Theme";
}




