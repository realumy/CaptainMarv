package com.example.alainbansais.creators;

import com.example.alainbansais.model.CreatorMarvel;

import java.util.List;

public interface ListCreatorView {
    void displayLoading();

    void displayCreatorList(List<CreatorMarvel> creators);

    void displayEmptyResult();

    void displayError();
}
