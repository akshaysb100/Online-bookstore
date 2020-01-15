package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import java.util.List;

@Service
public class OnlineBookService {

    @Autowired
    OnlineBookRepository onlineBookRepository;

    @Autowired
    CustomerRepository repository;

    public List<Book> getDataAsList() throws BookStoreException {
        if (onlineBookRepository.count() == 0) {
            throw new BookStoreException("NO Books Found", BookStoreException.ExceptionType.NO_BOOKS_FOUND);
        }
        return onlineBookRepository.findAll();
    }

    public void addDetailsOfCustomer(Customer customer) {
        repository.save(customer);
    }

    public Customer getCustomerDetails(Long customerId) {
        Optional<Customer> customerDetails = repository.findById(customerId);
        return customerDetails.get();
    }
}
