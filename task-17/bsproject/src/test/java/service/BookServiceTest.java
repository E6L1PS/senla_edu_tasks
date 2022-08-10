package service;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.AuthorRepository;
import com.senla.bookstore.repository.BookRepository;
import com.senla.bookstore.repository.RequestRepository;
import com.senla.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private static final Integer ID = 1;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void getBooks() {
        List<Book> books = mock(ArrayList.class);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> expectedBooks = bookService.getBooks();

        assertNotNull(expectedBooks);
        assertEquals(expectedBooks, books);
        verify(bookRepository).findAll();
    }

    @Test
    void getSortedBooks() {
        List<Book> books = mock(ArrayList.class);
        when(bookRepository.findAllByType("id")).thenReturn(books);

        List<Book> expectedBooks = bookService.getSortedBooks("id");

        assertNotNull(expectedBooks);
        assertEquals(expectedBooks, books);
        verify(bookRepository).findAllByType("id");
    }

    @Test
    void getDescription() {
        when(bookRepository.findDescriptionById(ID)).thenReturn(anyString());

        String expectedDescription = bookService.getDescription(ID);

        assertNotNull(expectedDescription);
        verify(bookRepository).findDescriptionById(ID);
    }

    @Test
    void getRequestBooks() {
        List<Request> requests = mock(ArrayList.class);
        when(requestRepository.findAllByType("id")).thenReturn(requests);

        List<Request> expectedRequests = bookService.getRequestBooks("id");

        assertNotNull(expectedRequests);
        assertEquals(expectedRequests, requests);
        verify(requestRepository).findAllByType("id");
    }

    @Test
    void getAuthors() {
        List<Author> authors = mock(ArrayList.class);
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> expectedAuthors = bookService.getAuthors();

        assertNotNull(expectedAuthors);
        assertEquals(expectedAuthors, authors);
        verify(authorRepository).findAll();
    }

    @Test
    void addRequest() {
        Book book = mock(Book.class);
        book.setStatus(BookStatus.OUT_STOCK);
        when(bookRepository.findEntityById(ID)).thenReturn(book);

        assertThrowsExactly(SQLClientInfoException.class, () -> {
            bookService.addRequest(ID);
        });

        verify(bookRepository).findEntityById(ID);
    }

}