package com.example.alainbansais.marvel;

import java.util.List;

public interface ListCharacterRepository {
    List<Character> get() throws RepositoryException;

    class RepositoryException extends Exception {}
}