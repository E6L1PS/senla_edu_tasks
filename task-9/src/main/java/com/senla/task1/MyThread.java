package com.senla.task1;

public class MyThread extends Thread {

    public void run() {
        synchronized (this) {
            System.out.println(getState() + " - RUNNABLE");

            try {
                wait(1000);

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void proceed() {
        System.out.println(getState() + " - WAITING");
        notify();
        System.out.println(getState() + " - BLOCKED");
    }
}
