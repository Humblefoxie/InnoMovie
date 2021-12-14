package com.example.pickafilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daprlabs.cardstack.SwipeDeck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private SwipeDeck cardStack;
    BufferedReader reader;
    private ArrayList<MovieContainer> movieContainerArrayList = new ArrayList<>();
    final MyDeckAdapter adapter = new MyDeckAdapter(movieContainerArrayList, this);
    private ActionBarDrawerToggle toggle;
    public static ArrayList<String> favorites = new ArrayList<>();
    private MovieContainer top;
    public static ArrayList<Boolean> preferenceList = new ArrayList<>();
    public static boolean first = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final InputStream inputStream = getResources().openRawResource(R.raw.data);
        reader = new BufferedReader(new InputStreamReader(inputStream));

        // initializing preference list
        if(!first) {
            initPrefList();
            first = true;
        }

        // Initializing array list and swipe deck.
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // Filling list of films, setting adapter to our card stack.
        loadSwipeCards();

        top = movieContainerArrayList.get(0);

        // Overriding methods needed in card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // Removes top element in movieContainerArraylist, and sets new top, if appropriate.
                movieContainerArrayList.remove(0);
                if (movieContainerArrayList.size() != 0) {
                    top = movieContainerArrayList.get(0);
                }
            }

            @Override
            public void cardSwipedRight(int position) {
                // Same as left swipe, though if card is swiped right, it is added to 'favorites'
                favorites.add(0, top.getName());
                movieContainerArrayList.remove(0);
                if (!movieContainerArrayList.isEmpty()) {
                    top = movieContainerArrayList.get(0);
                }
            }

            @Override
            public void cardsDepleted() {
                // This method is called when no more cards are left
                loadSwipeCards();
            }

            @Override
            public void cardActionDown() {
                // This method is called when card is swiped down.
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
            }
        });


        // Enabling Navigation menu
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        // Adds button to open menu
        toggle = new ActionBarDrawerToggle(this, drawerLayout , R.string.open, R.string.close);
        // Adds ability to click button
        drawerLayout.addDrawerListener(toggle);
        // Synchronizes state of drawer layout.
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



    }

    // This method allows the navigation view to be opened
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Adds new cards to the movieContainerArrayList, and updates cardstack.
    public void loadSwipeCards(){
            int i = 0;
            boolean fitted = true;
            while (i < 10) {
                movieContainerArrayList.add(constructMovie());
                i++;
            }
        // Updates cardstack with new movies.
        cardStack.setAdapter(adapter);
        top = movieContainerArrayList.get(0);

    }

    // This method starts the 'preferences' activity when the 'preferences' button in the nav view is pressed
    public void preferences(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Preferences.class);
        startActivity(intent);
    }

    // This method starts the 'favorites' activity when the 'favorites' button in the nav view is pressed
    public void favorites(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Favorites.class);
        startActivity(intent);
    }

    // This method reads a line from the comma-separated data.csv, formatting it into a movieContainer, and returns it.
    public MovieContainer constructMovie(){

        // Placeholders in case data is ugly and inconsistent
        String name = "PLACEHOLDER";
        String year = "PLACEHOLDER";
        String dur  = "PLACEHOLDER";
        String genre = "PLACEHOLDER";
        String desc = "PLACEHOLDER";

        try {
            String line = reader.readLine();

            if(line != null) {
                String[] splitted = line.split(",");
                name = splitted[0];

                year = splitted[1];

                dur = splitted[2];


                genre = splitted[3];
                genre = genre.replace("\"", "");

                // Since we split at every comma, and the description of the movie is after the last
                // separating comma, all elements in 'splitted', after the last comma is the description
                // which is concatenated into a string.
                if(splitted.length >= 5) {
                    desc = "";
                    for (int j = 4 ; j < splitted.length ; j++){
                        desc = desc + splitted[j];
                    }
                    desc = desc.replace("\"", "");
                }
            }
        } catch (IOException e) {e.printStackTrace();}

        return new MovieContainer(name, year,4, desc, genre, dur, 0 );
    }

    // Right now the result of preferences is kept as booleans in a list
    // later to be implemented as a hashmap (String nameOfPref:boolean chosen)
    private void initPrefList(){
        preferenceList.add(false);
        preferenceList.add(false);
        preferenceList.add(false);
        preferenceList.add(false);
        preferenceList.add(false);
        preferenceList.add(false);
    }

}
