package com.onlinebookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long id;


    private String author;

    private String title;

    private String image;

    private Double price;

    private String description;

    public Book() {
    }

    public Book(String author, String title, String image, Double price, String description) {

        this.author = author;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n\nBook{" +
                "\n\tid=" + id +
                "\n\tauthor=" + author +
                "\n\ttitle=" + title +
                "\n\timage=" + image +
                "\n\tprice=" + price +
                "\n\tdescription=" + description +
                "\n}";
    }
}
