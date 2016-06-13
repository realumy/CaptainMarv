package com.example.alainbansais.marvel;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CharacterDeserializationTest {

    @Test
    public void deserializeOneElement_shouldReturnOneCharacter() throws IOException {
        // Given
        final String json = "{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\"}";
        ObjectMapper mapper;
        mapper = new ObjectMapper();

        // When
        final Character character = mapper.readValue(json, Character.class);

        // Then
        assertNotNull(character);
        assertEquals("3-D Man", character.getNickname());
        assertEquals("1011334", character.getId());
        assertEquals("", character.getDescription());
    }

    @Test
    public void deserializeListOfElements_shouldReturnCharacters() throws IOException {

        // Given
        final String json = "{\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\"},{\"id\":1011500,\"name\":\"TOTO\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0500\"},{\"id\":1011600,\"name\":\"Mars\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0600\"}]}";
        ObjectMapper mapper;
        mapper = new ObjectMapper();
        JsonNode test = mapper.readTree(json);
        ArrayList<Character> characters = new ArrayList<>();

        // When
        Iterator<JsonNode> iterator = test.getElements();
        while (iterator.hasNext()) {
            JsonNode character = iterator.next();
            characters = mapper.readValue(character, new TypeReference<ArrayList<Character>>() {});
        }

        // Then
        assertNotNull(characters);
        assertEquals("Mars",characters.get(2).getNickname());
    }

    @Test
    public void deserializeElementsInList_shouldReturnDifferentCharacters() throws IOException {

        // Given
        final String json = "{\"offset\":0,\"limit\":40,\"total\":1485,\"count\":40,\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\"},{\"id\":1011500,\"name\":\"TOTO\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0500\"},{\"id\":1011600,\"name\":\"Mars\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0600\"}]}";
        ObjectMapper mapper;
        mapper = new ObjectMapper();
        JsonNode test = mapper.readTree(json);
        ArrayList<Character> characters = new ArrayList<>();
        JsonNode child = test.get("results");


        // When
        Iterator<JsonNode> iterator = child.getElements();
        while (iterator.hasNext()) {
            JsonNode character = iterator.next();
            characters.add(mapper.readValue(character, Character.class));
        }

        // Then
        assertNotNull(characters);
        assertEquals("Mars",characters.get(2).getNickname());
    }
}