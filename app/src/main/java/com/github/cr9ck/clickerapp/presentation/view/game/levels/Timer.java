package com.github.cr9ck.clickerapp.presentation.view.game.levels;

import androidx.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer implements Runnable {
    private AtomicInteger countedTime;
    private int time;
    private AtomicBoolean isStarted = new AtomicBoolean(false);
    private @Nullable
    ScheduledExecutorService service;
    private @Nullable
    ScheduledFuture<?> scheduledFuture;
    private @Nullable
    OnTimerUpdateListener listener;

    public Timer(int time) {
        this.time = time;
        this.countedTime = new AtomicInteger(time);
    }

    @Override
    public void run() {
        if (!isStarted.get()) return;
        if (listener != null &&
                countedTime.get() >= 0) {
            listener.onTimeUpdate(countedTime.getAndDecrement());
        } else {
            stop();
        }
    }

    public void setTime(int time) {
        this.time = time;
        this.countedTime = new AtomicInteger(time);
    }

    public void setOnTimerUpdateListener(OnTimerUpdateListener listener) {
        this.listener = listener;
    }

    public void start() {
        isStarted.set(true);
        service = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = service.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
    }

    public boolean isStarted() {
        return isStarted.get();
    }

    public void stop() {
        isStarted.set(false);
        if (scheduledFuture != null) scheduledFuture.cancel(true);
        if (service != null) service.shutdown();
    }

    public void reset() {
        stop();
        countedTime.set(time);
        if (listener == null) return;
        listener.onTimeUpdate(countedTime.get());
    }

    public interface OnTimerUpdateListener {
        void onTimeUpdate(int time);
    }
}
