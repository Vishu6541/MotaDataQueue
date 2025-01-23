package com.motadata.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    public void produce(String message) throws InterruptedException {
        queue.put(message);
    }

    public String consume() throws InterruptedException {
        return queue.take();
    }
}
