package ru.milandr.courses.myshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.myshop.entities.OrderGood;
import ru.milandr.courses.myshop.entities.OrderGoodPK;

@Repository
public interface OrderGoodDao extends CrudRepository<OrderGood, OrderGoodPK> {
}