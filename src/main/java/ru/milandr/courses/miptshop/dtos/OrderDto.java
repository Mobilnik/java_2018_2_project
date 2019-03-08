package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;

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

    @Getter
    @Setter
    private Short statusCode;

    @JsonIgnore
    public OrderStatus getStatus() {
        return OrderStatus.parse(this.statusCode);
    }

    @JsonIgnore
    public void setStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }

    @Getter
    @Setter
    private List<OrderGoodDto> orderGoods;
}