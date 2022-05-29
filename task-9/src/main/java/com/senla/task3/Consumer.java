package com.senla.task3;

import java.util.List;

public class Consumer extends Thread {
    private List<Integer> buffer;

    public Consumer(List<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        synchronized (buffer) {
            while (true) {
                if (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Consumed " + buffer);
                    buffer.clear();

                    buffer.notifyAll();
                }
            }
        }

    }
}
