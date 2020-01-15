package com.onlinebookstore.service;

public class BookStoreException extends Exception {
    public BookStoreException(String message, ExceptionType type) {
        super(message);

    }

    public enum ExceptionType {
        FILE_INPUT_ERROR, CSV_TO_OBJECT_ERROR
    }

    public ExceptionType type;

}
