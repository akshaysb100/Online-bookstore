package com.onlinebookstore.controller;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.response.Response;
import com.onlinebookstore.service.BookPriceCalculatorService;
import com.onlinebookstore.service.OnlineBookService;
import com.onlinebookstore.service.UpdateDbService;
import com.onlinebookstore.utility.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/books")
public class OnlineBookController {

    @Autowired
    UpdateDbService dbUpdater;

    @Autowired
    OnlineBookService onlineBookService;

    @Autowired
    BookPriceCalculatorService priceCalculatorService;


    /*HOME PAGE ->+
        display all books
        each book has buy button
        clicking on buy button sends book id to the next page
     */
    @GetMapping("/showBooks")
    public List<Book> getBooks() {
        return onlineBookService.getDataAsList();
    }

    /*SUMMARY PAGE ->
        displays books details, customer details and total price (including shipping)
        goes to confirmation page
     */
    //ORDER SUMMARY PAGE
    @GetMapping("/getBookDetail/{bookId}")
    public ResponseEntity<Book> getBookDetails(@PathVariable Long bookId, @RequestParam String country) throws BookStoreException, ErrorResponse {
        Book bookDetails = onlineBookService.getBookDetails(bookId, country);
        return new ResponseEntity<Book>(bookDetails, HttpStatus.OK);
    }

    /*FINAL PAGE ->
        displays succesful message
        updates database/inventory
     */
    //ORDER COMPLETE PAGE
    @PostMapping("/orderConfirmation/{bookId}")
    public Response convertToOrderDetailsDetails(@RequestBody Customer customer, @PathVariable Long bookId) {
        OrderDetailsDTO bookDetails = onlineBookService.getOrderDetails(customer, bookId);
        dbUpdater.updateDatabase(bookDetails);
        return new Response(" Order updated successfully", HttpStatus.OK.value());
    }

    @GetMapping("/searchByAuthorOrTitle")
    public ResponseEntity<List<Book>> searchByAuthorOrTitle(@RequestParam String searchElement){
        List<Book> bookList = onlineBookService.searchByAuthor(searchElement);
        return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
    }

    @GetMapping("/sortByPrice")
    public ResponseEntity<List<Book>> sortByPrice(@RequestParam String sortType){
        List<Book> bookList = onlineBookService.sortByPrice(sortType);
        return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
    }
}
