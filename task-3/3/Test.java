package ru.mirea.senla.task3.task3_3;

public class Test {
    public static void main(String[] args) {
        Director gl = new Director(new ILineStep[] {
                new BuildFirstPart(),
                new BuildSecondPart(),
                new BuildThirdPart()
        });
        IProduct glasses = gl.assembleProduct(new Instalation());
    }
}