package com.onlinebookstore.exception;

public class CountryNotFoundException extends RuntimeException {

    String message;

    public CountryNotFoundException() {
    }

    public CountryNotFoundException(String message) {
        super(message);
    }
}
