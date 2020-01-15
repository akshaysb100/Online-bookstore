package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OnlineBookServiceTest {

    @Mock
    OnlineBookRepository bookRepository;

    @InjectMocks
    OnlineBookService bookService = new OnlineBookService();

    private final String filePath = "/home/user/projectSimulation/Online-bookstore/src/test/java/resources/Sample.csv";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenListOfBooks_WhenLoaded_ShouldGetAddedToTheDataBase() {
        try {
            OnlineBookService bookStoreService = new OnlineBookService();
            Book book = new Book("Chetan Bhagat","dsafkjkdasnfckscdnf","the secret",123.0,"zlcvnlxsnvlsv");
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            bookStoreService.setmockObjects(bookRepository);
            when(bookRepository.findAll()).thenReturn(bookList);
            List<Book> dataAsList = null;
            dataAsList = bookStoreService.getDataAsList();
            Assert.assertEquals(bookList,dataAsList);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenListOfBooks_WhenListEmpty_ShouldThrowBookStoreException() {

        try {
            BookStoreException mockedException = mock(BookStoreException.class);
            long expectedSize = 0;
            when(bookRepository.count()).thenReturn(expectedSize);
            List<Book> dataAsList = bookService.getDataAsList();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.NO_BOOKS_FOUND,e.type);
        }


    }
}
