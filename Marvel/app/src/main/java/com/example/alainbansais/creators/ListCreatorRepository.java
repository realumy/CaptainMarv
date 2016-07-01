package com.example.alainbansais.creators;

import com.example.alainbansais.model.CreatorMarvel;

import java.util.List;

public interface ListCreatorRepository {
    List<CreatorMarvel> get() throws RepositoryCreatorException;

    class RepositoryCreatorException extends Exception {}
}
