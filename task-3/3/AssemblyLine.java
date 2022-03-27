package ru.mirea.senla.task3.task3_3;

public class AssemblyLine implements IAssemblyLine {
    private LensesLineStep lenses;
    private HousingLineStep housing;
    private ShacklesLineStep shackles;

    public AssemblyLine(LensesLineStep lenses, HousingLineStep housing, ShacklesLineStep shackles) {
        this.lenses = lenses;
        this.housing = housing;
        this.shackles = shackles;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installFirstPart(lenses.buildProductPart());
        product.installSecondPart(housing.buildProductPart());
        product.installThirdPart(shackles.buildProductPart());
        System.out.println("Продукт 'очки' собран");
        return product;
    }
}
