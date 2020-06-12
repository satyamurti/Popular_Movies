package com.mrspd.moviesappstage1;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public class MyExcecutors {

    private static final Object LOCK = new Object();
    private static MyExcecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private MyExcecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static MyExcecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MyExcecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(2),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
