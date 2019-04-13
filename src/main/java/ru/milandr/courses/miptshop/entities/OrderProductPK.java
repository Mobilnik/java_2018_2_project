package ru.milandr.courses.miptshop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class OrderProductPK implements Serializable {

    @Getter
    @Setter
    private Long orderId;

    @Getter
    @Setter
    private Long productId;

    public OrderProductPK() {
    }

    public OrderProductPK(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}