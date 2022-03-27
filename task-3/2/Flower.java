package ru.mirea.senla.task3.task3_2;

public abstract class Flower {
    private final int cost;
    private final String name;

    public Flower(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "cost is " + cost +
                ", name is " + name +
                '}';
    }
}
