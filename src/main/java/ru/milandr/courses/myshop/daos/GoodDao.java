package ru.milandr.courses.myshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.myshop.entities.Good;

@Repository
public interface GoodDao extends CrudRepository<Good, Long> {
    //Igor was here
}
