package com.onlinebookstore.controller;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.service.OnlineBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class OnlineBookController {

    @Autowired
    OnlineBookService onlineBookService;

    @PostMapping("/addCustomer")
    public void addCustomerDetails(@RequestBody Customer customer){
        onlineBookService.addDetailsOfCustomer(customer);
    }

}
