package com.onlinebookstore.service;

import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OnlineBookServiceTest {

    CustomerRepository customerRepository;
    private OnlineBookService onlineBookService;

    @Before
    public void setUp() throws Exception {
        customerRepository = mock(CustomerRepository.class);
        this.onlineBookService = new OnlineBookService();
    }

    @Test
    public void givenCustomer_WhenEntersDetailsToPlaceOrder_ShouldGetAddedToCustomerDetailsRepository() {
        onlineBookService.setMockObjects(customerRepository);
        Customer customer = new Customer(1L, "Manoj Bajpayee", "9552967330", "400116", "Sant Maharaj Temple,Aundh", "Pune");
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        onlineBookService.addDetailsOfCustomer(customer);
        Customer customerDetails = onlineBookService.getCustomerDetails(1L);
        Assert.assertEquals(customer,customerDetails);
    }
}