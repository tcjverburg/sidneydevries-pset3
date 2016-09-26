package com.example.getjsontest;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<String,String,String> {

    URL url;
    HttpURLConnection urlConnection;
    InputStream inputStream;
    String finalString;

    @Override
    protected String doInBackground(String... params) {
        try {
            url = new URL(params[0]);
            //Log.d("TAG", "URL_loaded");
            urlConnection = (HttpURLConnection) url.openConnection();
            //Log.d("TAG", "openConnection");
            inputStream = urlConnection.getInputStream();
            //Log.d("TAG", "Inputstreammade");
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            //Log.d("TAG", "bufferedreaderstarted");
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            finalString = total.toString();
            //Log.d("TAG", "finalstringmade");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return finalString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Log.d("TAG", "on post execute");
    }
}
