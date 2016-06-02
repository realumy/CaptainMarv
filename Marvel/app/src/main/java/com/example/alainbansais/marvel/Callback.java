package com.example.alainbansais.marvel;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.alainbansais.marvel.R.id.progressBar;
import static com.example.alainbansais.marvel.R.id.responseView;

public class Callback extends AsyncTask<Void, Void, String> {
    protected MainActivity context;
    private String API_URL = "http://gateway.marvel.com/v1/public/characters?";
    private String TIME_STAMP = "1";
    private String API_KEY = "e3abbc2f4d802fb69bc83f77a5463870";
    private String HASH = "24807b3e495349dd682c2171df9bd96c";

    public Callback(MainActivity currentContext) {
        context = currentContext;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context.findViewById(progressBar).setVisibility(VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL(API_URL + "ts=" + TIME_STAMP + "&apikey=" + API_KEY + "&hash=" + HASH);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("ConstantConditions")
    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        context.findViewById(progressBar).setVisibility(GONE);
        TextView test = (TextView) context.findViewById(responseView);
        try {
            JSONObject testJson = new JSONObject(response);
            //JSONObject yeah = testJson.getJSONObject(0);
            //String code = yeah.getString("code");
            test.setText(response);
           } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
