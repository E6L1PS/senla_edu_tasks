package service;

import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.AuthorRepository;
import com.senla.bookstore.repository.BookRepository;
import com.senla.bookstore.repository.RequestRepository;
import com.senla.bookstore.repository.WarehouseRepository;
import com.senla.bookstore.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WarehouseServiceTest {

    private static final Integer ID = 1;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private WarehouseService warehouseService;

    @Test
    void getWarehouse() {
        List<Warehouse> books = mock(ArrayList.class);
        when(warehouseRepository.findAll()).thenReturn(books);

        List<Warehouse> expectedBooks = warehouseService.getWarehouse();

        assertNotNull(expectedBooks);
        assertEquals(expectedBooks, books);
        verify(warehouseRepository).findAll();
    }

    @Test
    void getSortedWarehouse() {
        List<Warehouse> books = mock(ArrayList.class);
        when(warehouseRepository.findAllByType("id")).thenReturn(books);

        List<Warehouse> expectedBooks = warehouseService.getSortedWarehouse("id");

        assertNotNull(expectedBooks);
        assertEquals(expectedBooks, books);
        verify(warehouseRepository).findAllByType("id");
    }

    @Test
    void getStaleBooks() {
        List<Warehouse> books = mock(ArrayList.class);
        when(warehouseRepository.findStaleBooks("id", "1")).thenReturn(books);

        List<Warehouse> expectedBooks = warehouseService.getStaleBooks("id");

        assertNotNull(expectedBooks);
        assertEquals(expectedBooks, books);
        verify(warehouseRepository).findStaleBooks("id", "1");
    }

    @Test
    void addBook() {
    }

    @Test
    void removeBook() {
    }

    @Test
    void getNumberMonthForStale() {
    }

    @Test
    void setNumberMonthForStale() {
    }
}