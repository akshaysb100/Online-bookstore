package com.onlinebookstore.service;

import com.google.gson.Gson;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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

    public List<Book> getDataAsList() {
        if (onlineBookRepository.count() == 0)
            throw new BookStoreException(environment.getProperty("status.bookStatusCode.bookNotFound"),HttpStatus.NOT_FOUND);
        return onlineBookRepository.findAll();

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

    public List<Book> searchByAuthor(String searchElement){//book title : gone girl
        List<Book> byAuthor = onlineBookRepository.findByAuthorContaining(searchElement);// null
        List<Book> byTitle = onlineBookRepository.findByTitleContaining(searchElement);//gone girl book
        /*if(!byAuthor.isEmpty())
            return byAuthor;
        else  if ( !byTitle.isEmpty())
            return byTitle;*/
//        if(byAuthor.isEmpty() || byTitle.isEmpty())
//            return onlineBookRepository.findAll();
//        throw new BookStoreException(environment.getProperty("status.bookStatusCode.AuthorNotFound"));
        List<Book> searchOutput = Stream.concat(byAuthor.stream(), byTitle.stream())
                .collect(toList());
        if (searchOutput.isEmpty())
            throw new BookStoreException("status.bookStatusCode.invalidSearchInput", HttpStatus.NOT_FOUND);
        return searchOutput;
    }


    public List<Book> sortByPrice(String sortType){
        if(sortType.equals("price"))
            return onlineBookRepository.findAll(Sort.by(Sort.Direction.ASC,"price"));
        else if(sortType.equals("title"))
            return onlineBookRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
        throw  new BookStoreException(environment.getProperty("status.bookStatusCode.SortTypeNotFound"));
    }
}
