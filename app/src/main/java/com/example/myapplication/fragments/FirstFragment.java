package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Game;
import com.example.myapplication.R;
import com.example.myapplication.GameAdapter;
import com.example.myapplication.databinding.FragmentFirstListBinding;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    private ViewModelGame viewmodel;
    public GameAdapter gameAdapter;

    private FragmentFirstListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(ViewModelGame.class);
        gameListInit();
        ShowNewGameFragment();
        ShowSettingsFragment();
    }

    private void gameListInit() {
        if (gameAdapter == null) {
            gameAdapter = new GameAdapter(
                    getContext(),
                    R.layout.list_item,
                    viewmodel.gameList);
        }
        binding.list.setOnItemClickListener((parent, view, position, id) -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment, InfoGameFragment.newInstance(gameAdapter.getItem(position)), "fragment_town_details")
                    .addToBackStack("fragment_town_details")
                    .commit();
        });
        binding.list.setAdapter(gameAdapter);
        binding.list.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getContext(),
                    String.valueOf(i),
                    Toast.LENGTH_LONG).show();
            gameAdapter.remove(gameAdapter.getItem(i));
            return true;
        });

    }

    public void ShowNewGameFragment() {
        binding.addGame.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, new NewGameFragment(gameAdapter), "new_game_fragment")
                    .addToBackStack("new_game_fragment")
                    .commit();
        });

    }

    public void ShowSettingsFragment() {
        binding.settingsBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment, new SettingsFragment(gameAdapter), "fragment_settings")
                    .addToBackStack("fragment_settings")
                    .commit();
        });
    }
}
