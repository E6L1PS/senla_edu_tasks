package ru.mirea.senla.task3.task3_3;

public class HousingLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Компонент 'корпус' создан");
        return new Housing();
    }
}
