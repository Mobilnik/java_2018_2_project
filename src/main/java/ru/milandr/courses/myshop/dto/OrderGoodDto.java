package ru.milandr.courses.myshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OrderGoodDto {
    @Getter
    @Setter
    private long orderId;

    @Getter
    @Setter
    private long goodId;

    @Getter
    @Setter
    private int quantity;
}
