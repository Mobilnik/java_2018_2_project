package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
