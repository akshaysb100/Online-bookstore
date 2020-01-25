package com.onlinebookstore.exception;

import org.springframework.http.HttpStatus;

public class BookStoreException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public BookStoreException(String message) {
        super(message);
    }

    public BookStoreException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
