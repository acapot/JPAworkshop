package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<Book> findById(int id);
    List<Book> findAll();
    Book create(Book entity);
    Book update(Book entity);
    void delete(int id);
}
