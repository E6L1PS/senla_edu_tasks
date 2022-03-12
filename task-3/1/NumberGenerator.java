package ru.mirea.senla.task3.task3_1;

public class NumberGenerator {
    public static void main(String[] args) {
        int number = new java.util.Random().nextInt(999);
        int sum = 0;
        while(number < 100) {
            number = new java.util.Random().nextInt(999);
        }
        System.out.println("Number: " + number);

        for(; number != 0; number /= 10) {
            sum += (number % 10);
        }
        System.out.println("Sum of digit: " + sum);
    }
}
