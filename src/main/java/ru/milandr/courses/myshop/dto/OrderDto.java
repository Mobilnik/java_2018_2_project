package ru.milandr.courses.myshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.milandr.courses.myshop.entities.OrderGood;
import ru.milandr.courses.myshop.entities.enums.OrderStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OrderDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long userId;

    private short statusCode;

    public OrderStatus getStatus() {
        return OrderStatus.parse(this.statusCode);
    }

    public void setStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }

    @Getter
    @Setter
    private List<OrderGood> orderGoods;
}