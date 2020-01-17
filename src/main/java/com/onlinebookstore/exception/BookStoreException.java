package com.onlinebookstore.exception;

public class BookStoreException extends RuntimeException {

    String message;

    public BookStoreException() {
    }

    public BookStoreException(String message) {
        super(message);
    }

}
