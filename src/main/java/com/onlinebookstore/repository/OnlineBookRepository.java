package com.onlinebookstore.repository;

import com.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineBookRepository extends JpaRepository<Book,Long> {

    Book findBookById(long bookid);

    
}
