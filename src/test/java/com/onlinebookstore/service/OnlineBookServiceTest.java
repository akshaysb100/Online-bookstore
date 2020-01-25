package com.onlinebookstore.service;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.CartDetails;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.springframework.core.env.Environment;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class OnlineBookServiceTest {

    @Mock
    OnlineBookRepository bookRepository;

    @Mock
    Environment environment;

    @InjectMocks
    OnlineBookService bookService = new OnlineBookService();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenListOfBooks_WhenListEmpty_ShouldThrowBookStoreException() {
        try {
            List<Book> list = mock(List.class);
            when(bookRepository.findAll()).thenReturn(list);
            when(list.isEmpty()).thenReturn(true);
            when(environment.getProperty("status.bookStatusCode.bookNotFound")).thenReturn("Unable to get books from database!!!");
            List<Book> dataAsList = bookService.getDataAsList();
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals(404, e.getStatus().value());
            Assert.assertEquals("Unable to get books from database!!!", e.getMessage());
        }
    }

    @Test
    public void givenARequestToShowAllBooks_WhenDatabaseHasAMultipleBooks_ShouldReturnListOfBooksFromDatabase() {
        try {
            List<Book> bookList = mock(List.class);
            when(bookRepository.count()).thenReturn(1L);
            when(bookRepository.findAll()).thenReturn(bookList);
            List<Book> data = bookService.getDataAsList();
            Assert.assertEquals(bookList, data);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBookIdAndCustomer_WhenPlacedOrder_ShouldReturnWholeOrderDetails() {
        CartDetails cartDetails = mock(CartDetails.class);
        Customer customer = mock(Customer.class);
        List<Long> bookIds = mock(List.class);
        when(cartDetails.getCustomer()).thenReturn(customer);
        when(cartDetails.getListOfOrderedBooks()).thenReturn(bookIds);
        when(cartDetails.getTotalPrice()).thenReturn(700.0);
        OrderDetailsDTO orderDetails = bookService.getOrderDetails(cartDetails);
        Assert.assertEquals(700.0, orderDetails.getTotalPrice(), 0.0);
    }

    @Test
    public void givenListOfBooks_WhenSearchByTitleOrAuthor_ShouldReturnListOfBooksContainingSearchStringInTitleNameOrAuthorName() {
        List<Book> bookList = new ArrayList<>();
        Book book = mock(Book.class);
        bookList.add(book);
        when(bookRepository.findByTitleContaining("The Girl in Room 105")).thenReturn(bookList);
        when(bookRepository.findByAuthorContaining("The Girl in Room 105")).thenReturn(bookList);
        List<Book> list = bookService.searchBookBy("The Girl in Room 105");
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void givenListOfBooks_WhenSearchByUnWrongAuthor_ShouldThrowCustomException() {
        try {
            List<Book> bookList = new ArrayList<>();
            when(bookRepository.findByAuthorContaining("wrong name")).thenReturn(bookList);
            when(bookRepository.findByTitleContaining("wrong name")).thenReturn(bookList);
            when(environment.getProperty("status.bookStatusCode.invalidSearchInput")).thenReturn("No books or author found with the given search elements");
            List<Book> books = bookService.searchBookBy("wrong name");
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals("No books or author found with the given search elements", e.getMessage());
        }
    }

}

