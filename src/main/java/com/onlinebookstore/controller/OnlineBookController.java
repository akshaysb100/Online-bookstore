package com.onlinebookstore.controller;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.service.BookPriceCalculatorService;
import com.onlinebookstore.service.OnlineBookService;
import com.onlinebookstore.service.UpdateDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @GetMapping("/firstPage")
    public String getBooks(){
        try {
            return onlineBookService.getDataAsList().toString();
        } catch (BookStoreException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*CUSTOMER DETAILS ENTRY PAGE ->
        empty fields are displayed
        customer enters data
        book id and customer details are passed to the next page

     */
    //ENTER CUSTOMER DETAILS PAGE
    @PostMapping("/secondPage")
    public Long showEmptyCustomerDetailsPAge(@RequestParam Long bookId) {
        return bookId;
    }

    /*SUMMARY PAGE ->
        displays books details, customer details and total price (including shipping)
        goes to confirmation page
     */
    //ORDER SUMMARY PAGE
    @PostMapping("/thirdPage/{bookId}")
    public String addCustomerDetails(@Valid @RequestBody Customer customer, @PathVariable Long bookId){
        return onlineBookService.getOrderDetails(customer,bookId);
    }

    /*FINAL PAGE ->
        displays succesful message
        updates database/inventory
     */
    //ORDER COMPLETE PAGE
    @PostMapping("/fourthPage")
    public String doneShopping(@RequestBody OrderDetailsDTO orderDetails) {
        System.out.println(orderDetails.toString());
        dbUpdater.updateDatabase(orderDetails);
        return "ORDER PLACED";
    }
}
