package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OnlineBookServiceTest {

    OnlineBookRepository bookRepository;


    @Before
    public void setUp() throws Exception {
        bookRepository = mock(OnlineBookRepository.class);

    }

    private final String filePath = "/home/user/projectSimulation/Online-bookstore/src/test/java/resources/Sample.csv";

    @Test
    public void givenListOfBooks_WhenLoaded_ShouldGetAddedToTheDataBase() {
        OnlineBookService bookStoreService = new OnlineBookService();
        Book book = new Book("Chetan Bhagat","dsafkjkdasnfckscdnf","the secret",123.0,"zlcvnlxsnvlsv");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookStoreService.setmockObjects(bookRepository);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> dataAsList = bookStoreService.getDataAsList();
        Assert.assertEquals(bookList,dataAsList);
    }
}
