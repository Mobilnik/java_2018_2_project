package ru.milandr.courses.myshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.myshop.entities.Order;

import javax.transaction.Transactional;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
}
