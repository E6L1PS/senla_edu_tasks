package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Request;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements IRequestRepository {

    private static RequestRepository instance = new RequestRepository();
    List<Request> requests = new ArrayList<>();

    private RequestRepository() {

    }

    @Override
    public Request getRequestById(int id) {
        return requests.stream().filter(requests -> requests.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void addRequest(Book book) {
        Request req = book.getRequest();

        if (req.getNumber() == 0) {
            requests.add(req);
        }

        req.addRequest();
    }

    @Override
    public void deleteRequestsByBook(Book book) {
        Request request = book.getRequest();
        request.clearRequests();
        requests.remove(request);
    }

    public static RequestRepository getInstance() {
        return instance;
    }

}
