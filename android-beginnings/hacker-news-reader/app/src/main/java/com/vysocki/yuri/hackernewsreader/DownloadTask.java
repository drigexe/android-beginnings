package com.vysocki.yuri.hackernewsreader;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadTask extends AsyncTask<String, Void, String> {

    private String result = "";

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream;
        InputStreamReader reader;
        int data;
        char current;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            reader = new InputStreamReader(inputStream);
            data = reader.read();

            while (data != -1) {
                current = (char) data;
                result += current;
                data = reader.read();
            }

            Log.i("URLContent", result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
