package ru.mirea.senla.task3.task3_2;

public class Tulip extends Flower {
    public Tulip() {
        super(50, "Тюльпан");
    }
    @Override
    public String toString() {
        return getName() + ", цена " + getCost();
    }
}
