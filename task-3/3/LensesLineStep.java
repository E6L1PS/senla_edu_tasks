package ru.mirea.senla.task3.task3_3;

public class LensesLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Компонент 'линзы' создан");
        return new Lenses();
    }
}
