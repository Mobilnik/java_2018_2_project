package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long categoryId;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private byte[] photo;

    @Getter
    @Setter
    private BigDecimal price;

    public ProductDto(Long id,
                      Long categoryId,
                      String categoryName,
                      String name,
                      byte[] photo,
                      BigDecimal price) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.name = name;
        this.photo = photo;
        this.price = price;
    }
}