package com.example.pickafilm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<MovieContainer> movieData;
    private Context context;

    // on below line we have created constructor for our variables.
    public DeckAdapter(ArrayList<MovieContainer> movieData, Context context) {
        this.movieData = movieData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return movieData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.idMovieName)).setText(movieData.get(position).getName());
        ((TextView) v.findViewById(R.id.idMovieDesc)).setText("Description: \n" + movieData.get(position).getDesc());
        ((TextView) v.findViewById(R.id.idMovieGenre)).setText(movieData.get(position).getGenre());
        ((TextView) v.findViewById(R.id.idMovieRuntime)).setText("Duration: " + movieData.get(position).getRuntime());
        ((TextView) v.findViewById(R.id.idMovieRating)).setText("Rating: " + Integer.toString(movieData.get(position).getRating()));
        ((TextView) v.findViewById(R.id.idMovieYear)).setText("Year: " +movieData.get(position).getYear());
        ((ImageView) v.findViewById(R.id.idIMG)).setImageResource(movieData.get(position).getImgId());
        return v;
    }
}