package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public class DetailsDAOImpl implements DetailsDAO{

    private final EntityManager entityManager;

    @Autowired
    public DetailsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Details> findById(int id) {
        return Optional.ofNullable(
                entityManager.find(Details.class, id)
        );
    }

    @Override
    public List<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d", Details.class)
                .getResultList();
    }

    @Override
    public Details create(Details entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getDetailsId() == 0){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Details update(Details entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
