package com.example.alainbansais.marvel;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class CallbackTest {
    private String urlLabel = "http://gateway.marvel.com/v1/public/characters?limit=40&ts=1&apikey=e3abbc2f4d802fb69bc83f77a5463870&hash=24807b3e495349dd682c2171df9bd96c";
    private HttpURLConnection urlConnection;
    private URL url;
    private ArrayList<Character> characters;

    @Before
    public void setUp() throws MalformedURLException {
        url = new URL(urlLabel);
    }

    @Test
    public void testDoInBackground() throws Exception {
        try {
            // Given
            urlConnection = (HttpURLConnection) url.openConnection();
            ObjectMapper mapper;
            mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
            JsonNode stateRequest = null;

            // When
            stateRequest = mapper.readTree(urlConnection.getInputStream());
            JsonNode data = stateRequest.get("data");
            JsonNode results = data.get("results");
            Iterator<JsonNode> firstChild = results.getElements();

            // Then
            assertEquals("3-D Man", results.get(0).get("name").getTextValue());
            while (firstChild.hasNext()) {
                JsonNode character = firstChild.next();
                //characters = mapper.readValue(character, new TypeReference<List<Character>>() {});
                assertEquals("tr",mapper.readValue(character, new TypeReference<List<Character>>() {}));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

    }

}