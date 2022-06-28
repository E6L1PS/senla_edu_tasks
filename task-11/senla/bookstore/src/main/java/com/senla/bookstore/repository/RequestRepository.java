package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.util.ConnectorDB;
import com.senla.configure.annotations.Singleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RequestRepository extends AbstractRepository<Integer, Request> implements IRequestRepository<Integer, Request> {
    public static final String SQL_SELECT_ALL_REQUESTS = "SELECT * FROM requests";
    public static final String SQL_SELECT_BOOK_ID = "SELECT * FROM requests WHERE id=?";

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try (Statement statement = ConnectorDB.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_REQUESTS);
            while (rs.next()) {
                requests.add(new Request(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("number")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requests;
    }

    @Override
    public Request findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Request entity) {
        return false;
    }

    @Override
    public boolean create(Request entity) {
        return false;
    }

    @Override
    public Request update(Request entity) {
        return null;
    }

    @Override
    public void addRequest(Book book) {
        Request req = book.getRequest();

        if (req.getNumber() == 0) {
            // requests.add(req);
        }

        req.addRequest();
    }
}

    /*
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
*/
