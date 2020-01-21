package com.onlinebookstore.service;

import com.google.gson.Gson;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
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
    Gson gson;

    @Autowired
    private Environment environment;

    public String getDataAsList() {
        if (onlineBookRepository.count() == 0)
            throw new BookStoreException("NO Books Found");
        List<Book> bookList = onlineBookRepository.findAll();
        bookList.stream().forEach(book -> {
            String updatedImageUrl = book.getImage().substring(0, book.getImage().length() - 1);
            book.setImage(updatedImageUrl);
        });
        return gson.toJson(bookList);
    }

    public Book getBookDetails(Long bookId, String country) throws BookStoreException {
        Book book=onlineBookRepository.findBookById(bookId);
        if(book == null) {
            throw  new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"));
        }
        Double price = calculatorService.calculatePriceOfBookAsPerCountry(bookId, country);
        book.setPrice(price);
        return book;
    }

    public OrderDetailsDTO getOrderDetails(@Valid Customer customer, Long bookId) throws BookStoreException {
        String country = customer.getCountry();
        Double totalPrice = calculatorService.calculatePriceOfBookAsPerCountry(bookId, country);
        return new OrderDetailsDTO(customer,bookId,totalPrice);
    }

    public Book searchByAuthor(String searchElement){
        Optional<Book> book1 = onlineBookRepository.findByAuthor(searchElement);
        Optional<Book> book2 = onlineBookRepository.findByTitle(searchElement);
        if(book1.isPresent() || book2.isPresent())
            return book1.get();
        throw new BookStoreException(environment.getProperty("status.bookStatusCode.AuthorNotFound"));

    }


    public List<Book> sortByPrice(String sortType){
        if(sortType.equals("price"))
            return onlineBookRepository.findAll(Sort.by(Sort.Direction.ASC,"price"));
        else if(sortType.equals("title"))
            return onlineBookRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
        return null;

    }


}
