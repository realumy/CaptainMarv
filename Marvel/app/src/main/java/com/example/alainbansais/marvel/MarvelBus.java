package com.example.alainbansais.marvel;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public class MarvelBus extends Bus {

    public static final MarvelBus SINGLETON = new MarvelBus();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private MarvelBus() {

    }

    @Override
    public void register(final Object object) {
        if (isInMainLooper()) {
            super.register(object);
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    MarvelBus.super.register(object);
                }
            });
        }
    }

    @Override
    public void unregister(final Object object) {
        if (isInMainLooper()) {
            super.unregister(object);
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    MarvelBus.super.unregister(object);
                }
            });
        }
    }

    @Override
    public void post(final Object event) {
        if (isInMainLooper()) {
            super.post(event);
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    MarvelBus.super.post(event);
                }
            });
        }
    }

    private boolean isInMainLooper() {return Looper.myLooper() == Looper.getMainLooper();}
}
