package ru.mirea.senla.task3.task3_1;

public class NumberGenerator {

    public static int generateRandomNumber() {
        int number = new java.util.Random().nextInt(899) + 100;
        return number;
    }

    public static int calculateSumDigits(int number) {
        int sum = 0;
        for (; number != 0; number /= 10) {
            sum += (number % 10);
        }
        return sum;
    }

    public static void main(String[] args) {
        int number = generateRandomNumber();
        System.out.println("Number: " + number);
        System.out.println("Sum of digits: " + calculateSumDigits(number));
    }

}
