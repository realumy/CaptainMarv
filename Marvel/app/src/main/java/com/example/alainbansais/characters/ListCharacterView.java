package com.example.alainbansais.characters;

import com.example.alainbansais.model.Character;

import java.util.List;

public interface ListCharacterView {
    void displayLoading();

    void displayCharacterList(List<Character> characters);

    void displayEmptyResult();

    void displayError();
}