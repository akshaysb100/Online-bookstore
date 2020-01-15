package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.myopencsv.CsvBuilderFactory;
import com.myopencsv.ICsvBuilder;
import com.myopencsv.OpenCsvException;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OnlineBookService {

    @Autowired
    OnlineBookRepository onlineBookRepository;

    public List<Book> getDataAsList() throws BookStoreException {
        if(onlineBookRepository.count() == 0) {
            throw new BookStoreException("NO Books Found", BookStoreException.ExceptionType.NO_BOOKS_FOUND);
        }
        return onlineBookRepository.findAll();
    }

    public void setmockObjects(OnlineBookRepository repository) {
        onlineBookRepository = repository;
    }
}
