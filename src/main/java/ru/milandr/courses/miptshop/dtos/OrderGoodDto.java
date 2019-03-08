package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderGoodDto {

    @Getter
    @Setter
    private long goodId;

    @Getter
    @Setter
    private int quantity;

    public OrderGoodDto(long goodId, int quantity) {
        this.goodId = goodId;
        this.quantity = quantity;
    }
}
