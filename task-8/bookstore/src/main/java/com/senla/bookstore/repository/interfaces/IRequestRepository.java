package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;

import java.util.List;

public interface IRequestRepository extends IRepository {

    Request getRequestById(int id);

    List<Request> getRequests();

    void addRequest(Book book);

    void deleteRequestsByBook(Book book);

}
