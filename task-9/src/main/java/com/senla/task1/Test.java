package com.senla.task1;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        System.out.println(myThread.getState() + " - NEW");

        myThread.start();

        Thread.sleep(500);
        System.out.println(myThread.getState() + " - TIMED_WAITING");

        Thread.sleep(1000);
        myThread.proceed();

        Thread.sleep(1000);
        System.out.println(myThread.getState() + " - TERMINATED");
    }
}