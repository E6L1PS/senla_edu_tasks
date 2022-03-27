package ru.mirea.senla.task3.task3_2;

public class Test {
    public static void main(String[] args) {
        Bouquet bouquet = new Bouquet();

        bouquet.addFlower(new Rose(Color.WHITE));
        bouquet.addFlower(new Rose(Color.RED));
        bouquet.addFlower(new Rose(Color.RED));
        bouquet.addFlower(new Rose(Color.YELLOW));
        bouquet.addFlower(new Rose(Color.PINK));
        bouquet.addFlower(new Pion());
        bouquet.addFlower(new Pion());
        bouquet.addFlower(new Pion());
        bouquet.addFlower(new Carnation());
        bouquet.addFlower(new Carnation());
        bouquet.addFlower(new Tulip());

        System.out.println("В букете:");
        bouquet.showBouquet();
        System.out.println("Сумма букета " + bouquet.calculateSum());
    }
}
