package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.util.ConnectorDB;
import com.senla.configure.annotations.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BookRepository extends AbstractRepository<Integer, Book> implements IBookRepository<Integer, Book> {
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM books";
    public static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id=?";
    public static final String SQL_INSERT_BOOK = "INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id=?";
    public static final String SQL_UPDATE_BOOK = "UPDATE books SET " +
            "price=?," +
            "isbn=?," +
            "name=?," +
            "description=?," +
            "status=?," +
            "publication_date=?," +
            "delivery_date=? WHERE id=?";
    public static final String SQL_SELECT_CHECK_PRICE_BY_ID = "SELECT price FROM books WHERE id=?";

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
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_INSERT_BOOK)) {
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setString(3, entity.getIsbn());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getDescription());
            preparedStatement.setString(6, entity.getStatus().name());
            preparedStatement.setDate(8, Date.valueOf(entity.getPublicationDate()));
            preparedStatement.setDate(9, Date.valueOf(entity.getDeliveryDate()));
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

    @Override
    public Integer checkPrice(List<Integer> bookIds) {
        int sum = 0;
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_SELECT_CHECK_PRICE_BY_ID)) {
            for (Integer bookId : bookIds) {
                preparedStatement.setInt(1, bookId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                sum += rs.getInt("price");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
/*
        List<Book> books = findAll();
        int sum = 0;

        for (Integer bookId : bookIds) {
            sum += books.get(bookId).getPrice();
        }
*/
        return sum;
    }

}
/*
    private static final String URL_KEY = "bookstore.url";
    private static final String USERNAME_KEY = "bookstore.username";
    private static final String PASSWORD_KEY = "bookstore.password";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static int countBooksId;
    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM books";
            ResultSet resultSetBooks = statement.executeQuery(SQL);

            while (resultSetBooks.next()) {
                Book book = new Book();

                book.setId(resultSetBooks.getInt("id"));
                book.setPrice(resultSetBooks.getInt("price"));
                book.setName(resultSetBooks.getString("name"));
                book.setDescription(resultSetBooks.getString("description"));
                // book.setStatus(resultSetBooks.get);
                book.setPublicationDate(resultSetBooks.getDate("publication_date").toLocalDate());
                book.setDeliveryDate(resultSetBooks.getDate("publication_date").toLocalDate());

                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //books = JsonReader.readRepository("Library.json", Book.class);
       // countBooksId = books.size();
    }

    public void save(Book book) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO books VALUES(1, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, book.getPrice());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDate(4, Date.valueOf(book.getPublicationDate()));
            preparedStatement.setDate(5, Date.valueOf(book.getDeliveryDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getCountBooksId() {
        return countBooksId;
    }

    @Override
    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        book.setId(countBooksId++);
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        int id = book.getId();
        deleteBookById(id);
        books.add(id, book);
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public void deleteBookById(int id) {
        books.remove(getBookById(id));
    }

    @Override
    public int checkPrice(List<Integer> bookIds) {
        int sum = 0;

        for (Integer bookId : bookIds) {
            sum += books.get(bookId).getPrice();
        }

        return sum;
    }
}*/
