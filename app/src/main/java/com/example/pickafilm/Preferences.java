package com.example.pickafilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {


    Switch newMovies;
    Switch goodRating;
    Switch actionMovies;
    Switch scifiMovies;
    Switch horrorMovies;
    Switch adventureMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        // Initializing switches in 'preferences' view
        newMovies = (Switch) findViewById(R.id.newMovies);
        goodRating = (Switch) findViewById(R.id.goodRating);
        actionMovies = (Switch) findViewById(R.id.actionMovies);
        scifiMovies = (Switch) findViewById(R.id.scifiMovies);
        horrorMovies = (Switch) findViewById(R.id.horrorMovies);
        adventureMovies = (Switch) findViewById(R.id.adventureMovies);

        // Setting their respective checked/unchecked status
        if(MainActivity.preferenceList.size()!= 0) {
            newMovies.setChecked(MainActivity.preferenceList.get(0));
            goodRating.setChecked(MainActivity.preferenceList.get(1));
            actionMovies.setChecked(MainActivity.preferenceList.get(2));
            scifiMovies.setChecked(MainActivity.preferenceList.get(3));
            horrorMovies.setChecked(MainActivity.preferenceList.get(4));
            adventureMovies.setChecked(MainActivity.preferenceList.get(5));
        }
    }

    // When new preferences are selected, the 'preference' list is rebuild with
    // regards to what switched are checked
    public void submitPreferences(View v){
        MainActivity.preferenceList.clear();
        MainActivity.preferenceList.add(newMovies.isChecked());
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