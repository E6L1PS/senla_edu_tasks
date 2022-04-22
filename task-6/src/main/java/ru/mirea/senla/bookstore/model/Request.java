package ru.mirea.senla.bookstore.model;

public class Request implements IEntity {

    private int id;
    private int number;
    private String name;

    public Request(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public void addRequest() {
        this.number++;
    }

    public void removeRequest() {
        this.number--;
    }

    public void clearRequests() {
        this.number = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number requests=" + number +
                "} \n";
    }
}
