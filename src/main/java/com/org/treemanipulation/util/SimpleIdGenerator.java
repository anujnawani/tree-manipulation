package com.hingehealth.demo.util;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleIdGenerator implements IdGenerator {
    private static AtomicLong sequence = new AtomicLong(1);

    public long generateId() {
        return sequence.getAndIncrement();
    }
}