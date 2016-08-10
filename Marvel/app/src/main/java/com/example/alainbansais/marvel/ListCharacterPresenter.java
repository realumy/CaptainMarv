package com.example.alainbansais.marvel;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

public class ListCharacterPresenter {
    private final ListCharacterRepository repository;
    private final Bus bus;
    private final ListCharacterView view;
    private SearcherService searcherService;

    public ListCharacterPresenter(ListCharacterView view) {
        this.view = view;
        repository = new NetListCharacterRepository();
        bus = MarvelBus.SINGLETON;
    }

    public void load() {
        view.displayLoading();
        new Thread(new ListCharacterRunnable(repository, bus)).start();
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onRepositorySuccess(ListCharacterRunnable.SuccessEvent event) {
        final List<Character> characters = event.getCharacters();
        if (characters.isEmpty()) {
            view.displayEmptyResult();
        } else {
            view.displayCharacterList(characters);
            searcherService = new SearcherService(characters);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onRepositoryError(ListCharacterRunnable.ErrorEvent event) {
        view.displayError();
    }

    public void search(String pattern){
        searcherService.search(pattern);
        view.displayCharacterList(searcherService.result);
    }
}