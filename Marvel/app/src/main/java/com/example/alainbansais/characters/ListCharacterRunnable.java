package com.example.alainbansais.characters;

import com.example.alainbansais.model.Character;
import com.squareup.otto.Bus;

import java.lang.*;
import java.util.List;

public class ListCharacterRunnable implements Runnable {
    private final ListCharacterRepository repository;
    private final Bus bus;

    public ListCharacterRunnable(ListCharacterRepository repository, Bus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public void run() {
        List<Character> characters = null;
        try {
            characters = repository.get();
        } catch (ListCharacterRepository.RepositoryException e) {
            e.printStackTrace();
        } finally {
            bus.post(characters != null
                             ? new SuccessEvent(characters)
                             : new ErrorEvent());
        }
    }

    public static class SuccessEvent {

        private final List<Character> characters;

        public SuccessEvent(List<Character> characters) {this.characters = characters;}

        public List<Character> getCharacters() {
            return characters;
        }
    }

    public static class ErrorEvent {

    }
}