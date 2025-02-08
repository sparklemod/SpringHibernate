package app.model;

import org.springframework.stereotype.Component;

@Component
public class Timer {

    private final Long nanoTime;

    public Long getTime() {
        return nanoTime;
    }

    public Timer() {
        this.nanoTime = System.currentTimeMillis();
    }
}
