package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.util.ConnectorDB;
import com.senla.configure.annotations.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WarehouseRepository extends AbstractRepository<Integer, Book> implements IWarehouseRepository<Integer, Book> {
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM warehouse";
    public static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM warehouse WHERE id=?";
    public static final String SQL_INSERT_BOOK = "INSERT INTO warehouse VALUES(?, ?, ?, ?, ?, ?, null , ?, ?)";
    public static final String SQL_DELETE_BOOK = "DELETE FROM warehouse WHERE id=?";
    public static final String SQL_UPDATE_BOOK = "UPDATE warehouse SET " +
            "price=?," +
            "isbn=?," +
            "name=?," +
            "description=?," +
            "status=?," +
            "publication_date=?," +
            "delivery_date=? WHERE id=?";

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = ConnectorDB.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("description"),
                        BookStatus.valueOf(rs.getString("status")),
                        rs.getDate("publication_date").toLocalDate(),
                        rs.getDate("delivery_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public Book findEntityById(Integer id) {
        Book book = null;
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_SELECT_BOOK_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            book = new Book(
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getString("isbn"),
                    rs.getString("name"),
                    rs.getString("description"),
                    BookStatus.valueOf(rs.getString("status")),
                    rs.getDate("publication_date").toLocalDate(),
                    rs.getDate("delivery_date").toLocalDate()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_INSERT_BOOK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Book entity) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_DELETE_BOOK)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Book entity) {

        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_DELETE_BOOK)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setString(3, entity.getIsbn());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getDescription());
            preparedStatement.setObject(6, entity.getStatus(), Types.OTHER);
            preparedStatement.setDate(7, Date.valueOf(entity.getPublicationDate()));
            preparedStatement.setDate(8, Date.valueOf(entity.getDeliveryDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Book update(Book entity) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_UPDATE_BOOK)) {
            preparedStatement.setInt(1, entity.getPrice());
            preparedStatement.setString(2, entity.getIsbn());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getDescription());
            preparedStatement.setObject(5, entity.getStatus(), Types.OTHER);
            preparedStatement.setDate(6, Date.valueOf(entity.getPublicationDate()));
            preparedStatement.setDate(7, Date.valueOf(entity.getDeliveryDate()));
            preparedStatement.setInt(8, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }
}