package ru.mirea.senla.task3.task3_2;

public class Bouquet {
    public static int checkSum(Flower flowers[]) {
        int sum = 0;
        for (int i = 0; i < flowers.length; i++) {
            sum += flowers[i].getCost();
        }
        return sum;
    }
    public static void main(String[] args) {
        Flower[] flowers = new Flower[]{
                new Rose("white"),
                new Rose("red"),
                new Rose("red"),
                new Rose("yellow"),
                new Rose("pink"),
                new Pion(),
                new Pion(),
                new Pion(),
                new Pion(),
                new Carnation(),
                new Carnation(),
                new Tulip()
        };
        System.out.println("В букете:");
        for (int i = 0; i < flowers.length; i++) System.out.println(flowers[i].toString());
        System.out.println("Сумма букета " + checkSum(flowers));
    }
}
