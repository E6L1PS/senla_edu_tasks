package ru.mirea.senla.task3.task3_2;

public class Pion extends Flower {
    private static final int cost = 40;
    private static final String name = "Пион";

    public Pion() {
        super(cost, name);
    }

    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
