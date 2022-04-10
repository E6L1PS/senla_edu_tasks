package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository {

    List<Request> requests = new ArrayList<>();

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Book book) {
        Request req = book.getRequest();

        if (req.getNumber() == 0) {
            requests.add(req);
        }

        req.addRequest();
    }

    public void clearRequests(Book book) {
        Request request = book.getRequest();
        request.clearRequests();
        requests.remove(request);
    }
}
