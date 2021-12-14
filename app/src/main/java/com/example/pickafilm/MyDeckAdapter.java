package com.example.pickafilm;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyDeckAdapter extends BaseAdapter {

    private ArrayList<MovieContainer> movieData;
    private Context context;
    private static int picpick = 0;

    // constructor
    public MyDeckAdapter(ArrayList<MovieContainer> movieData, Context context) {
        this.movieData = movieData;
        this.context = context;
    }

    @Override
    public int getCount() {

        return movieData.size();
    }

    @Override
    public Object getItem(int position) {

        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        }

        // Initializing variables and setting data
        ((TextView) v.findViewById(R.id.idMovieName)).setText(movieData.get(position).getName());
        ((TextView) v.findViewById(R.id.idMovieDesc)).setText("Description: \n" + movieData.get(position).getDesc());
        ((TextView) v.findViewById(R.id.idMovieGenre)).setText(movieData.get(position).getGenre());
        ((TextView) v.findViewById(R.id.idMovieRuntime)).setText("Duration: " + movieData.get(position).getRuntime());
        ((TextView) v.findViewById(R.id.idMovieRating)).setText("Rating: " + Integer.toString(movieData.get(position).getRating()));
        ((TextView) v.findViewById(R.id.idMovieYear)).setText("Year: " +movieData.get(position).getYear());
        ImageView iv = v.findViewById(R.id.idIMG);

        // Start asynchronous task to get a drawable from a url.
        try {
            AsyncTask at = new AsyncGetDrawableFromURL().execute(pickUrl());
            iv.setImageDrawable((Drawable) at.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return v;
    }

    // Cycles between 5 pictures of the internet
    // Later to implement: url to actual movieposter (tmdb)
    private String pickUrl(){
        String url;
        if(picpick == 0) {
            url = "https://assets1.ignimgs.com/2018/08/31/bestdramas-blogroll-1535740374035_1280w.jpg";
            picpick++;
        }
        else if(picpick == 1){
            url = "https://images.hdqwalls.com/download/the-amazing-spider-man-2-3840x2400.jpg";
            picpick++;
        }
        else if(picpick == 2){
            url = "https://1.bp.blogspot.com/-0_SCvO-FI88/XLiLma5pxYI/AAAAAAAANgM/F77DvtamMYorMgmHmmbiqjCgv77xnC2eQCLcBGAs/s2560/aladdin-2019-movie-poster-qi-1080x1920.jpg";
            picpick++;
        }
        else if(picpick == 3){
            url = "https://www.nsbpictures.com/wp-content/uploads/2018/08/movie-poster-backgrounds-18.jpg";
            picpick++;
        }
        else if(picpick == 4){
            url = "https://cdn.shopify.com/s/files/1/2339/7977/products/IMG_8145_1200x1200.JPG?v=1559928611";
            picpick++;
        }
        else{
            url = "http://artatm.com/wp-content/uploads/2013/09/25_thumb.jpg";
            picpick = 0;
        }

        return url;
    }


}


// This class only contains one method, and extends AsyncTask.
// AsyncTask enables Asynchronous execution, as networking is not allowed on
// android.
class AsyncGetDrawableFromURL extends AsyncTask<String, Void, Drawable> {

    protected Drawable doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            InputStream is = url.openStream();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;

        } catch (IOException e) {

            return null;
        }
    }
}
