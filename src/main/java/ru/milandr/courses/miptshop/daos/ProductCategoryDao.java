package ru.milandr.courses.miptshop.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.ProductCategory;

@Repository
public interface ProductCategoryDao extends CrudRepository<ProductCategory, Long> {
}
