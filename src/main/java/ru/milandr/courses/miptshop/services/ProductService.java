package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.ProductCategoryDao;
import ru.milandr.courses.miptshop.daos.ProductDao;
import ru.milandr.courses.miptshop.dtos.ProductDto;
import ru.milandr.courses.miptshop.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;
import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;

    public List<ProductDto> getAll() {
        return productDao.findAllBy().stream()
                .map(this::buildProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto buildProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCategoryId(product.getCategoryId());
        productDto.setCategoryName(productCategoryDao.findOne(product.getCategoryId()).getName());
        productDto.setName(product.getName());
        productDto.setPhotoUrl(product.getPhotoUrl());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    public Product create(ProductDto productDto) throws ValidationException {
        validateIsNotNull(productDto, "No Product DTO provided");
        validateIsNull(productDto.getId(), "Can not create a product with existing id");

        Product product = buildProduct(productDto);
        productDao.save(product);
        return product;
    }

    private Product buildProduct(ProductDto productDto) {
        return new Product(null,
                productDto.getName(),
                productDto.getPhotoUrl(),
                productDto.getPrice());
    }

    public Product update(ProductDto productDto) throws ValidationException {
        validateIsNotNull(productDto, "No Product DTO provided");
        validateIsNotNull(productDto.getId(), "Can not update a product without id");

        Product product = productDao.findOne(productDto.getId());
        validateIsNotNull(product, "No Product with id " + productDto.getId());

        product.setName(productDto.getName());
        product.setPhotoUrl(productDto.getPhotoUrl());
        product.setPrice(productDto.getPrice());

        productDao.save(product);
        return product;
    }

    public void delete(Long productId) throws ValidationException {
        validateIsNotNull(productId, "No Product ID provided");
        productDao.delete(productId);
    }
}