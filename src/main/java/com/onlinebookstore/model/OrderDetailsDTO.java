package com.onlinebookstore.model;

import com.onlinebookstore.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class OrderDetailsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Double totalPrice;

    @OneToOne
    private Customer customer;

    private Long bookId;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(@Valid Customer customer, Long bookId, Double totalPrice) {
        this.customer = customer;
        this.bookId = bookId;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getBookId() {
        return bookId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "\n\nOrderDetails{" +
                "\n\torderId=" + orderId +
                "\n\ttotalPrice=" + totalPrice +
                "\n\tcustomer=" + customer +
                "\n\tbookId=" + bookId +
                "\n}";
    }
}
