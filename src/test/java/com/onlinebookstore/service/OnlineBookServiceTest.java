package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OnlineBookServiceTest {

    private final String filePath = "/home/user/projectSimulation/Online-bookstore/src/test/java/resources/Sample.csv";
    CustomerRepository customerRepository;
    OnlineBookRepository bookRepository;
    private OnlineBookService onlineBookService;



    @Before
    public void setUp() throws Exception {
        customerRepository = mock(CustomerRepository.class);
        bookRepository = mock(OnlineBookRepository.class);
        this.onlineBookService = new OnlineBookService();
    }

    @Test
    public void givenCustomer_WhenEntersDetailsToPlaceOrder_ShouldGetAddedToCustomerDetailsRepository() {
        onlineBookService.setMockObjects(customerRepository);
        Customer customer = new Customer(1L, "Manoj Bajpayee", "9552967330", "400116", "Sant Maharaj Temple,Aundh", "Pune");
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        onlineBookService.addDetailsOfCustomer(customer);
        Customer customerDetails = onlineBookService.getCustomerDetails(1L);
        Assert.assertEquals(customer, customerDetails);
    }



    @Test
    public void givenListOfBooks_WhenLoaded_ShouldGetAddedToTheDataBase() {
        OnlineBookService bookStoreService = new OnlineBookService();
        Book book = new Book("Chetan Bhagat", "dsafkjkdasnfckscdnf", "the secret", 123.0, "zlcvnlxsnvlsv");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookStoreService.setmockObjects(bookRepository);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> dataAsList = bookStoreService.getDataAsList();
        Assert.assertEquals(bookList, dataAsList);
    }
}
