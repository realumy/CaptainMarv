package com.example.alainbansais.marvel;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParsingMarvelApi {
    private final ObjectMapper mapper;

    public ParsingMarvelApi(ObjectMapper mapper) {this.mapper = mapper;}


    public List<Character> parseCharactersList(InputStream json) {
        List<Character> characters = new ArrayList<>();
        try {
            JsonNode requestTree = mapper.readTree(json);

            JsonNode data = requestTree.get("data");
            if (data != null) {
                JsonNode results = data.get("results");
                if (results != null) {
                    Iterator<JsonNode> iterator = results.elements();

                    while (iterator.hasNext()) {
                        JsonNode character = iterator.next();
                        characters.add(mapper.treeToValue(character, Character.class));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

}
