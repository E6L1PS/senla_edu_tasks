package ru.mirea.senla.task3.task3_2;

public enum Color {
    RED(80, "красный"), PINK(100, "розовый"), WHITE(110, "белый"), HOT_PINK(130, "ярко-розовый"),
    LAVENDER(150, "лавандовый"), YELLOW(70, "желтый"), GREEN(300, "зеленый"), ORANGE(250, "оранжевый");
    private final int cost;
    private final String name;

    Color(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getNameColor() {
        return name;
    }

}
