package com.onlinebookstore.exception;

public class BookStoreException extends Exception {
    public enum ExceptionType {
        NO_BOOKS_FOUND
    }

    public BookStoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public ExceptionType type;

}
