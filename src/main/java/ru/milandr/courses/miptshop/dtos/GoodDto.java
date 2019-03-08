package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
public class GoodDto {

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

    public GoodDto(Long id, String name, byte[] photo, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
    }
}