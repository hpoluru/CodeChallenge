package com.example.asus.codechallenge.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.util.Log;

public class JSONParser {

    static InputStream is = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    public String getJSONFromUrl(String fetchedUrl) {

        try {
            URL url = new URL(fetchedUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                Log.e("line",line);
            }
            is.close();
            json = sb.toString();

            Log.d("JSON_PARSER",json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return JSON String
        Log.e("json repsonse",json);
        return json;
    }
}