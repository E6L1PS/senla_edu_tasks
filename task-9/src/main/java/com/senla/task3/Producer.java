package com.senla.task3;

import java.util.List;
import java.util.Random;

public class Producer extends Thread {
    private List<Integer> buffer;
    private int bufferSize = 8;

    public Producer(List<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        synchronized (buffer) {
            while (true) {
                if (buffer.size() == bufferSize) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Integer number = new Random().nextInt(100);
                    System.out.println("Produced " + number);
                    buffer.add(number);

                    buffer.notifyAll();
                }
            }
        }
    }
}
