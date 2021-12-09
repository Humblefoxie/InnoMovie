package com.example.pickafilm;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SwipeDeck cardStack;
    private ArrayList<MovieContainer> movieContainerArrayList = new ArrayList<>();
    final DeckAdapter adapter = new DeckAdapter(movieContainerArrayList, this);
    private ActionBarDrawerToggle toggle;
    private ArrayList<String> favorites = new ArrayList<>();
    private MovieContainer top;
    public static ArrayList<Boolean> preferenceList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our array list and swipe deck.
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // filling list of films, setting adapter to our card stack.
        loadSwipeCards();

        top = movieContainerArrayList.get(0);

        // setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                movieContainerArrayList.remove(0);
                if (movieContainerArrayList.size() != 0) {
                    top = movieContainerArrayList.get(0);
                }
                //Toast.makeText(MainActivity.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                favorites.add(0, top.getName());
                movieContainerArrayList.remove(0);
                if (movieContainerArrayList.size() != 0) {
                    top = movieContainerArrayList.get(0);
                }
                Toast.makeText(MainActivity.this, favorites.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                loadSwipeCards();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout , R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Updates card to swipe
    public void loadSwipeCards(){
        movieContainerArrayList.add(new MovieContainer("Kevin in Space", "1990", 3, "Kevin Spacey acting spacey in space", "Superhero, Action, Adventure", "00:05:00",  R.drawable.ic_launcher_foreground));
        movieContainerArrayList.add(new MovieContainer("Kevin in Space 2", "1991", 4, "Kevin Spacey acting spacey in space 2", "Superhero, Action, Adventure", "00:05:01",  R.drawable.ic_launcher_foreground));
        movieContainerArrayList.add(new MovieContainer("Kevin in Space 3", "1992", 5, "Kevin Spacey acting spacey in space 3", "Superhero, Action, Adventure", "00:05:30",  R.drawable.ic_launcher_foreground));
        cardStack.setAdapter(adapter);
        top = movieContainerArrayList.get(0);
    }

    public void preferences(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Preferences.class);
        startActivity(intent);
    }

    public void favorites(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Favorites.class);
        startActivity(intent);
    }
}