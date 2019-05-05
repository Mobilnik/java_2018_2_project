package ru.milandr.courses.miptshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milandr.courses.miptshop.dtos.ProductDto;
import ru.milandr.courses.miptshop.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}