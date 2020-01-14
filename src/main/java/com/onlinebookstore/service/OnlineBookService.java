package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OnlineBookService {

    @Autowired
    CustomerRepository repository ;

    public void  addDetailsOfCustomer(Customer customer) {
        repository.save(customer);
    }

    public Customer getCustomerDetails(Long customerId) {
        Optional<Customer> customerDetails = repository.findById(customerId);
        return customerDetails.get();
    }

    public void setMockObjects(CustomerRepository customerRepository) {
        repository = customerRepository;
    }
}
