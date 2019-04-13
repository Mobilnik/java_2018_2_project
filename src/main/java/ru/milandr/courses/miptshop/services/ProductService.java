package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
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

    public ProductDto get(Long productId) throws ValidationException {
        validateIsNotNull(productId, "No Product id provided");

        Product product = productDao.findOne(productId);
        validateIsNotNull(product, "No Product with id " + productId);
        return buildProductDto(product);
    }

    public List<ProductDto> getAll() {
        return productDao.findAllBy().stream()
                 .map(this::buildProductDto)
                 .collect(Collectors.toList());
    }

    private ProductDto buildProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPhoto(product.getPhoto());
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
                productDto.getPhoto(),
                productDto.getPrice());
    }

    public Product update(ProductDto productDto) throws ValidationException {
        validateIsNotNull(productDto, "No Product DTO provided");
        validateIsNotNull(productDto.getId(), "Can not update a product without id");

        Product product = productDao.findOne(productDto.getId());
        validateIsNotNull(product, "No Product with id " + productDto.getId());

        product.setName(productDto.getName());
        product.setPhoto(productDto.getPhoto());
        product.setPrice(productDto.getPrice());

        productDao.save(product);
        return product;
    }

    public void delete(Long productId) throws ValidationException {
        validateIsNotNull(productId, "No Product ID provided");
        productDao.delete(productId);
    }
}