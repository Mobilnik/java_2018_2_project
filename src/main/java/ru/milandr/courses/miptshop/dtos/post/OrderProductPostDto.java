package ru.milandr.courses.miptshop.dtos.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderProductPostDto {
    @Getter
    @Setter
    private long productId;

    @Getter
    @Setter
    private int quantity;

    public OrderProductPostDto(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
