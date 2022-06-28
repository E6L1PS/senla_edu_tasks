package com.senla.bookstore.repository;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.repository.interfaces.IAuthorRepository;
import com.senla.bookstore.util.ConnectorDB;
import com.senla.configure.annotations.Singleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AuthorRepository extends AbstractRepository<Integer, Author> implements IAuthorRepository<Integer, Author> {

    public static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM authors";
    public static final String SQL_SELECT_AUTHOR_ID = "SELECT * FROM authors WHERE id=?";

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Statement statement = ConnectorDB.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_AUTHORS);

            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("rating")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authors;
    }

    @Override
    public Author findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Author entity) {
        return false;
    }

    @Override
    public boolean create(Author entity) {
        return false;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }
}
