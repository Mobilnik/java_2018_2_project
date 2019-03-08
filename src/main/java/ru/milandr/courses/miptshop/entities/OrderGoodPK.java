package ru.milandr.courses.miptshop.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class OrderGoodPK implements Serializable {

    @Getter
    @Setter
    private Long orderId;

    @Getter
    @Setter
    private Long goodId;

    public OrderGoodPK() {
    }

    public OrderGoodPK(Long orderId, Long goodId) {
        this.orderId = orderId;
        this.goodId = goodId;
    }
}