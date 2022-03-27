package ru.mirea.senla.task3.task3_4;

public class Main {
    public static void main(String[] args) {
        Orders orders = new Orders();

        orders.addToWarehouse(new int[]{0, 1, 2, 3});
        System.out.println("\n\n");
        orders.addOrder(new Customer(1, "Виктор",18), new int[]{0, 1, 2, 3, 4});
        orders.addRequest(3, orders.getOrder(0));
        orders.getOrder(0).setStatus(OrderStatus.COMPLETED);
        System.out.println("Статус заказа " + orders.getOrder(0).getId() + " " + orders.getOrder(0).getStatus());
        System.out.println("\n\n");
        orders.removeToWarehouse(new int[]{0, 1});
        orders.addOrder(new Customer(2, "Сергей",28), new int[]{0, 1, 4});
        orders.addRequest(5, orders.getOrder(1));
        orders.removeOrder(orders.getOrder(1));
    }
}
