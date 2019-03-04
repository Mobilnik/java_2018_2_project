package ru.milandr.courses.myshop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class OrderGoodPK implements Serializable {

    @Getter
    @Setter
    private Long orderId;

    @Getter
    @Setter
    private Long goodId;
}
