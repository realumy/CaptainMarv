package com.example.alainbansais.marvel;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiRequest extends AsyncTask<Void, Void, List<Character>> {
    private static final String API_URL = "http://gateway.marvel.com/v1/public/characters?";
    private static final String TIME_STAMP = "1";
    private static final String API_KEY = "e3abbc2f4d802fb69bc83f77a5463870";
    private static final String HASH = "24807b3e495349dd682c2171df9bd96c";
    private HttpURLConnection urlConnection;
    private ParsingMarvelApi parsable = new ParsingMarvelApi(new ObjectMapper());
    private AsyncResponse delegate = null;

    public ApiRequest(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<Character> doInBackground(Void... params) {
        List<Character> result = new ArrayList<>();
        try {
            URL url = new URL(API_URL + "limit=60&ts=" + TIME_STAMP + "&apikey=" + API_KEY + "&hash=" + HASH);
            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                result.addAll(parsable.parseCharactersList(urlConnection.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    protected void onPostExecute(final List<Character> result) {
        delegate.processFinish(result);
    }

}
