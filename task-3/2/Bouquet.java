package ru.mirea.senla.task3.task3_2;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers = new ArrayList<>();

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void showBouquet() {
        for (int i = 0; i < flowers.size(); i++) {
            System.out.println(flowers.get(i).toString());
        }
    }

    public int calculateSum() {
        int sum = 0;
        for (int i = 0; i < flowers.size(); i++) {
            sum += flowers.get(i).getCost();
        }
        return sum;
    }
}
