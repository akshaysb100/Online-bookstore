package com.onlinebookstore.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URL;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @CsvBindByName
    private Long id;

    @CsvBindByName
    private String author;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String image;

    @CsvBindByName
    private Double price;

    @CsvBindByName
    private String description;

    public Book(String author, String title, String image, Double price, String description) {

        this.author = author;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
