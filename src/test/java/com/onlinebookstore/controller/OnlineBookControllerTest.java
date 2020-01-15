package com.onlinebookstore.controller;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.service.OnlineBookService;
import com.onlinebookstore.service.UpdateDbService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class OnlineBookControllerTest {

    @Mock
    OnlineBookService bookService;

    @Mock
    UpdateDbService dbUpdater;

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
    public void givenIGoOnCustomerDetailsPage_ShouldReturnBookId() {
        Long outputId = onlineBookController.showEmptyCustomerDetailsPAge(1L);
        Assert.assertEquals(1L,outputId,0.0);
    }

    @Test
    public void givenCustomerPassesDetails_WhenABookIsSelected_ShouldReturnOrderSummary() {
        Customer mockedCustomer = mock(Customer.class);
        String expectedString = "output string";
        when(bookService.getOrderDetails(mockedCustomer,1L)).thenReturn(expectedString);
        String actualString = onlineBookController.addCustomerDetails(mockedCustomer, 1L);
        Assert.assertEquals(expectedString,actualString);
    }

    @Test
    public void givenOrderIsPlaced_ShouldUpdateTheDatabase() {
        OrderDetailsDTO mockedDetails = mock(OrderDetailsDTO.class);
        String outputString = onlineBookController.doneShopping(mockedDetails);
        verify(dbUpdater).updateDatabase(mockedDetails);
        Assert.assertEquals("ORDER PLACED",outputString);
    }
}