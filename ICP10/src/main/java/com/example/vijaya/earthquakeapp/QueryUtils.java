package com.example.vijaya.earthquakeapp;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;


public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String inputUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL input_url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder sb = new StringBuilder();

        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            input_url = new URL(inputUrl);


            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)
            URLConnection connection = input_url.openConnection();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            if (br != null) {
                br.close();
            }

            jsonResponse = sb.toString();


                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */

            //store response array
            JSONObject object = new JSONObject(jsonResponse);
            JSONArray jsonArray = object.getJSONArray("features");

            for (int i=0; i< jsonArray.length(); i++) {

                JSONObject jsonItem = jsonArray.getJSONObject(i);
                JSONObject properties = jsonItem.getJSONObject("properties");

                System.out.println(jsonItem);

                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");

                Earthquake earthquake = new Earthquake(magnitude, place, time, url);
                earthquakes.add(earthquake);

            }

            // Return the list of earthquakes

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
