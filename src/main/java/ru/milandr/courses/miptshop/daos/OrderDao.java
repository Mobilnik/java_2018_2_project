package ru.milandr.courses.miptshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.Order;

import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {

    List<Order> findAllByUserIdAndStatusCodeNot(Long userId, Short status);

    List<Order> findByUserIdAndStatusCode(Long userId, Short statusCode);
}
