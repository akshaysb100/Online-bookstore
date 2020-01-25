package com.onlinebookstore.exception;

import org.springframework.http.HttpStatus;

public class BookStoreException extends RuntimeException {

    public HttpStatus code;
    String message;

    public BookStoreException(String message) {
        super(message);
    }

    public BookStoreException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

}
