package ru.milandr.courses.miptshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.OrderProduct;
import ru.milandr.courses.miptshop.entities.OrderProductPK;

@Repository
public interface OrderProductDao extends CrudRepository<OrderProduct, OrderProductPK> {
}