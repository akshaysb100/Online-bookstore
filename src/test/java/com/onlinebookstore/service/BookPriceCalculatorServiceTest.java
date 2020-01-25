package com.onlinebookstore.service;

import com.onlinebookstore.exception.CountryNotFoundException;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookPriceCalculatorServiceTest {

    @Mock
    OnlineBookRepository bookRepository;

    @Mock
    Environment environment;

    @InjectMocks
    BookPriceCalculatorService calculatorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookIdAs1AndCountryAsIndia_WhenBooksPriceIs193_ShouldReturnTotalPriceAs243() {
        Book book = mock(Book.class);
        when(bookRepository.findBookById(1L)).thenReturn(book);
        when(book.getPrice()).thenReturn(193.0);
        Double price = calculatorService.calculatePriceOfBookAsPerCountry(1L, "india");
        Assert.assertEquals(243,price,0.0);
    }

/*
    @Test
    @Ignore
    public void givenBookIdAs1AndCountryAsIndia_WhenCountryNameIsWrong_ShouldThrowException() {
        try {
            Book book = mock(Book.class);
            when(bookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(book));
            when(environment.getProperty("status.bookStatusCode.CountryError")).thenReturn("Country not found!!!");
            Double price = calculatorService.calculatePriceOfBookAsPerCountry(1L, "ia");
        } catch (CountryNotFoundException e) {
            e.printStackTrace();
            Assert.assertEquals("Country not found!!!",e.getMessage());
        }

    }
*/
}
