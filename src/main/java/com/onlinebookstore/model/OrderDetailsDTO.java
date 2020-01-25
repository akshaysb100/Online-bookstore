package com.onlinebookstore.model;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class OrderDetailsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Double totalPrice;

    @OneToOne
    private Customer customer;

    @ElementCollection
    private List<Long> bookIds;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(@Valid Customer customer, List<Long> bookIdList, Double totalPrice) {
        this.customer = customer;
        this.bookIds = bookIdList;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Long> getBookIds() {
        return bookIds;
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
                "\n\tbookId=" + bookIds +
                "\n}";
    }
}
