package com.example.getjsontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String jsonLongPlot;
    String url;
    Button searchButton;
    EditText searchText;
    String input;

    String movieTitle;
    String movieYear;
    String moviePlot;
    String movieDirector;
    String movieActors;
    String moviePoster;
    boolean response;

    TextView movieTitleTv;
    TextView movieYearTv;
    TextView moviePlotTv;
    TextView movieDirectorTv;
    TextView movieActorsTv;
    ImageView moviePosterIv;
    Button addToFave;

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        setContentView(R.layout.activity_main);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(R.id.searchText);

        if (outState != null) {
            jsonLongPlot = outState.getString("longplot");
            addToFave = (Button) findViewById(R.id.addToFaves);
            addToFave.setVisibility(View.INVISIBLE);
            try {
                extractJson(jsonLongPlot);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            findViews();
            setContent();
        }
        else {
            addToFave = (Button) findViewById(R.id.addToFaves);
            addToFave.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("longplot", jsonLongPlot);
    }

    public void onSearchButtonClick(View view) throws JSONException {
        input = searchText.getText().toString();
        validateMovie(input);
        if (response) {
            getJsonFromUrl(input);
            extractJson(jsonLongPlot);
            findViews();
            setContent();
        }
        else {
            Toast.makeText(this, "Movie not found", Toast.LENGTH_LONG).show();
        }
    }

    public void addMovieToFave(View view) throws JSONException {
        //adds searched movie to watchlist
        Intent goToWatchlist = new Intent(this, WatchlistActivity.class);
        goToWatchlist.putExtra("title", movieTitle);
        startActivity(goToWatchlist);
    }

    public void getJsonFromUrl(String input) {
        //gets data with long plot for display
        MyAsyncTask asyncTask = new MyAsyncTask();
        input = input.replace(" ", "+"); //maybe meer validation
        url = "http://www.omdbapi.com/?t="+input+"&y=&plot=full&r=json";
        try {
            jsonLongPlot = asyncTask.execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void findViews() {
        //defines all views
        movieTitleTv = (TextView) findViewById(R.id.movieTitle);
        movieYearTv = (TextView) findViewById(R.id.movieYear);
        moviePlotTv = (TextView) findViewById(R.id.moviePlot);
        movieDirectorTv = (TextView) findViewById(R.id.movieDirector);
        movieActorsTv = (TextView) findViewById(R.id.movieActors);
        moviePosterIv = (ImageView) findViewById(R.id.moviePoster);
    }

    public void setContent() {
        //sets contents of views to data recieved from json
        movieTitleTv.setText(movieTitle);
        movieYearTv.setText(movieYear);
        moviePlotTv.setText(moviePlot);
        movieDirectorTv.setText(movieDirector);
        movieActorsTv.setText(movieActors);
        Picasso.with(this).load(moviePoster).into(moviePosterIv);
        addToFave.setVisibility(View.VISIBLE);
    }

    public void extractJson(String jsonToExtract) throws JSONException {
        //extracts data from json to put in views
        JSONObject jObj = new JSONObject(jsonToExtract);
        movieTitle = jObj.getString("Title");
        movieYear = jObj.getString("Year");
        moviePlot = jObj.getString("Plot");
        movieDirector = jObj.getString("Director");
        movieActors = jObj.getString("Actors");
        moviePoster = jObj.getString("Poster");
    }

    public void validateMovie(String input) throws JSONException {
        //checks if movie exists in ombd
        getJsonFromUrl(input);
        JSONObject jObj = new JSONObject(jsonLongPlot);
        response = jObj.getBoolean("Response");
    }

    public void goToWatchlist(View view) {
        //goes to watchlist activity
        Intent goToWatchlist = new Intent (this, WatchlistActivity.class);
        startActivity(goToWatchlist);
    }
}

