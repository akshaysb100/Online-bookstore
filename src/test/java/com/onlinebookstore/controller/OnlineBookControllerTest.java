package com.onlinebookstore.controller;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.service.CustomerService;
import com.onlinebookstore.service.OnlineBookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.configuration.IMockitoConfiguration;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OnlineBookControllerTest {

    @Mock
    CustomerService customerService;

    @Mock
    OnlineBookService bookService;

    @InjectMocks
    OnlineBookController onlineBookController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenCSVFile_WhenRecordsAddedToDatabase_ShouldReturnListOfRecords() {
        try {
            List<Book> bookList = mock(List.class);
            when(bookService.getDataAsList()).thenReturn(bookList);
            String books = onlineBookController.getBooks();
            Assert.assertEquals(bookList.toString(),books);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCustomer_WhenAdded_ShouldReturn() {
        Customer myCustomer = mock(Customer.class);
        onlineBookController.addCustomerDetails(myCustomer);
        verify(customerService).addDetailsOfCustomer(myCustomer);
    }
}