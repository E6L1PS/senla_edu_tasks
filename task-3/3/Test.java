package ru.mirea.senla.task3.task3_3;

public class Test {
    public static void main(String[] args) {
        AssemblyLine glassesLine = new AssemblyLine(new LensesLineStep(), new HousingLineStep(), new ShacklesLineStep());
        IProduct glasses = glassesLine.assembleProduct(new Instalation());
    }
}