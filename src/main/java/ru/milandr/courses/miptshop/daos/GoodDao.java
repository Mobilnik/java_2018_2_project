package ru.milandr.courses.miptshop.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.Good;

import java.util.List;

@Repository
public interface GoodDao extends CrudRepository<Good, Long> {

    @Query("select g from Good g")
    List<Good> findAllBy();
}
