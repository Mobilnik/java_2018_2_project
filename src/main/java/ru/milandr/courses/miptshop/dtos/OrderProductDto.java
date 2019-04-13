package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderProductDto {

    @Getter
    @Setter
    private long productId;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal productPrice;

    @Getter
    @Setter
    private int quantity;

    public OrderProductDto(long productId,
                           String productName,
                           BigDecimal productPrice,
                           int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
