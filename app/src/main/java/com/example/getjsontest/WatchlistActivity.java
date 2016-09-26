package com.example.getjsontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class WatchlistActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    MyAdapter myadapter;
    LinearLayoutManager layoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watchlist_view);

        ArrayList titles = new ArrayList();
        ArrayList years = new ArrayList();
        ArrayList plots = new ArrayList();

        Intent recieveData = getIntent();
        String recievedTitle = recieveData.getStringExtra("title");
        String recievedYear = recieveData.getStringExtra("year");
        String recievedPlot = recieveData.getStringExtra("plot");

        titles.add(recievedTitle);
        years.add(recievedYear);
        plots.add(recievedPlot);

        recyclerview = (RecyclerView) findViewById(R.id.myrv);
        recyclerview.setHasFixedSize(true);
        myadapter = new MyAdapter(titles, years, plots);
        layoutmanager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setAdapter(myadapter);
    }
}



































//      String[] titles = {"Shrek", "Shrek 2", "Shrek the third"};
//      String[] years = {"2001", "2005", "2007"};
//      String[] plots = {"After his swamp is filled with magical creatures, an ogre agrees to rescue a princess for a villainous lord in order to get his land back.", "Princess Fiona's parents invite her and Shrek to dinner to celebrate her marriage. If only they knew the newlyweds were both ogres.", "When his new father-in-law, King Harold falls ill, Shrek is looked at as the heir to the land of Far, Far Away. Not one to give up his beloved swamp, Shrek recruits his friends Donkey and Puss in Boots to install the rebellious Artie as the new king. Princess Fiona, however, rallies a band of royal girlfriends to fend off a coup d'etat by the jilted Prince Charming."};