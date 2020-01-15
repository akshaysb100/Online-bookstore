package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.repository.OnlineBookRepository;
import com.onlinebookstore.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDbService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OnlineBookRepository onlineBookRepository;

    public UpdateDbService() {
    }

    public void updateDatabase(OrderDetailsDTO orderDetails) {
        this.updateCustomerDetails(orderDetails.getCustomer());
        this.updateBookDetails(orderDetails.getBookId());
        this.updateOrderDetails(orderDetails);
    }

    private void updateOrderDetails(OrderDetailsDTO orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    private void updateBookDetails(Long bookId) {
        Optional<Book> optionalBook = onlineBookRepository.findById(bookId);
        Book book = optionalBook.get();
        long updatedCopiesCount = book.getNumberOfCopies() - 1;
        book.setNumberOfCopies(updatedCopiesCount);
        onlineBookRepository.save(book);
    }

    private void updateCustomerDetails(Customer customer) {
        customerRepository.save(customer);
    }
}
