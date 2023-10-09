package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {

    private LayoutInflater inflater;
    private int layout;
    private List<Game> games;
    public GameAdapter(Context context, int resource, List<Game> games){
        super(context, resource, games);
        this.games = games;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View view=inflater.inflate(this.layout, parent, false);

        ImageView photoView = view.findViewById(R.id.photo);
        TextView nameView = view.findViewById(R.id.name);
        TextView yearView = view.findViewById(R.id.year);
        Game game = games.get(position);
        photoView.setImageResource(game.getPhoto());
        nameView.setText(game.getName());
        yearView.setText(String.format("%d", game.getYear()));

        return view;
    }
}
