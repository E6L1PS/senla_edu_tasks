package com.senla.task2;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        MyThread firstThread = new MyThread(lock);
        MyThread secondThread = new MyThread(lock);

        firstThread.start();
        secondThread.start();
    }
}


