package ru.mirea.senla.task3.task3_2;

public class Carnation extends Flower {
    public Carnation() {
        super(90, "Гвоздика");
    }
    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
