package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Book;
import com.example.jpaworkshop.model.Details;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO{

    private final EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(
                entityManager.find(Book.class, id)
        );
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public Book create(Book entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getBookId() == 0){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Book update(Book entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
