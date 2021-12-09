package com.example.pickafilm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {


    Switch oldMovies;
    Switch goodRating;
    Switch actionMovies;
    Switch scifiMovies;
    Switch horrorMovies;
    Switch adventureMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        oldMovies = (Switch) findViewById(R.id.oldMovies);
        goodRating = (Switch) findViewById(R.id.goodRating);
        actionMovies = (Switch) findViewById(R.id.actionMovies);
        scifiMovies = (Switch) findViewById(R.id.scifiMovies);
        horrorMovies = (Switch) findViewById(R.id.horrorMovies);
        adventureMovies = (Switch) findViewById(R.id.adventureMovies);


        if(MainActivity.preferenceList.size()!= 0) {
            oldMovies.setChecked(MainActivity.preferenceList.get(0));
            goodRating.setChecked(MainActivity.preferenceList.get(1));
            actionMovies.setChecked(MainActivity.preferenceList.get(2));
            scifiMovies.setChecked(MainActivity.preferenceList.get(3));
            horrorMovies.setChecked(MainActivity.preferenceList.get(4));
            adventureMovies.setChecked(MainActivity.preferenceList.get(5));
        }
    }

    public void submitPreferences(View v){
        MainActivity.preferenceList.clear();
        MainActivity.preferenceList.add(oldMovies.isChecked());
        MainActivity.preferenceList.add(goodRating.isChecked());
        MainActivity.preferenceList.add(actionMovies.isChecked());
        MainActivity.preferenceList.add(scifiMovies.isChecked());
        MainActivity.preferenceList.add(horrorMovies.isChecked());
        MainActivity.preferenceList.add(adventureMovies.isChecked());
        Toast.makeText(Preferences.this, "Preferences updated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Preferences.this, MainActivity.class);
        startActivity(intent);
    }
}