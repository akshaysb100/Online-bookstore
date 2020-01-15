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

    private Float price;

    private Long numberOfCopies;

    private String description;

    public Book() {
    }

    public Book(String author, String title, String image, Float price, String description) {

        this.author = author;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Long getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "\n\nBook{" +
                "\n\tid=" + id +
                "\n\tauthor=" + author +
                "\n\ttitle=" + title +
                "\n\timage=" + image +
                "\n\tprice=" + price +
                "\n\tno. of copies=" + this.showAvailability(numberOfCopies) +
                "\n\tdescription=" + description +
                "\n}";
    }

    private String showAvailability(Long numberOfCopies) {
        if (numberOfCopies==0) {
            return "Out of stock";
        }
        return numberOfCopies.toString();
    }

}
