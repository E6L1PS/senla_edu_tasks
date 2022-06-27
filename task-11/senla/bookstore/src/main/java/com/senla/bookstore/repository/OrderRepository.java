package com.senla.bookstore.repository;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.util.ConnectorDB;
import com.senla.configure.annotations.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class OrderRepository extends AbstractRepository<Integer, Order> implements IOrderRepository<Integer, Order> {
    public static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    public static final String SQL_SELECT_ORDER_ID = "SELECT * FROM orders WHERE id=?";
    public static final String SQL_INSERT_ORDER = "INSERT INTO orders (price, status, book_ids, issue_date) VALUES(?, ?, ?, ?)";
    public static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id=?";
    public static final String SQL_UPDATE_ORDER = "UPDATE orders SET " +
            "price=?," +
            "status=?," +
            "books_ids=?," +
            "issue_date=? WHERE id=?";

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = ConnectorDB.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        Arrays.asList((Integer[]) rs.getArray("book_ids").getArray()),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getDate("issue_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findEntityById(Integer id) {
        Order order = null;
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_SELECT_ORDER_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            order = new Order(
                    rs.getInt("id"),
                    rs.getInt("price"),
                    (ArrayList<Integer>) rs.getArray("book_ids").getArray(),
                    OrderStatus.valueOf(rs.getString("status")),
                    rs.getDate("issue_date").toLocalDate()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_DELETE_ORDER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_DELETE_ORDER)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Order entity) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_INSERT_ORDER)) {
            preparedStatement.setInt(1, entity.getPrice());
            preparedStatement.setObject(2, entity.getStatus(), Types.OTHER);
            preparedStatement.setArray(3, ConnectorDB.getConnection().createArrayOf("int", entity.getBookIds().toArray()));
            preparedStatement.setDate(4, Date.valueOf(entity.getIssueDate()));
            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Order update(Order entity) {
        try (PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL_UPDATE_ORDER)) {
            preparedStatement.setInt(1, entity.getPrice());
            preparedStatement.setString(2, entity.getStatus().name());
            preparedStatement.setArray(3, ConnectorDB.getConnection().createArrayOf("int", entity.getBookIds().toArray()));
            preparedStatement.setDate(4, Date.valueOf(entity.getIssueDate()));
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }
}


/*
    private static int countOrdersId;
    private List<Order> orders;

    public OrderRepository() {
        orders = JsonReader.readRepository("Orders.json", Order.class);
        countOrdersId = orders.size();
    }

    @Override
    public int getCountOrdersId() {
        return countOrdersId;
    }


    @Override
    public Order getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        order.setId(countOrdersId++);
        orders.add(order);
    }

    @Override
    public void updateOrder(Order order) {
        int id = order.getId();
        // orders.remove(id);
        deleteOrderById(id);
        orders.add(id, order);
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orders.remove(getOrderById(id));
    }
}
*/