package com.onlinebookstore.service;

import com.google.gson.Gson;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.CartDetails;
import com.onlinebookstore.model.OrderDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

@PropertySource(value = {"classpath:message.properties"})
@Service
public class OnlineBookService {

    @Autowired
    OnlineBookRepository onlineBookRepository;

    @Autowired
    Gson gson;

    @Autowired
    private Environment environment;

    public List<Book> getDataAsList() {
        if (onlineBookRepository.count() == 0)
            throw new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"),HttpStatus.NOT_FOUND);
        return onlineBookRepository.findAll();

    }

    public List<Book> searchBookBy(String searchElement){
        List<Book> byAuthor = onlineBookRepository.findByAuthorContaining(searchElement);
        List<Book> byTitle = onlineBookRepository.findByTitleContaining(searchElement);
        List<Book> searchOutput = Stream.concat(byAuthor.stream(), byTitle.stream())
                .collect(toList());
        if (searchOutput.isEmpty())
            throw  new BookStoreException(environment.getProperty("status.bookStatusCode.invalidSearchInput"), HttpStatus.NOT_FOUND);
        return searchOutput;
    }

    public OrderDetailsDTO getOrderDetails(CartDetails cartDetails) {
        return new OrderDetailsDTO(cartDetails.getCustomer(), cartDetails.getListOfOrderedBooks(),cartDetails.getTotalPrice());
    }

    public List<Book> sortByPrice() {
        List<Book> book =onlineBookRepository.findAll();

        List<Book> bookList = book.stream().sorted((o1, o2)->o1.getPrice().
                compareTo(o2.getPrice())).collect(Collectors.toList());
        return  bookList;
    }
}
