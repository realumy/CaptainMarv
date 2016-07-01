package com.example.alainbansais.creators;

import com.example.alainbansais.core.AsyncExecutor;
import com.example.alainbansais.core.MarvelBus;
import com.example.alainbansais.model.CreatorMarvel;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.concurrent.Executor;

public class ListCreatorPresenter {
    private final ListCreatorRepository repository;
    private final Bus bus;
    private final ListCreatorView view;
    private final Executor executor;

    public static ListCreatorPresenter newInstance(final ListCreatorView view){
        return new ListCreatorPresenter(view,
                                        new RetrofitListCreatorRepository(),
                                        new AsyncExecutor(),
                                        MarvelBus.SINGLETON);
    }

    ListCreatorPresenter(
            ListCreatorView view,
            ListCreatorRepository repository,
            Executor executor,
            Bus bus
    ) {
        this.view = view;
        this.repository = repository;
        this.executor = executor;
        this.bus = bus;
    }

    public void load() {
        view.displayLoading();
        executor.execute(new ListCreatorRunnable(repository, bus));
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onRepositorySuccess(ListCreatorRunnable.SuccessEvent event) {
        final List<CreatorMarvel> creators = event.getCreators();
        if (creators.isEmpty()) {
            view.displayEmptyResult();
        } else {
            view.displayCreatorList(creators);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onRepositoryError(ListCreatorRunnable.ErrorEvent event) {
        view.displayError();
    }
}
