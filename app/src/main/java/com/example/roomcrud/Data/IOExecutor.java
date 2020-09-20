package com.example.roomcrud.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;


public class IOExecutor {
    private static IOExecutor executor = null;
    private static final Object LOCK = new Object();


    public void execute(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }
    public void executeCallable(Callable callable) {
        Executors.newSingleThreadExecutor().submit(callable);
    }

    public static IOExecutor getInstance() {
        if (executor == null) {
            synchronized (LOCK) {
                executor = new IOExecutor();
            }
        }
        return executor;
    }

}
