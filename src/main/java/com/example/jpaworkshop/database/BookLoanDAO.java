package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Book;
import com.example.jpaworkshop.model.BookLoan;

import java.util.List;
import java.util.Optional;

public interface BookLoanDAO {
    Optional<BookLoan> findById(int id);
    List<BookLoan> findAll();
    BookLoan create(BookLoan entity);
    BookLoan update(BookLoan entity);
    void delete(int id);
}
