package ru.mirea.senla.task3.task3_3;

public class Instalation implements IProduct {
    private IProductPart lenses;
    private IProductPart housing;
    private IProductPart shackles;

    @Override
    public void installFirstPart(IProductPart part) {
        this.lenses = part;
        System.out.println("Компонент 'линзы' установлен");
    }

    @Override
    public void installSecondPart(IProductPart part) {
        this.housing = part;
        System.out.println("Компонент 'корпус' установлен");
    }

    @Override
    public void installThirdPart(IProductPart part) {
        this.shackles = part;
        System.out.println("Компонент 'дужки' установлен");
    }
}
