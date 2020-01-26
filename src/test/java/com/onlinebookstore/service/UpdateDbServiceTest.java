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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UpdateDbServiceTest {

    @Mock
    OrderDetailsRepository orderDetailsRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    MailSenderService mailSenderService;

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
        Customer customer = mock(Customer.class);
        Book book = mock(Book.class);
        List<Long> bookIds = new ArrayList<>();
        bookIds.add(1L);
        OrderDetailsDTO orderDetails = new OrderDetailsDTO(customer,bookIds,700.00);
        when(orderDetailsRepository.save(orderDetails)).thenReturn(orderDetails);
        when(customerRepository.save(orderDetails.getCustomer())).thenReturn(customer);
        when(onlineBookRepository.findBookById(1L)).thenReturn(book);
        when(book.getNumberOfCopies()).thenReturn(10L);
        dbService.updateDatabase(orderDetails);
        verify(book).setNumberOfCopies(9L);
        verify(orderDetailsRepository).save(orderDetails);
        verify(customerRepository).save(orderDetails.getCustomer());
        verify(onlineBookRepository).save(book);
        verify(mailSenderService).sendMail(orderDetails);
    }
}
