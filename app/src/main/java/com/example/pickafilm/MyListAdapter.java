package com.example.pickafilm;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<String> favorites;

    // constructor
    public MyListAdapter(Context applicationContext, ArrayList<String> favorites) {
        this.context = context;
        this.favorites = favorites;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return favorites.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Initializing variables and setting data
        view = inflter.inflate(R.layout.activity_list_view, null);
        TextView movieName = (TextView) view.findViewById(R.id.textView);
        movieName.setText(favorites.get(i));
        return view;
    }
}