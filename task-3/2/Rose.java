package ru.mirea.senla.task3.task3_2;

public class Rose extends Flower{
    private String color;
    public Rose(String color) {
        super(80, "Роза");
        this.color =color;
        switch (color){
            case "red":
                setCost(80);
                break;
            case "pink":
                setCost(100);
                break;
            case "white":
                setCost(110);
                break;
            case "hot pink":
                setCost(130);
                break;
            case "novelty":
                setCost(160);
                break;
            case "lavender":
                setCost(150);
                break;
            case "yellow":
                setCost(70);
                break;
            case "green":
                setCost(300);
                break;
            case "orange":
                setCost(250);
                break;
            case "tinted":
                setCost(330);
                break;

            default:
                setCost(0);
                System.out.println("There is no '"+color+"' color");
        }

    }

    @Override
    public String toString() {
        return getName() + ", цвет "+ color + ", цена " + getCost();
    }
}
