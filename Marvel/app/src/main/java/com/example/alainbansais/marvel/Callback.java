package com.example.alainbansais.marvel;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Callback extends AsyncTask<Void, Void, Integer> {
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    protected MainActivity context;
    private String API_URL = "http://gateway.marvel.com/v1/public/characters?";
    private String TIME_STAMP = "1";
    private String API_KEY = "e3abbc2f4d802fb69bc83f77a5463870";
    private String HASH = "24807b3e495349dd682c2171df9bd96c";
    private URL url;
    private HttpURLConnection urlConnection;
    private ArrayList<Character> characters = new ArrayList<>();

    public Callback(MainActivity currentContext) {
        context = currentContext;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context.findViewById(R.id.progressBar).setVisibility(VISIBLE);
        myRecyclerView = (RecyclerView) context.findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected Integer doInBackground(Void... params) {
        Integer result = 0;
        try {
            url = new URL(API_URL + "limit=60&ts=" + TIME_STAMP + "&apikey=" + API_KEY + "&hash=" + HASH);
            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                ObjectMapper mapper;
                mapper = new ObjectMapper();
                JsonNode stateRequest;

                stateRequest = mapper.readTree(urlConnection.getInputStream());
                JsonNode data = stateRequest.get("data");
                JsonNode results = data.get("results");

                Iterator<JsonNode> iterator = results.getElements();

                while (iterator.hasNext()) {
                    JsonNode character = iterator.next();
                    characters.add(mapper.readValue(character, Character.class));
                }
                result = 1;
            } else {
                result = 0;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;
    }


    @SuppressWarnings("ConstantConditions")
    protected void onPostExecute(Integer result) {
        if (result == 1) {
            context.findViewById(R.id.progressBar).setVisibility(GONE);
            myAdapter = new MyAdapter(characters);
            myRecyclerView.addItemDecoration(new DividerItemDecoration(context));
            myRecyclerView.setAdapter(myAdapter);
        }
    }

}
