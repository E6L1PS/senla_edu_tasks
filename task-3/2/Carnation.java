package ru.mirea.senla.task3.task3_2;

public class Carnation extends Flower {
    private static final int cost = 90;
    private static final String name = "Гвоздика";

    public Carnation() {
        super(cost, name);
    }

    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
