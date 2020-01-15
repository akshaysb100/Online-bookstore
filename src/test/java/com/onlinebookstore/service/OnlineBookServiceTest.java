package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OnlineBookServiceTest {

    private final String filePath = "/home/user/projectSimulation/Online-bookstore/src/test/java/resources/Sample.csv";
    @Mock
    OnlineBookRepository bookRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    OnlineBookService bookService = new OnlineBookService();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenListOfBooks_WhenListEmpty_ShouldThrowBookStoreException() {
        try {
            long expectedSize = 0;
            when(bookRepository.count()).thenReturn(expectedSize);
            List<Book> dataAsList = bookService.getDataAsList();
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals(BookStoreException.ExceptionType.NO_BOOKS_FOUND,e.type);
        }
    }

    @Test
    public void givenCustomer_WhenEntersDetailsToPlaceOrder_ShouldGetAddedToCustomerDetailsRepository() {
        Customer customer = mock(Customer.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        bookService.addDetailsOfCustomer(customer);
        Customer customerDetails = bookService.getCustomerDetails(1L);
        Assert.assertEquals(customer, customerDetails);
    }

    @Test
    public void givenARequestToShowAllBooks_WhenDatabaseHasAMultipleBooks_ShouldReturnListOfBooksFromDatabase() {
        try {
            List<Book> bookList = mock(List.class);
            when(bookRepository.count()).thenReturn(52L);
            when(bookRepository.findAll()).thenReturn(bookList);
            List<Book> dataAsList = bookService.getDataAsList();
            Assert.assertEquals(bookList, dataAsList);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }
}
