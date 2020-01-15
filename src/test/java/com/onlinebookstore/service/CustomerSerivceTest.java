package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerSerivceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService = new CustomerService();

    @Test
    public void givenCustomer_WhenEntersDetailsToPlaceOrder_ShouldGetAddedToCustomerDetailsRepository() {
        Customer customer = mock(Customer.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        customerService.addDetailsOfCustomer(customer);
        Customer customerDetails = customerService.getCustomerDetails(1L);
        Assert.assertEquals(customer, customerDetails);
    }

}
