package com.example.alainbansais.marvel;

import java.util.List;

public interface ListCharacterView {
    void displayLoading();

    void displayCharacterList(List<Character> characters);

    void displayEmptyResult();

    void displayError();
}
