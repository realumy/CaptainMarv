package com.example.alainbansais.marvel;

import java.util.ArrayList;
import java.util.List;

public class SearcherService {
    public List<Character> result;
    public List<Character> allCharacters;


    public SearcherService(List<Character> listCharacter) {
        allCharacters = listCharacter;
    }

    public void search(String s) {
        result = new ArrayList<>(allCharacters);
        if (!s.isEmpty()) {
            result.clear();
            for (Character character : allCharacters) {
                if(character.getNickname().startsWith(s)){
                    result.add(character);
                }
            }
        }
    }
}
