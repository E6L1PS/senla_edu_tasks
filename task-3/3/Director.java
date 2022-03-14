package ru.mirea.senla.task3.task3_3;

public class Director implements IAssemblyLine {
    private ILineStep[] build;

    public Director(ILineStep[] build) {
        this.build = build;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installFirstPart(build[0].buildProductPart());
        product.installSecondPart(build[1].buildProductPart());
        product.installThirdPart(build[2].buildProductPart());
        System.out.println("Продукт 'очки' собран");
        return product;
    }
}
