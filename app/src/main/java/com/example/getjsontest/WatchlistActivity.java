package com.example.getjsontest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class WatchlistActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    RecyclerView recyclerview;
    MyAdapter myadapter;
    LinearLayoutManager layoutmanager;
    String toRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watchlist_view);

        Intent receiveData = getIntent();
        String receivedTitle = receiveData.getStringExtra("title");
        toRemove = receiveData.getStringExtra("toBeRemoved");

        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, 0);
        Set<String> receivedset;
        receivedset = sharedPref.getStringSet("titles", null);
        ArrayList<String> titles = new ArrayList<String>();

        if(receivedset == null || receivedset.isEmpty() && receivedTitle != null){
            titles.add(receivedTitle);
            saveArrayList(titles);
        }
        else {
            for (Iterator<String> iterator = receivedset.iterator(); iterator.hasNext(); ) {
                String str = iterator.next();
                if (str == null) {
                    iterator.remove();
                } else {
                    titles.add(str);
                }
            }

            if (!titles.contains(receivedTitle)) {
                titles.add(receivedTitle);
            }
            else if (titles.contains(receivedTitle)) {
                Toast.makeText(this, "Movie already exists in watchlist",
                        Toast.LENGTH_LONG).show();
            }
            saveArrayList(titles);
        }

        recyclerview = (RecyclerView) findViewById(R.id.myrv);
        recyclerview.setHasFixedSize(true);
        myadapter = new MyAdapter(titles);
        layoutmanager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setAdapter(myadapter);
    }

    public void saveArrayList(ArrayList list) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> titleset = new HashSet<>();
        //remove null values saved in list
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            if (str == null) {
                iterator.remove();
            }
            else if (Objects.equals(str, toRemove)) {
                iterator.remove();
                Toast.makeText(this, "Movie removed", Toast.LENGTH_LONG).show();
            }
            else {
                titleset.add(str);
            }
        }
        editor.putStringSet("titles", titleset);
        editor.apply();
    }
}



































//      String[] titles = {"Shrek", "Shrek 2", "Shrek the third"};
//      String[] years = {"2001", "2005", "2007"};
//      String[] plots = {"After his swamp is filled with magical creatures, an ogre agrees to rescue a princess for a villainous lord in order to get his land back.", "Princess Fiona's parents invite her and Shrek to dinner to celebrate her marriage. If only they knew the newlyweds were both ogres.", "When his new father-in-law, King Harold falls ill, Shrek is looked at as the heir to the land of Far, Far Away. Not one to give up his beloved swamp, Shrek recruits his friends Donkey and Puss in Boots to install the rebellious Artie as the new king. Princess Fiona, however, rallies a band of royal girlfriends to fend off a coup d'etat by the jilted Prince Charming."};