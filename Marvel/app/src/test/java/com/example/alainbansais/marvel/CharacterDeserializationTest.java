package com.example.alainbansais.marvel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class CharacterDeserializationTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void deserializeOneElement_shouldReturnOneCharacter() throws IOException {
        // Given
        final String json = "{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\"}";

        // When
        final Character character = mapper.readValue(json, Character.class);

        // Then
        assertNotNull(character);
        assertEquals("3-D Man", character.getNickname());
        assertEquals("1011334", character.getId());
        assertEquals("", character.getDescription());
    }

    @Test
    public void deserializeElementsInList_shouldReturnDifferentCharacters() throws IOException {
        // Given
        final String json = "{\"offset\":0,\"limit\":40,\"total\":1485,\"count\":40,\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\"},{\"id\":1011500,\"name\":\"TOTO\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0500\"},{\"id\":1011600,\"name\":\"Mars\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0600\"}]}";
        JsonNode test = mapper.readTree(json);
        ArrayList<Character> characters = new ArrayList<>();
        JsonNode child = test.get("results");

        // When
        Iterator<JsonNode> iterator = child.elements();
        while (iterator.hasNext()) {
            JsonNode character = iterator.next();
            characters.add(mapper.readValue(character.traverse(), Character.class));
        }

        // Then
        assertNotNull(characters);
        assertEquals("Mars",characters.get(2).getNickname());
    }
}