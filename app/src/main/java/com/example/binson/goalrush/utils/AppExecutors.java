package com.example.binson.goalrush.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors INSTANCE;
    private final Executor diskIO;
    private final Executor networkIO;

    private AppExecutors(Executor diskIO, Executor networkIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    public static AppExecutors getInstance() {
        if (INSTANCE == null) {
            synchronized (AppExecutors.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3));
                }
            }
        }
        return INSTANCE;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }
}
