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
    private String name;

    @Getter
    @Setter
    private byte[] photo;

    @Getter
    @Setter
    private BigDecimal price;

    public ProductDto(Long id, String name, byte[] photo, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
    }
}