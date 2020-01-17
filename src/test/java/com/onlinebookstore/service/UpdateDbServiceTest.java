package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.repository.OnlineBookRepository;
import com.onlinebookstore.repository.OrderDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UpdateDbServiceTest {

    @Mock
    OrderDetailsRepository orderDetailsRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OnlineBookRepository onlineBookRepository;

    @InjectMocks
    UpdateDbService dbService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenOrderDetails_ShouldGetUpdatedInDatabase() {
        OrderDetailsDTO orderDetails = mock(OrderDetailsDTO.class);
        Book book = mock(Book.class);
        Customer customer = mock(Customer.class);
        when(orderDetailsRepository.save(orderDetails)).thenReturn(orderDetails);
        when(customerRepository.save(orderDetails.getCustomer())).thenReturn(customer);
        when(onlineBookRepository.findById(orderDetails.getBookId())).thenReturn(Optional.of(book));
        when(onlineBookRepository.save(book)).thenReturn(book);
        dbService.updateDatabase(orderDetails);
        verify(orderDetailsRepository).save(orderDetails);
        verify(customerRepository).save(orderDetails.getCustomer());
        verify(onlineBookRepository).save(book);
    }
}
