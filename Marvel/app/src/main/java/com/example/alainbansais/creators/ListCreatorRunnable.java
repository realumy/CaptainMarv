package com.example.alainbansais.creators;

import com.example.alainbansais.model.CreatorMarvel;
import com.squareup.otto.Bus;

import java.util.List;

public class ListCreatorRunnable implements Runnable {
    private final ListCreatorRepository creatorRepository;
    private final Bus bus;

    public ListCreatorRunnable(ListCreatorRepository repository, Bus bus) {
        this.creatorRepository = repository;
        this.bus = bus;
    }

    @Override
    public void run() {
        List<CreatorMarvel> creators = null;
        try {
            creators = creatorRepository.get();
        } catch (ListCreatorRepository.RepositoryCreatorException e) {
            e.printStackTrace();
        } finally {
            bus.post(creators != null
                             ? new SuccessEvent(creators)
                             : new ErrorEvent());
        }
    }

    public static class SuccessEvent {

        private final List<CreatorMarvel> creators;

        public SuccessEvent(List<CreatorMarvel> creators) {this.creators = creators;}

        public List<CreatorMarvel> getCreators() {
            return creators;
        }
    }

    public static class ErrorEvent {

    }
}
