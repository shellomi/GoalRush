package com.example.binson.goalrush.sync;

import android.content.Context;

import com.example.binson.goalrush.utils.NetworkUtils;

import java.net.URL;

public class ArticleSyncTask {
    synchronized public static void syncArticles(Context context) {
        try {
            URL url = NetworkUtils.getUrl();
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
