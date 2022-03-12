package ru.mirea.senla.task3.task3_2;

public class Pion extends Flower {
    public Pion() {
        super(40, "Пион");
    }
    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
