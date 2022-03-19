package ru.mirea.senla.task3.task3_2;

public class Tulip extends Flower {
    private static final int cost = 50;
    private static final String name = "Тюльпан";

    public Tulip() {
        super(cost, name);
    }


    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
