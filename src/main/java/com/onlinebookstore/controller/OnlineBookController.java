package com.onlinebookstore.controller;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.exception.BookStoreException;
import com.onlinebookstore.service.CustomerService;
import com.onlinebookstore.service.OnlineBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class OnlineBookController {

    @Autowired
    OnlineBookService onlineBookService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public void addCustomerDetails(@Valid @RequestBody Customer customer){
        customerService.addDetailsOfCustomer(customer);
    }

    @GetMapping("/showBooks")
    public String getBooks(){
        try {
            return onlineBookService.getDataAsList().toString();
        } catch (BookStoreException e) {
            e.printStackTrace();
            return null;
        }
    }
}
