package com.example.alainbansais.core;

import java.util.concurrent.Executor;

public class SyncExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
