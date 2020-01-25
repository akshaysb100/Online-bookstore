package com.onlinebookstore.repository;

import com.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OnlineBookRepository extends JpaRepository<Book,Long> {

    Book findBookById(long bookid);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleContaining(String titleToSearch);



}
