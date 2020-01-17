package com.onlinebookstore.service;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.response.Response;
import com.onlinebookstore.utility.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@PropertySource(value = {"classpath:message.properties"})
@Service
public class OnlineBookService {

    @Autowired
    BookPriceCalculatorService calculatorService;

    @Autowired
    OnlineBookRepository onlineBookRepository;

    @Autowired
    private Environment environment;

    public List<Book> getDataAsList() {
        List<Book> books = onlineBookRepository.findAll();
        if(books.isEmpty()){

            throw  new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"));
        }
        new Response(environment.getProperty("status.bookStatusCode.getSuccess")).toString();
        return books;
    }

    public Book getBookDetails(Long bookId, String country) throws BookStoreException {
        Optional<Book> optionalBook = onlineBookRepository.findById(bookId);
        if(!optionalBook.isEmpty()) {
            throw  new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"));
        }
        Book myBook = optionalBook.get();
        Double price = calculatorService.calculatePriceOfBookAsPerCountry(bookId, country);
        myBook.setPrice(price);
        return myBook;
    }

    public OrderDetailsDTO getOrderDetails(@Valid Customer customer, Long bookId) throws BookStoreException {
        String country = customer.getCountry();
        Double totalPrice = calculatorService.calculatePriceOfBookAsPerCountry(bookId, country);
        return new OrderDetailsDTO(customer,bookId,totalPrice);
    }
}
