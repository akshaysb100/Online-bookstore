package com.onlinebookstore.service;

import com.google.gson.Gson;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.utility.ErrorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.*;


public class OnlineBookServiceTest {

    @Mock
    Gson gson;

    @Mock
    OnlineBookRepository bookRepository;

    @Mock
    BookPriceCalculatorService calculatorService;

    @Mock
    Environment environment;

    @InjectMocks
    OnlineBookService bookService = new OnlineBookService();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Ignore
    public void givenListOfBooks_WhenListEmpty_ShouldThrowBookStoreException() {
        try {
            List<Book> list = mock(List.class);
            when(bookRepository.findAll()).thenReturn(list);
            when(list.isEmpty()).thenReturn(true);
            when(environment.getProperty("status.bookStatusCode.bookNotFound")).thenReturn("Unable to get books from database!!!");
//            List<Book> dataAsList = bookService.getDataAsList();
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals("Unable to get books from database!!!",e.getMessage());
        }
    }

    @Test
    @Ignore
    public void givenARequestToShowAllBooks_WhenDatabaseHasAMultipleBooks_ShouldReturnListOfBooksFromDatabase() {
        try {
            List<Book> bookList = mock(List.class);
            when(bookRepository.count()).thenReturn(52L);
            when(bookRepository.findAll()).thenReturn(bookList);
            when(gson.toJson(bookList)).thenReturn("{hello}");
            String dataAsString = bookService.getDataAsList();
            Assert.assertEquals("{hello}",dataAsString);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void givenBookIdAs1AndCountryAsIndia_WhenBooksPriceIs193_ShouldReturnBookWithShippingCharges() {
        try {
            Book book = mock(Book.class);
            when(bookRepository.findBookById(1L)).thenReturn(book);
            when(book.getPrice()).thenReturn(243.0);
            when(calculatorService.calculatePriceOfBookAsPerCountry(1L, "india")).thenReturn(243.0);
            Book outputBook = bookService.getBookDetails(1L, "india");
            verify(book).setPrice(243.0);
        } catch (BookStoreException errorResponse) {
            errorResponse.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void givenBookIdAs76AndCountryAsIndia_WhenBooksNotFound_ShouldReturnThrowException() {
        try {
            Book book = mock(Book.class);
            when(bookRepository.findBookById(1L)).thenReturn(null);
            when(environment.getProperty("status.bookStatusCode.bookNotFound")).thenReturn("Unable to get books from database!!!");
            when(calculatorService.calculatePriceOfBookAsPerCountry(1L, "india")).thenReturn(243.0);
            Book outputBook = bookService.getBookDetails(1L, "india");
            verify(book).setPrice(243.0);
        } catch (BookStoreException e) {
            e.printStackTrace();
            Assert.assertEquals("Unable to get books from database!!!",e.getMessage());
        }
    }

    @Test
    @Ignore
    public void givenBookIdAndCustomer_WhenPlacedOrder_ShouldReturnWholeOrderDetails() {
        Customer customer = mock(Customer.class);
        when(customer.getCountry()).thenReturn("india");
        when(calculatorService.calculatePriceOfBookAsPerCountry(1L,"india")).thenReturn(243.0);
        OrderDetailsDTO orderDetails = bookService.getOrderDetails(customer, 1L);
        Assert.assertEquals(243.0,orderDetails.getTotalPrice(),0.0);
    }

    @Test
    @Ignore
    public void givenListOfBooks_WhenSearchByTitle_ShouldReturnDesiredBook() {
        List<Book> bookList = mock(List.class);
        Book correctBook = mock(Book.class);
        when(bookRepository.findByTitle("The Girl in Room 105'")).thenReturn(bookList);
        List<Book> list = bookService.searchByAuthor("The Girl in Room 105'");
        Assert.assertEquals(bookList,list);
    }

    @Test
    @Ignore
    public void givenListOfBooks_WhenSearchByAuthor_ShouldReturnDesiredBook() {
        List<Book> bookList = mock(List.class);
        Book correctBook = mock(Book.class);
        when(bookRepository.findByAuthor("Chetan Bhagat'")).thenReturn(bookList);
        List<Book> list = bookService.searchByAuthor("Chetan Bhagat'");
        Assert.assertEquals(bookList,list);
    }

    @Test
    @Ignore
    public void givenListOfBooks_WhenSearchByUnWorngAuthor_ShouldThrowCustomException() {
        try {
            List<Book> bookList = mock(List.class);
            Book correctBook = mock(Book.class);
            when(bookRepository.findByAuthor("Kumud Garg'")).thenReturn(bookList);
            when(bookList.isEmpty()).thenReturn(true);
            when(environment.getProperty("status.bookStatusCode.AuthorOrTitleNotFound")).thenReturn("Such Type Author Or Title Book Not Found!!!");
            List<Book> books = bookService.searchByAuthor("Kumud Garg'");
        }
        catch (BookStoreException e) {
            Assert.assertEquals("Such Type Author Or Title Book Not Found!!!", e.getMessage());
        }
    }

    @Test
    @Ignore
    public void givenListOfBooks_WhenSortByPrice_ShouldReturnDesiredBook() {
        List<Book> bookList = mock(List.class);
        Book correctBook = mock(Book.class);
        when(bookRepository.findAll(Sort.by(Sort.Direction.ASC,"price"))).thenReturn(bookList);
        List<Book> list = bookService.sortByPrice( "price");
        Assert.assertEquals(bookList,list);
    }

    @Test
    @Ignore
    public void givenListOfBooks_WhenSortByTitle_ShouldReturnDesiredBook() {
        List<Book> bookList = mock(List.class);
        Book correctBook = mock(Book.class);
        when(bookRepository.findAll(Sort.by(Sort.Direction.ASC,"title"))).thenReturn(bookList);
        List<Book> list = bookService.sortByPrice( "title");
        Assert.assertEquals(bookList,list);
    }
}
