package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
//@Transactional
public class AppUserDAOImpl implements AppUserDAO{

    private final EntityManager entityManager;

    @Autowired
    public AppUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<AppUser> findById(int id) {
        return Optional.ofNullable(
            entityManager.find(AppUser.class, id)
        );
    }

    @Override
    public List<AppUser> findAll() {
        return entityManager.createQuery("SELECT au FROM AppUser au", AppUser.class)
                .getResultList();
    }

    @Override
    public AppUser create(AppUser entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getAppUserId() == 0){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
