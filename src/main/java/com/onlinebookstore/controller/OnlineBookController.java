package com.onlinebookstore.controller;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.CartDetails;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.response.Response;
import com.onlinebookstore.service.OnlineBookService;
import com.onlinebookstore.service.UpdateDbService;
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

    @GetMapping("/showBooks")
    public List<Book> getBooks() {
        return onlineBookService.getDataAsList();
    }

    @GetMapping("/searchByAuthorOrTitle")
    public ResponseEntity<List<Book>> searchByAuthorOrTitle(@RequestParam String searchElement){
        List<Book> bookList = onlineBookService.searchBookBy(searchElement);
        return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
    }

    @PostMapping("/orderConfirmation/")
    public Response convertToOrderDetailsDetails(@RequestBody CartDetails cartDetails) {
        OrderDetailsDTO orderDetails = onlineBookService.getOrderDetails(cartDetails);
        dbUpdater.updateDatabase(orderDetails);
        return new Response(" Order updated successfully", HttpStatus.OK.value());
    }

}
