package com.onlinebookstore.service;

import com.onlinebookstore.exception.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;

import java.util.List;

@Service
public class OnlineBookService {

    @Autowired
    OnlineBookRepository onlineBookRepository;

    public List<Book> getDataAsList() throws BookStoreException {
        if (onlineBookRepository.count() == 0) {
            throw new BookStoreException("NO Books Found", BookStoreException.ExceptionType.NO_BOOKS_FOUND);
        }
        return onlineBookRepository.findAll();
    }
}
