package com.onlinebookstore.controller;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.service.BookStoreException;
import com.onlinebookstore.service.OnlineBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class OnlineBookController {

    @Autowired
    OnlineBookService onlineBookService;

    @PostMapping("/addCustomer")
    public void addCustomerDetails(@RequestBody Customer customer){
        onlineBookService.addDetailsOfCustomer(customer);
    }

    @GetMapping("/showBooks")
    public List<Book> getBooks(){
        try {
            return onlineBookService.getDataAsList();
        } catch (BookStoreException e) {
            e.printStackTrace();
            return null;
        }
    }
}
