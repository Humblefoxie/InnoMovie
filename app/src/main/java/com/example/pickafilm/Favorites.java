package com.example.pickafilm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ListView favorites = (ListView) findViewById(R.id.favListView);
        MyListAdapter listAdapter = new MyListAdapter(this, MainActivity.favorites);
        favorites.setAdapter(listAdapter);
    }
}