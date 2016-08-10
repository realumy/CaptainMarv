package com.example.alainbansais.marvel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearcherServiceTest {
    final List<Character> listCharacters = new ArrayList<>();


    @Before
    public void setUp() {
        listCharacters.add(new Character("0","Danyboy","Beaugosse",null));
        listCharacters.add(new Character("1","DaClo","Belle gosse",null));
        listCharacters.add(new Character("2","Alain","Paillettes",null));
        listCharacters.add(new Character("3","Elise","a des idées",null));
        listCharacters.add(new Character("4","Thomas","Dictator del ombre",null));
    }

    @Test
    public void searchWithEmptyPatternShouldReturnFullList() {
        // Given
        SearcherService searcherService = new SearcherService(listCharacters);
        // When
        searcherService.search("");
        // Then
        Assert.assertEquals(5,searcherService.result.size());
    }

    @Test
    public void searchWithAShouldFindCharacterBeginningWithA() {
        // Given
        SearcherService searcherService = new SearcherService(listCharacters);
        // When
        searcherService.search("A");
        // Then
        Assert.assertEquals(1,searcherService.result.size());
        Assert.assertEquals("Alain",searcherService.result.get(0).getNickname());
    }

    @Test
    public void searchWithEShouldFindCharacterBeginningWithE() {
        // Given
        SearcherService searcherService = new SearcherService(listCharacters);
        // When
        searcherService.search("E");
        // Then
        Assert.assertEquals(1,searcherService.result.size());
        Assert.assertEquals("Elise",searcherService.result.get(0).getNickname());
    }

    @Test
    public void searchWithTwoLettersShouldFindCharacterBeginningWithTheseTwoLetters() {
        // Given
        SearcherService searcherService = new SearcherService(listCharacters);
        // When
        searcherService.search("Da");
        // Then
        Assert.assertEquals(2,searcherService.result.size());
        Assert.assertEquals("Danyboy",searcherService.result.get(0).getNickname());
        Assert.assertEquals("DaClo",searcherService.result.get(1).getNickname());
    }


}