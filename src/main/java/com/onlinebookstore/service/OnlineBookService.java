package com.onlinebookstore.service;

import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class OnlineBookService {

    @Autowired
    BookPriceCalculatorService calculatorService;

    @Autowired
    OnlineBookRepository onlineBookRepository;

    public List<Book>getDataAsList() throws BookStoreException {
        if (onlineBookRepository.count() == 0) {
            throw new BookStoreException("NO Books Found", BookStoreException.ExceptionType.NO_BOOKS_FOUND);
        }
        return onlineBookRepository.findAll();
    }

    public String getOrderDetails(@Valid Customer customer, Long bookId) {
        String country = customer.getCountry();
        Double totalPrice = calculatorService.calculatePriceOfBookAsPerCountry(bookId, country);
        return new OrderDetailsDTO(customer,bookId,totalPrice).toString();
    }
}
