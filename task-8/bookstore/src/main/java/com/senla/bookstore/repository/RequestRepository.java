package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.util.json.JsonReader;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class RequestRepository implements IRequestRepository {

    private List<Request> requests;

    public RequestRepository() {
        requests = JsonReader.readRepository("Requests.json", Request.class);
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

}