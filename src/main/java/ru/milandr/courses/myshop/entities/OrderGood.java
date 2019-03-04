package ru.milandr.courses.myshop.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS_GOODS")
@IdClass(OrderGoodPK.class)
@ToString (exclude = {"order", "good"})
public class OrderGood {
    @Id
    @Column(name = "ORDER_ID")
    @Getter
    @Setter
    private Long orderId;

    @Id
    @Column(name = "GOOD_ID")
    @Getter
    @Setter
    private Long goodId;

    @Column(name = "QUANTITY")
    @Getter
    @Setter
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOOD_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Good good;
}
