package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.BookLoan;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookLoanDAOImpl implements BookLoanDAO{

    private final EntityManager entityManager;

    @Autowired
    public BookLoanDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BookLoan> findById(int id) {
        return Optional.ofNullable(
                entityManager.find(BookLoan.class, id)
        );
    }

    @Override
    public List<BookLoan> findAll() {
        return entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class)
                .getResultList();
    }

    @Override
    public BookLoan create(BookLoan entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getLoanId()== 0){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public BookLoan update(BookLoan entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
