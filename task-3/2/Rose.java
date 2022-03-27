package ru.mirea.senla.task3.task3_2;

public class Rose extends Flower {
    private static final String name = "Роза";
    private final int cost;
    private final String nameColor;

    public Rose(Color color) {
        super(color.getCost(), name);
        this.cost = color.getCost();
        this.nameColor = color.getNameColor();
    }

    @Override
    public String toString() {
        return name + ", цвет " + nameColor + ", цена " + cost;
    }
}
