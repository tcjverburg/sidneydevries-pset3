package com.example.getjsontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class WatchlistItem extends AppCompatActivity {

    String jsonLongPlot;
    String url;
    String input;

    String movieTitle;
    String movieYear;
    String moviePlot;
    String movieDirector;
    String movieActors;
    String moviePoster;

    TextView movieTitleTv;
    TextView movieYearTv;
    TextView moviePlotTv;
    TextView movieDirectorTv;
    TextView movieActorsTv;
    ImageView moviePosterIv;

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        setContentView(R.layout.watchlist_item_view);

        Intent receiveData = getIntent();
        String receivedTitle = receiveData.getStringExtra("title");

        input = receivedTitle;
        getJsonFromUrl(input);
        try {
            extractJson(jsonLongPlot);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        findViews();
        setContent();
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
}
