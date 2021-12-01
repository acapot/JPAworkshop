package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Author;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl implements AuthorDAO{

    private final EntityManager entityManager;

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Author> findById(int id) {
        return Optional.ofNullable(
                entityManager.find(Author.class, id)
        );
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author create(Author entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getAuthorId() == 0){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Author update(Author entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}