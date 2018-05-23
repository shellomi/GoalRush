package com.example.binson.goalrush.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String STATIC_WEATHER_URL =
            "https://andfun-weather.udacity.com/staticweather";
    private static final String QUERY_PARAM = "q";
    private static final String FORMAT_PARAM = "mode";
    private static final String UNITS_PARAM = "units";
    private static final String DAYS_PARAM = "cnt";

    public static URL getUrl() {
        Uri weatherUri = Uri.parse(STATIC_WEATHER_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "Mumbai")
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(UNITS_PARAM, "metric")
                .appendQueryParameter(DAYS_PARAM, Integer.toString(14))
                .build();
        try {
            URL url = new URL(weatherUri.toString());
            Log.v(TAG, "Url: " + url);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }
}
