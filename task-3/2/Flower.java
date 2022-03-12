package ru.mirea.senla.task3.task3_2;

public abstract class Flower {
    private int cost;
    private String name;

    public Flower(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "cost is " + cost +
                ", name is " + name +
                '}';
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }
}
