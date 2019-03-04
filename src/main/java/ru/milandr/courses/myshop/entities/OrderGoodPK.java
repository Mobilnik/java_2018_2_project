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
    private long orderId;

    @Getter
    @Setter
    private long goodId;
}
