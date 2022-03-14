package ru.mirea.senla.task3.task3_3;

public class BuildThirdPart implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Компонент 'дужки' создан");
        return new Shackles();
    }
}
