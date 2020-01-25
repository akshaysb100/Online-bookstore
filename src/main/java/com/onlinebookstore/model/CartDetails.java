package com.onlinebookstore.model;

import java.util.List;

public class CartDetails {

    private Customer customer;
    private List<Long> listOfOrderedBooks;
    private Double totalPrice;

    public CartDetails(Customer customer, List<Long> listOfOrderedBooks, Double totalPrice) {
        this.customer = customer;
        this.listOfOrderedBooks = listOfOrderedBooks;
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Long> getListOfOrderedBooks() {
        return listOfOrderedBooks;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
