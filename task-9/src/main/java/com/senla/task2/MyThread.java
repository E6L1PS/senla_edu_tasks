package com.senla.task2;

public class MyThread extends Thread {

    private Object lock;

    public MyThread(Object lock) {
        this.lock = lock;
    }


    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                try {
                    System.out.println(getName());
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
