package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.OrderDetailsDTO;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailSenderService {

    @Autowired
    private OnlineBookRepository bookRepository;

    @Autowired
    private JavaMailSender JavaMailSender;

    public void sendMail(OrderDetailsDTO orderDetails) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(orderDetails.getCustomer().getEmailId());
        msg.setFrom("kumud50garg@gmail.com");
        msg.setSubject("Order Confirmation regarding BookStore");
        msg.setText("Dear " +orderDetails.getCustomer().getCustomerName()+"\n"+"Congratulation!! your book has been purchased Successfully\n" + "Total Price :" + orderDetails.getTotalPrice()
        + "\n"  + "3. Order Id :" + orderDetails.getOrderId() + "\n\n" + "THANK YOU!!\n" );
        JavaMailSender.send(msg);
    }
}
