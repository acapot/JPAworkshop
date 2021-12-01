package com.example.jpaworkshop.database;

import com.example.jpaworkshop.model.Details;

import java.util.List;
import java.util.Optional;

public interface DetailsDAO {
    Optional<Details>  findById(int id);
    List<Details> findAll();
    Details create(Details entity);
    Details update(Details entity);
    void delete(int id);
}
