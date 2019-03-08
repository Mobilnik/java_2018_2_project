package ru.milandr.courses.miptshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.OrderGood;
import ru.milandr.courses.miptshop.entities.OrderGoodPK;

@Repository
public interface OrderGoodDao extends CrudRepository<OrderGood, OrderGoodPK> {
}