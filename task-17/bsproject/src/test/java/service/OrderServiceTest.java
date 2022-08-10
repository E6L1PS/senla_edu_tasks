package service;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.repository.BookRepository;
import com.senla.bookstore.repository.CustomerRepository;
import com.senla.bookstore.repository.OrderRepository;
import com.senla.bookstore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final Integer ID = 1;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void addOrder() {
        Order order = mock(Order.class);;

        orderRepository.create(order);

        verify(orderRepository).create(order);
    }

    @Test
    void getSortedOrders() {
        List<Order> orders = mock(ArrayList.class);
        when(orderRepository.findAllByType("id")).thenReturn(orders);

        List<Order> expectedOrders = orderService.getSortedOrders("id");

        assertNotNull(expectedOrders);
        assertEquals(expectedOrders, orders);
        verify(orderRepository).findAllByType("id");
    }

    @Test
    void getCompletedOrders() {
        LocalDate now = LocalDate.now();
        List<Order> orders = mock(ArrayList.class);
        when(orderRepository.findCompletedByType(now, now, "id")).thenReturn(orders);

        List<Order> expectedOrders = orderService.getCompletedOrders(now, now, "id");

        assertNotNull(expectedOrders);
        assertEquals(expectedOrders, orders);
        verify(orderRepository).findCompletedByType(now, now, "id");
    }

    @Test
    void getQuantityCompletedOrders() {
        LocalDate now = LocalDate.now();
        when(orderRepository.getQuantityCompletedOrders(now, now)).thenReturn(anyLong(), eq(12L));

        Long quantity = orderService.getQuantityCompletedOrders(now, now);

        assertNotNull(quantity);
        verify(orderRepository).getQuantityCompletedOrders(now, now);
    }

    @Test
    void getFullPrice() {
        LocalDate now = LocalDate.now();
        when(orderRepository.getFullPrice(now, now)).thenReturn(anyInt(), eq(12));

        Integer price = orderService.getFullPrice(now, now);

        assertNotNull(price);
        verify(orderRepository).getFullPrice(now, now);
    }

    @Test
    void setStatus() {
        orderService.setStatus(ID, OrderStatus.COMPLETED);

        verify(orderRepository).setStatus(ID, OrderStatus.COMPLETED);
    }

    @Test
    void removeOrder() {
        orderService.removeOrder(ID);

        verify(orderRepository).setStatus(ID, OrderStatus.CANCELLED);
    }

}