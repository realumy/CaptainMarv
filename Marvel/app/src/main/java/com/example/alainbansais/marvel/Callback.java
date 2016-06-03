package com.example.alainbansais.marvel;

import android.os.AsyncTask;
import android.widget.TextView;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Callback extends AsyncTask<Void, Void, String> {
    protected MainActivity context;
    private String API_URL = "http://gateway.marvel.com/v1/public/characters?";
    private String TIME_STAMP = "1";
    private String API_KEY = "e3abbc2f4d802fb69bc83f77a5463870";
    private String HASH = "24807b3e495349dd682c2171df9bd96c";
    private URL url;
    private HttpURLConnection urlConnection;
    private ArrayList<Character> characters;

    public Callback(MainActivity currentContext) {
        context = currentContext;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context.findViewById(R.id.progressBar).setVisibility(VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            url = new URL(API_URL + "ts=" + TIME_STAMP + "&apikey=" + API_KEY + "&hash=" + HASH);
            urlConnection = (HttpURLConnection) url.openConnection();

            ObjectMapper mapper;
            mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
            JsonNode stateRequest = null;

            stateRequest = mapper.readTree(urlConnection.getInputStream());
            JsonNode data = stateRequest.get("data");
            JsonNode results = data.get("results");


            Iterator<JsonNode> iterator = results.getElements();

            while (iterator.hasNext()) {
                JsonNode character = iterator.next();
                characters = mapper.readValue(character, new TypeReference<List<Character>>() {});

            }

            return null;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
                    /*List<Character> characters = mapper.readValue(results,
                                                                  new TypeReference<List<Character>>() {});


                    for (final Character character : characters) {
                        Log.d("TEST", character.getId());
                        Log.d("TEST", character.getName());
                        Log.d("TEST", character.getDescription());
                        Log.d("TEST", character.getPathImg());
                    }*/


        return null;
    }


    @SuppressWarnings("ConstantConditions")
    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        context.findViewById(R.id.progressBar).setVisibility(GONE);
        TextView test = (TextView) context.findViewById(R.id.responseView);
        test.setText(response);


    }

}
