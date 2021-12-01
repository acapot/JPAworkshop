package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {
    Optional<Author> findById(int id);
    List<Author> findAll();
    Author create(Author entity);
    Author update(Author entity);
    void delete(int id);
}