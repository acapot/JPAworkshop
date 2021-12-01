package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDAO {
    Optional<AppUser> findById(int id);
    List<AppUser> findAll();
    AppUser create(AppUser appUser);
    void delete(int id);
}
