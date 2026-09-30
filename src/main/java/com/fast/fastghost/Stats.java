package com.fast.fastghost;

import java.util.concurrent.atomic.AtomicLong;

public class Stats {

    private final AtomicLong totalSwings = new AtomicLong(0);
    private final AtomicLong processedHits = new AtomicLong(0);

    public void incrementTotalSwings() {
        totalSwings.incrementAndGet();
    }

    public void incrementProcessedHits() {
        processedHits.incrementAndGet();
    }

    public long getTotalSwings() {
        return totalSwings.get();
    }

    public long getProcessedHits() {
        return processedHits.get();
    }
}
