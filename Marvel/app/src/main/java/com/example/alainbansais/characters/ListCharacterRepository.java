package com.example.alainbansais.characters;

import com.example.alainbansais.model.Character;

import java.lang.*;
import java.util.List;

public interface ListCharacterRepository {
    List<Character> get() throws RepositoryException;

    class RepositoryException extends Exception {}
}