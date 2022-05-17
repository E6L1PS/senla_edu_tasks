package com.senla.task4;

import java.util.Date;
import java.util.Scanner;

public class MyThread extends Thread {
    private long n;

    public MyThread() {
        System.out.println("Введите количество секунд");
        this.n = 1000 * new Scanner(System.in).nextInt();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                System.out.println(new Date());
                try {
                    wait(n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
