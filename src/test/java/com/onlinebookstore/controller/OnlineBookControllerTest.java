package com.onlinebookstore.controller;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.response.Response;
import com.onlinebookstore.service.OnlineBookService;
import com.onlinebookstore.service.UpdateDbService;
import com.onlinebookstore.utility.ErrorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

public class OnlineBookControllerTest {

    @Mock
    OnlineBookService bookService;

    @Mock
    UpdateDbService dbUpdater;

    @Mock
    Environment environment;


    @InjectMocks
    OnlineBookController onlineBookController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
/*

    @Test
    public void givenCSVFile_WhenRecordsAddedToDatabase_ShouldReturnListOfRecords() {
        try {
            String books = onlineBookController.getBooks();

            Assert.assertEquals(,);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }
*/

    @Test
    public void givenCSVFile_WhenRecordsNotAddedToDatabase_ShouldReturnListOfRecords() {
        BookStoreException bookStoreException = mock(BookStoreException.class);
        try {
            when(bookService.getDataAsList()).thenThrow(bookStoreException);
            List<Book> books = onlineBookController.getBooks();
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals(bookStoreException, e);
        }
    }

    @Test
    public void givenCustomerPassesDetails_WhenABookIsSelected_ShouldReturnOrderSummary() {
        try {
            Book book = mock(Book.class);
            when(bookService.getBookDetails(1L, "india")).thenReturn(book);
            ResponseEntity<Book> output = onlineBookController.getBookDetails(1L, "india");
            Assert.assertEquals(200, output.getStatusCode().value());
        } catch (ErrorResponse errorResponse) {
            errorResponse.printStackTrace();
        }

    }

    @Test
    public void givenOrderIsPlaced_ShouldUpdateTheDatabase() {
        Customer customer = mock(Customer.class);
        OrderDetailsDTO dto = mock(OrderDetailsDTO.class);

        when(bookService.getOrderDetails(customer, 1L)).thenReturn(dto);
        Response output = onlineBookController.convertToOrderDetailsDetails(customer, 1L);
        verify(dbUpdater).updateDatabase(dto);
        Assert.assertEquals(200, output.getStatusCode());
    }

    @Test
    public void givenListOfBooks_WhenSearchByAuthor_ShouldReturnDesireBookList() {
        try {
            List<Book> bookList = mock(List.class);
            Book book = mock(Book.class);
            when(bookService.searchBookBy("Chetan Bhagat'")).thenReturn(bookList);
            ResponseEntity<List<Book>> books = onlineBookController.searchByAuthorOrTitle("Chetan Bhagat'");
            Assert.assertEquals(200, books.getStatusCode().value());
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenListOfBooks_WhenSortByPrice_ShouldReturnDesireBookList() {
        try {
            List<Book> bookList = mock(List.class);
            Book book = mock(Book.class);
            when(bookService.sortByPrice("price")).thenReturn(bookList);
            ResponseEntity<List<Book>> books = onlineBookController.sortByPrice("price");
            Assert.assertEquals(200, books.getStatusCode().value());
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenListOfBooks_WhenSearchByWrongAuthor_ShouldReturnCustomException() {
        List<Book>  list2 = mock(List.class);
        BookStoreException exception = new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"), HttpStatus.NOT_FOUND);
        try {
             when(bookService.searchBookBy("dhsakhdh")).thenThrow(exception);
            ResponseEntity<List<Book>> output = onlineBookController.searchByAuthorOrTitle("dhsakhdh");
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals(404,e.getStatus().value());
        }
    }



}

