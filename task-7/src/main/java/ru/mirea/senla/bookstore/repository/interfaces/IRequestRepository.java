package ru.mirea.senla.bookstore.repository.interfaces;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Request;

import java.util.List;

public interface IRequestRepository extends IRepository {

    Request getRequestById(int id);

    List<Request> getRequests();

    void addRequest(Book book);

    Request updateRequest(Request request);

    void deleteRequests(Book book);

    void deleteRequestsById(int id);

}
